package com.zng.stock.product.dto;

import java.util.List;

public class ProductInfoDto {

    private Long id;
    private Long shipId;
    private String productName;
    private String productCode;
    private String certificateType;
    private String handDate;
    private String version;
    private String mapNo;
    private List<Long> treeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShipId() {
        return shipId;
    }

    public void setShipId(Long shipId) {
        this.shipId = shipId;
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

    public List<Long> getTreeId() {
        return treeId;
    }

    public void setTreeId(List<Long> treeId) {
        this.treeId = treeId;
    }
}
