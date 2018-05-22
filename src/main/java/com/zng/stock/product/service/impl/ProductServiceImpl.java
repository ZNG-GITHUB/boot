package com.zng.stock.product.service.impl;

import com.zng.common.entity.*;
import com.zng.common.service.TableService;
import com.zng.common.util.DateUtil;
import com.zng.stock.product.dto.ProductInfoDto;
import com.zng.stock.product.dto.ProductNameDto;
import com.zng.stock.product.dto.ProductTableDto;
import com.zng.stock.product.entity.Product;
import com.zng.stock.product.repository.ProductRepository;
import com.zng.stock.product.service.ProductService;
import com.zng.stock.product.view.ProductSaveRequest;
import com.zng.stock.product.view.ProductSearchRequest;
import com.zng.stock.project.entity.Project;
import com.zng.stock.ship.entity.Ship;
import com.zng.stock.ship.repository.ShipRepository;
import com.zng.stock.shipyard.entity.Shipyard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Table;
import javax.persistence.criteria.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShipRepository shipRepository;

    /**
     * 获得产品列表
     * @param request
     * @return
     */
    @Override
    public ResponseModel table(CommonTableRequest request) {

        PageRequest pageRequest = PageRequest.of(request.getPage().getToPage()-1,request.getPage().getPageSize());

        Page<Product> list = productRepository.findAll(new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<TableCondition> conditions = request.getConditions();
//                List<Predicate> predicateList = new ArrayList<>();
                List<Predicate> predicateList = TableService.buildPredicate(conditions,root,criteriaBuilder);
                predicateList.add(criteriaBuilder.isFalse(root.get("isDeleted")));
                List<TableSort> sorts = request.getSorts();
                List<Order> sortList = TableService.buildSort(sorts,root,criteriaBuilder);
                criteriaQuery.orderBy(sortList);
                criteriaQuery.distinct(true);
                criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
                return criteriaQuery.getRestriction();
            }
        },pageRequest);
        List<ProductTableDto> tableList = getTableDtoList(list.getContent());
        TablePage pageInfo = request.getPage();
        pageInfo.setTotalCount(list.getTotalElements());
        return new ResponseModel(tableList).put("pageInfo",pageInfo);
    }

    /**
     * 根据船只获得产品
     * @param request
     * @return
     */
    @Override
    public ResponseModel getByShipId(ProductSearchRequest request) {

        Long shipId = request.getShipId();
        String productName = request.getProductName();
        String productCode = request.getProductCode();

        List<Product> list = productRepository.findByShipIdAndNameAndCode(shipId,productName,productCode);

        List<ProductNameDto> dtos = new ArrayList<>();

        for(Product product : list){
            ProductNameDto dto = new ProductNameDto();
            dto.setId(product.getId());
            dto.setName(product.getProductName());
            dto.setCode(product.getProductCode());
            dtos.add(dto);
        }
        return new ResponseModel(dtos);
    }

    /**
     * 保存产品
     * @param request
     * @return
     */
    @Override
    public ResponseModel save(ProductSaveRequest request) {

        Long shipId = request.getShipId();
        Ship ship = shipRepository.findByIdSoftly(shipId);
        if(ship == null){
            return new ResponseModel(ResponseCode.Error,"船只不存在");
        }
        Product product = new Product();
        if(request.getId() != null){
            product = productRepository.findByIdSoftly(request.getId());
        }
        product.setShip(ship);
        product.setCertificateType(request.getCertificateType());
        product.setProductName(request.getProductName());
        product.setProductCode(request.getProductCode());
        product.setHandDate(request.getHandDate());
        product.setMapNo(request.getMapNo());
        product.setVersion(request.getVersion());
        productRepository.save(product);

        return new ResponseModel();
    }

    /**
     * 获得产品信息
     * @param id
     * @return
     */
    @Override
    public ResponseModel info(@NotNull Long id) {
        Product product = productRepository.findByIdSoftly(id);
        if(product != null){
            ProductInfoDto dto = new ProductInfoDto();
            dto.setId(product.getId());
            dto.setCertificateType(product.getCertificateType());
            dto.setHandDate(DateUtil.formatShortDate(product.getHandDate()));
            dto.setProductCode(product.getProductCode());
            dto.setProductName(product.getProductName());
            dto.setMapNo(product.getMapNo());
            dto.setVersion(product.getVersion());
            List<Long> shipId = new ArrayList<>();
            if(product.getShip() != null){
                dto.setShipId(product.getShip().getId());
                shipId.add(product.getShip().getId());
            }
            if(product.getShip().getProject() != null){
                shipId.add(product.getShip().getProject().getId());
            }
            if(product.getShip().getProject().getShipyard() != null){
                shipId.add(product.getShip().getProject().getShipyard().getId());
            }
            dto.setTreeId(shipId);
            
            return new ResponseModel(dto);
        }
        return new ResponseModel();
    }

    @Override
    @Transactional(readOnly = false)
    public ResponseModel delete(@NotNull Long id) {
        productRepository.deleteByIdSoftly(id);
        return new ResponseModel();
    }


    private List<ProductTableDto> getTableDtoList(List<Product> list) {
        List<ProductTableDto> tableList = new ArrayList<>();
        for(Product product : list){
            ProductTableDto dto = new ProductTableDto();
            dto.setId(product.getId());
            dto.setIsArrived(product.isArrived());
            dto.setIsPurchased(product.isPurchased());
            dto.setCertificateType(product.getCertificateType());
            Ship ship = product.getShip();
            if(ship != null){
                dto.setClassificationSociety(ship.getClassificationSociety());
                dto.setShipNo(ship.getShipNo());
                dto.setShipId(ship.getId());
                Project project = ship.getProject();
                if(project != null){
                    dto.setProjectName(project.getProjectName());
                    dto.setProjectId(project.getId());
                    Shipyard shipyard = project.getShipyard();
                    if(shipyard != null){
                        dto.setShipyardId(shipyard.getId());
                        dto.setShipyardName(shipyard.getShipyardName());
                    }
                }
            }
            dto.setCost(product.getCost());
            dto.setVersion(product.getVersion());
            dto.setHandDate(DateUtil.formatShortDate(product.getHandDate()));
            dto.setMapNo(product.getMapNo());
            dto.setProductCode(product.getProductCode());
            dto.setProductName(product.getProductName());
            tableList.add(dto);
        }

        return tableList;
    }
}
