package com.zng.stock.product.service.impl;

import com.zng.common.entity.CommonTableRequest;
import com.zng.common.entity.ResponseModel;
import com.zng.common.entity.TableCondition;
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
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
                List<Predicate> predicateList = buildPredicate(conditions,root,criteriaBuilder);

                return null;
            }
        },pageRequest);
        List<ProductTableDto> tableList = getTableDtoList(list.getContent());
        return new ResponseModel(tableList);
    }

    private List<Predicate> buildPredicate(List<TableCondition> conditions, Root<Product> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicateList = new ArrayList<>();
        for(TableCondition condition : conditions){
            Predicate predicate = toPredicate(condition,root,criteriaBuilder);
            if(predicate != null){
                predicateList.add(predicate);
            }
        }
        return predicateList;
    }

    private Predicate toPredicate(TableCondition condition,Root<Product> root, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = null;

        String attr = condition.getAttr();
        String op = condition.getOp();
        String value = condition.getValue();
        List<String> values = condition.getValues();

        if(!StringUtils.isEmpty(attr) && !StringUtils.isEmpty(op)){
            switch (op){
                case TableCondition.EQUAL:
                    predicate = criteriaBuilder.equal(root.get(attr),value);
                    break;

                case TableCondition.LIKE:
                    predicate = criteriaBuilder.like(root.get(attr),"%"+value+"%");
                    break;

                case TableCondition.IN:
                    predicate = root.in(values);
                    break;

                case TableCondition.NOT_IN:
                    predicate = root.in(values).not();
                    break;


                default:
                    return null;
            }
        }
        return predicate;
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
