package com.zng.stock.product.service.impl;

import com.zng.common.entity.CommonTableRequest;
import com.zng.common.entity.ResponseModel;
import com.zng.common.entity.TableCondition;
import com.zng.common.entity.TableSort;
import com.zng.common.service.TableService;
import com.zng.stock.product.dto.ProductTableDto;
import com.zng.stock.product.entity.Product;
import com.zng.stock.product.respository.ProductRespository;
import com.zng.stock.product.service.ProductService;
import com.zng.stock.project.entity.Project;
import com.zng.stock.ship.entity.Ship;
import com.zng.stock.shipyard.entity.Shipyard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRespository productRespository;

    @Override
    public ResponseModel table(CommonTableRequest request) {

        PageRequest pageRequest = PageRequest.of(request.getPage().getToPage(),request.getPage().getPageSize());

        Page<Product> list = productRespository.findAll(new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<TableCondition> conditions = request.getConditions();
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
        return new ResponseModel(tableList);
    }


    private List<ProductTableDto> getTableDtoList(List<Product> list) {
        List<ProductTableDto> tableList = new ArrayList<>();
        for(Product product : list){
            ProductTableDto dto = new ProductTableDto();
            dto.setId(product.getId());
            dto.setArrived(product.isArrived());
            dto.setCertificateType(product.getCertificateType());
            Ship ship = product.getShip();
            if(ship != null){
                dto.setClassificationSociety(ship.getClassificationSociety());
                Project project = ship.getProject();
                if(project != null){
                    dto.setProjectName(project.getProjectName());
                    Shipyard shipyard = project.getShipyard();
                    if(shipyard != null){
                        dto.setShipyardName(shipyard.getShipyardName());
                    }
                }
            }
            dto.setCost(product.getCost());
            dto.setHandDate(product.getHandDate());
            dto.setMapNo(product.getMapNo());
            dto.setProductCode(product.getProductCode());
            dto.setProductName(product.getProductName());
            tableList.add(dto);
        }

        return tableList;
    }
}
