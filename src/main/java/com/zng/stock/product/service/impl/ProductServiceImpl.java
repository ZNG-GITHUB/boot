package com.zng.stock.product.service.impl;

import com.zng.common.entity.*;
import com.zng.common.service.TableService;
import com.zng.common.util.DateUtil;
import com.zng.stock.product.dto.ProductNameDto;
import com.zng.stock.product.dto.ProductTableDto;
import com.zng.stock.product.entity.Product;
import com.zng.stock.product.repository.ProductRepository;
import com.zng.stock.product.service.ProductService;
import com.zng.stock.product.view.ProductSearchRequest;
import com.zng.stock.project.entity.Project;
import com.zng.stock.ship.entity.Ship;
import com.zng.stock.shipyard.entity.Shipyard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

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
