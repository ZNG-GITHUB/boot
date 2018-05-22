package com.zng.stock.product.view;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ProductSaveRequest {

    private Long id;
    @NotNull
    private Long shipId;
    @NotNull
    private String productName;
    private String productCode;
    private String certificateType;
    private Date handDate;
    private String version;
    private String mapNo;

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

    public Date getHandDate() {
        return handDate;
    }

    public void setHandDate(Date handDate) {
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
}
