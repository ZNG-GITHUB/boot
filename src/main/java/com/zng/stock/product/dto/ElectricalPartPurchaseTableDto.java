package com.zng.stock.product.dto;

/**
 * @author John.Zhang
 * @version 1.0
 * @date 2018/6/4
 */
public class ElectricalPartPurchaseTableDto {

    private Long id;
    private String partName;
    private String partMaterial;
    private String partSpecification;
    private Integer totalCount;
    private Integer stockCount;
    private Integer stockUsedCount;
    private Integer purchaseCount;
    private Integer totalPrice;
    private String createDate;
    private String updateDate;
    private String remarks;
    private boolean isPurchased = false;
    private boolean isArrived = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPartMaterial() {
        return partMaterial;
    }

    public void setPartMaterial(String partMaterial) {
        this.partMaterial = partMaterial;
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

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Integer getStockUsedCount() {
        return stockUsedCount;
    }

    public void setStockUsedCount(Integer stockUsedCount) {
        this.stockUsedCount = stockUsedCount;
    }

    public Integer getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(Integer purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public boolean isPurchased() {
        return isPurchased;
    }

    public void setPurchased(boolean purchased) {
        isPurchased = purchased;
    }

    public boolean isArrived() {
        return isArrived;
    }

    public void setArrived(boolean arrived) {
        isArrived = arrived;
    }
}
