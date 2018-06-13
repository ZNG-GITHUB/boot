package com.zng.stock.product.service.impl;

import com.zng.common.entity.*;
import com.zng.common.service.TableService;
import com.zng.common.util.DateUtil;
import com.zng.stock.product.dto.ElectricalPartPurchaseTableDto;
import com.zng.stock.product.entity.ElectricalPart;
import com.zng.stock.product.entity.ElectricalPartDetail;
import com.zng.stock.product.entity.ElectricalPartPurchase;
import com.zng.stock.product.entity.ElectricalPartUse;
import com.zng.stock.product.repository.ElectricalPartDetailRepository;
import com.zng.stock.product.repository.ElectricalPartPurchaseRepository;
import com.zng.stock.product.repository.ElectricalPartRepository;
import com.zng.stock.product.repository.ElectricalPartUseRepository;
import com.zng.stock.product.service.ElectricalService;
import com.zng.stock.product.view.ElectricalSaveRequest;
import com.zng.stock.product.view.PartUseView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ElectricalServiceImpl implements ElectricalService {

    @Autowired
    private ElectricalPartRepository electricalPartRepository;

    @Autowired
    private ElectricalPartPurchaseRepository electricalPartPurchaseRepository;

    @Autowired
    private ElectricalPartDetailRepository electricalPartDetailRepository;

    @Autowired
    private ElectricalPartUseRepository electricalPartUseRepository;

    /**
     * 获得电气外购件采购单列表
     * @param request
     * @param prodectId
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseModel purchaseTable(CommonTableRequest request, Long prodectId) {
        PageRequest pageRequest = PageRequest.of(request.getPage().getToPage()-1,request.getPage().getPageSize());

        Page<ElectricalPartPurchase> list = electricalPartPurchaseRepository.findAll(new Specification<ElectricalPartPurchase>() {
            @Override
            public Predicate toPredicate(Root<ElectricalPartPurchase> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<TableCondition> conditions = request.getConditions();
                List<Predicate> predicateList = TableService.buildPredicate(conditions,root,criteriaBuilder);

//                predicateList.add(criteriaBuilder.)

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

    /**
     * 电器外购件使用
     * @param request
     * @return
     */
    @Override
    @Transactional
    public ResponseModel save(ElectricalSaveRequest request) {

        String partMaterial = request.getPartMaterial();
        String partName = request.getPartName();
        String partSpecification = request.getPartSpecification();

        //找到对应的电器外购件信息
        ElectricalPart electricalPart = electricalPartRepository.findByNameAndMaterialAndSpecSoftly(partMaterial,partName,partSpecification);
        if(electricalPart == null){
            return new ResponseModel(ResponseCode.Error,"未找到对应的电器外购件信息");
        }

        ElectricalPartPurchase purchase = new ElectricalPartPurchase();
        purchase.setElectricalPart(electricalPart);
        purchase.setTotalPrice(request.getTotalPrice());
        purchase.setTotalCount(request.getTotalCount());
        purchase.setPurchaseCount(request.getPurchaseCount());
        purchase.setPurchasePrice(request.getPrice());
        purchase.setRemarks(request.getRemarks());
        purchase = electricalPartPurchaseRepository.save(purchase);

        List<PartUseView> useList = request.getUseList();

        if(!CollectionUtils.isEmpty(useList)){
            List<ElectricalPartUse> saveList = new ArrayList<>();
            for(PartUseView useView : useList){
                Long id = useView.getId();
                Integer useCount = useView.getUseCount();
                ElectricalPartDetail detail = electricalPartDetailRepository.findByIdSoftly(id);
                if(detail != null){

                    /*Integer stockCount = detail.getCount();
                    if(stockCount < useCount){
                        return new ResponseModel(ResponseCode.Error,"库存不足");
                    }
                    detail.setCount(stockCount-useCount);
                    detail = electricalPartDetailRepository.save(detail);*/

                    ElectricalPartUse use = new ElectricalPartUse();
                    use.setElectricalPartDetail(detail);
                    use.setElectricalPartPurchase(purchase);
                    use.setUsedCount(useCount);
                    saveList.add(use);
                }
            }
            if(!CollectionUtils.isEmpty(saveList)){
                electricalPartUseRepository.saveAll(saveList);
            }
        }

        return new ResponseModel();
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
            if(purchase.getElectricalPart() != null){
                dto.setPartMaterial(purchase.getElectricalPart().getPartMaterial());
                dto.setPartName(purchase.getElectricalPart().getPartName());
                dto.setPartSpecification(purchase.getElectricalPart().getPartSpecification());
            }
            dto.setPurchaseCount(purchase.getPurchaseCount());
            dto.setTotalCount(purchase.getTotalCount());
            dto.setRemarks(purchase.getRemarks());
            dto.setTotalPrice(purchase.getTotalPrice());

            Integer stockCount = 0;
            Integer stockUsedCount = 0;
            dto.setStockCount(stockCount);
            dto.setStockUsedCount(stockUsedCount);

            tableList.add(dto);
        }

        return tableList;
    }
}
