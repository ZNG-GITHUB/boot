package com.zng.common.service;

import com.zng.common.entity.TableCondition;
import com.zng.common.entity.TableSort;
import com.zng.stock.product.entity.Product;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class TableService {

    public static List<Order> buildSort(List<TableSort> sorts, Root<Product> root, CriteriaBuilder criteriaBuilder) {

        List<Order> sortList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(sorts)){
            for(TableSort tableSort : sorts){
                String attr = tableSort.getAttr();
                String type = tableSort.getType();

                if(StringUtils.isEmpty(attr) || StringUtils.isEmpty(type)){
                    continue;
                }
                if(type.toLowerCase().equals("asc")){
                    sortList.add(criteriaBuilder.asc(root.get(attr)));
                }else if(type.toLowerCase().equals("desc")){
                    sortList.add(criteriaBuilder.desc(root.get(attr)));
                }
            }
        }
        if(CollectionUtils.isEmpty(sortList)){
            sortList.add(criteriaBuilder.desc(root.get("id")));
        }

        return sortList;
    }

    public static List<Predicate> buildPredicate(List<TableCondition> conditions, Root<Product> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicateList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(conditions)){
            for(TableCondition condition : conditions){
                Predicate predicate = toPredicate(condition,root,criteriaBuilder);
                if(predicate != null){
                    predicateList.add(predicate);
                }
            }
        }
        return predicateList;
    }

    public static Predicate toPredicate(TableCondition condition,Root<Product> root, CriteriaBuilder criteriaBuilder) {
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

}
