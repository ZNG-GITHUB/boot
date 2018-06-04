package com.zng.stock.product.service.impl;

import com.zng.common.entity.*;
import com.zng.common.service.TableService;
import com.zng.common.util.DateUtil;
import com.zng.stock.product.dto.ElectricalPartPurchaseTableDto;
import com.zng.stock.product.entity.ElectricalPart;
import com.zng.stock.product.entity.ElectricalPartPurchase;
import com.zng.stock.product.repository.ElectricalPartPurchaseRepository;
import com.zng.stock.product.repository.ElectricalPartRepository;
import com.zng.stock.product.service.ElectricalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ElectricalServiceImpl implements ElectricalService {

    @Autowired
    private ElectricalPartRepository electricalPartRepository;

    @Autowired
    private ElectricalPartPurchaseRepository electricalPartPurchaseRepository;

    /**
     * 获得电气外购件采购单列表
     * @param request
     * @return
     */
    @Override
    public ResponseModel purchaseTable(CommonTableRequest request) {
        PageRequest pageRequest = PageRequest.of(request.getPage().getToPage()-1,request.getPage().getPageSize());

        Page<ElectricalPartPurchase> list = electricalPartPurchaseRepository.findAll(new Specification<ElectricalPartPurchase>() {
            @Override
            public Predicate toPredicate(Root<ElectricalPartPurchase> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

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
        List<ElectricalPartPurchaseTableDto> tableList = getPurchaseTableDtoList(list.getContent());
        TablePage pageInfo = request.getPage();
        pageInfo.setTotalCount(list.getTotalElements());
        return new ResponseModel(tableList).put("pageInfo",pageInfo);
    }

    private List<ElectricalPartPurchaseTableDto> getPurchaseTableDtoList(List<ElectricalPartPurchase> content) {
        List<ElectricalPartPurchaseTableDto> tableList = new ArrayList<>();

        for(ElectricalPartPurchase purchase : content){
            ElectricalPartPurchaseTableDto dto = new ElectricalPartPurchaseTableDto();
            dto.setId(purchase.getId());
            dto.setArrived(purchase.isArrived());
            dto.setPurchased(purchase.isPurchased());
            dto.setCreateDate(DateUtil.formatDate(purchase.getCreateDate()));
            dto.setUpdateDate(DateUtil.formatDate(purchase.getUpdateDate()));
            dto.setPartMaterial(purchase.getPartMaterial());
            dto.setPartName(purchase.getPartName());
            dto.setPartSpecification(purchase.getPartSpecification());
            dto.setPurchaseCount(purchase.getPurchaseCount());
            dto.setTotalCount(purchase.getTotalCount());
            dto.setRemarks(purchase.getRemarks());
            dto.setTotalPrice(purchase.getTotalPrice());

            dto.setStockCount(0);
            dto.setStockUsedCount(0);

            tableList.add(dto);
        }

        return tableList;
    }
}
