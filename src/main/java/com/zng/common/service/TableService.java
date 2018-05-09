package com.zng.common.service;

import com.zng.common.entity.TableCondition;
import com.zng.common.entity.TableSort;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TableService {

    public static List<Order> buildSort(List<TableSort> sorts, Root<?> root, CriteriaBuilder criteriaBuilder) {

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

    public static List<Predicate> buildPredicate(List<TableCondition> conditions, Root<?> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicateList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(conditions)){
            HashMap<String,From> map = new HashMap<>();
            for(TableCondition condition : conditions){

                String attr = condition.getAttr();
                if(StringUtils.isEmpty(attr)){
                    return null;
                }
                Path path = root;
                From from = root;
                String[] attrs = attr.split("\\.");
                for(String str :attrs){
                    if(!attrs[attrs.length-1].equals(str)){
                        if(map.containsKey(str)){
                            from = map.get(str);
                        }else{
                            from = from.join(str,JoinType.LEFT);
                            map.put(str,from);
                        }
                    }else {
                        path = from.get(str);
                    }
                }
                Predicate predicate = toPredicate(condition,path,criteriaBuilder);
                if(predicate != null){
                    predicateList.add(predicate);
                }
            }
        }
        return predicateList;
    }

    public static Predicate toPredicate(TableCondition condition,Path path, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = null;
        String op = condition.getOp();
        String value = condition.getValue();
        List<String> values = condition.getValues();
        Object val = bulidType(path.getJavaType().getName(),value);

        if(!StringUtils.isEmpty(op) && !StringUtils.isEmpty(value)){
            switch (op){
                case TableCondition.EQUAL:
                    if(!StringUtils.isEmpty(val)){
                        predicate = criteriaBuilder.equal(path,val);
                    }
                    break;

                case TableCondition.LIKE:
                    predicate = criteriaBuilder.like(path,"%"+val+"%");
                    break;

                case TableCondition.IN:
                    predicate = path.in(values);
                    break;

                case TableCondition.NOT_IN:
                    predicate = path.in(values).not();
                    break;


                default:
                    return null;
            }
        }
        return predicate;
    }

    private static Object bulidType(String name, String value) {
        if(name.equals("boolean") || name.equals("java.lang.Boolean")){
            if (value.equals("0") || value.toLowerCase().equals("false")){
                return false;
            }else if(value.equals("1") || value.toLowerCase().equals("true")){
                return true;
            }else {
                return null;
            }
        }else if(name.equals("java.lang.Integer") || name.equals("int")){
            return Integer.valueOf(value);
        }else {
            return value;
        }
    }

}
