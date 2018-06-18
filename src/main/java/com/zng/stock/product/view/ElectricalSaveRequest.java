package com.zng.stock.product.view;

import java.util.List;

/**
 * @author John.Zhang
 * @version 1.0
 * @date 2018/6/5
 */
public class ElectricalSaveRequest {

    private Long productId;

    private String partMaterial;

    private String partName;

    private String partSpecification;

    private Integer totalCount;

    private Integer purchaseCount;

    private Double price;

    private Double totalPrice;

    private String remarks;

    private List<PartUseView> useList;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getPartMaterial() {
        return partMaterial;
    }

    public void setPartMaterial(String partMaterial) {
        this.partMaterial = partMaterial;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPartSpecification() {
        return partSpecification;
    }

    public void setPartSpecification(String partSpecification) {
        this.partSpecification = partSpecification;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(Integer purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<PartUseView> getUseList() {
        return useList;
    }

    public void setUseList(List<PartUseView> useList) {
        this.useList = useList;
    }
}
