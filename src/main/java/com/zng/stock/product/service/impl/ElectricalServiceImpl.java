package com.zng.stock.product.service.impl;

import com.zng.common.entity.*;
import com.zng.common.service.TableService;
import com.zng.stock.product.entity.ElectricalPart;
import com.zng.stock.product.repository.ElectricalPartRepository;
import com.zng.stock.product.service.ElectricalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.List;

@Service
public class ElectricalServiceImpl implements ElectricalService {

    @Autowired
    private ElectricalPartRepository electricalPartRepository;

    /**
     * 获得电气外购件列表
     * @param request
     * @return
     */
    @Override
    public ResponseModel table(CommonTableRequest request) {
        PageRequest pageRequest = PageRequest.of(request.getPage().getToPage()-1,request.getPage().getPageSize());

        Page<ElectricalPart> list = electricalPartRepository.findAll(new Specification<ElectricalPart>() {
            @Override
            public Predicate toPredicate(Root<ElectricalPart> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

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
//        List<ProductTableDto> tableList = getTableDtoList(list.getContent());
        TablePage pageInfo = request.getPage();
        pageInfo.setTotalCount(list.getTotalElements());
        return new ResponseModel(list.getContent()).put("pageInfo",pageInfo);
    }
}
