package com.zng.stock.product.dto;

import java.util.Date;

/**
 * Created by John.Zhang on 2018/4/28.
 */
public class ProductTableDto {
    private Long id;
    private Long shipyardId;
    private String shipyardName;
    private Long projectId;
    private String projectName;
    private Long shipId;
    private String shipNo;
    private String classificationSociety;
    private String productName;
    private String productCode;
    private String certificateType;
    private String handDate;
    private String version;
    private String mapNo;
    private Double cost;
    private boolean isPurchased;
    private boolean isArrived;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShipyardName() {
        return shipyardName;
    }

    public void setShipyardName(String shipyardName) {
        this.shipyardName = shipyardName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getShipNo() {
        return shipNo;
    }

    public void setShipNo(String shipNo) {
        this.shipNo = shipNo;
    }

    public String getClassificationSociety() {
        return classificationSociety;
    }

    public void setClassificationSociety(String classificationSociety) {
        this.classificationSociety = classificationSociety;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getHandDate() {
        return handDate;
    }

    public void setHandDate(String handDate) {
        this.handDate = handDate;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMapNo() {
        return mapNo;
    }

    public void setMapNo(String mapNo) {
        this.mapNo = mapNo;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public boolean getIsPurchased() {
        return isPurchased;
    }

    public void setIsPurchased(boolean purchased) {
        isPurchased = purchased;
    }

    public boolean getIsArrived() {
        return isArrived;
    }

    public void setIsArrived(boolean arrived) {
        isArrived = arrived;
    }

    public Long getShipyardId() {
        return shipyardId;
    }

    public void setShipyardId(Long shipyardId) {
        this.shipyardId = shipyardId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getShipId() {
        return shipId;
    }

    public void setShipId(Long shipId) {
        this.shipId = shipId;
    }
}
