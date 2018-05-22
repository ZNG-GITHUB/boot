package com.zng.stock.product.entity;

import com.zng.common.entity.CommonEntity;
import com.zng.stock.ship.entity.Ship;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.TABLE;

/**
 * 产品实体类
 */
@Entity
@Table(name = "product")
public class Product extends CommonEntity {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = TABLE, generator = "sequence_generator_product")
    @GenericGenerator(
            name = "sequence_generator_product",
            strategy = "org.hibernate.id.enhanced.TableGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "segment_value", value = "product"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled")
            })
    private Long id;

    /**
     * 产品名称
     */
    @Column(name="product_name")
    private String productName;

    /**
     * 产品编号
     */
    @Column(name = "product_code",unique = true)
    private String productCode;

    /**
     * 证书类型
     */
    @Column(name = "certificate_type")
    private String certificateType;

    /**
     * 交货日期
     */
    @Column(name = "hand_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date handDate;

    /**
     * 版本
     */
    @Column(name = "version")
    private String version;

    /**
     * 产品图号
     */
    @Column(name = "map_no")
    private String mapNo;

    /**
     * 成本统计
     */
    @Column(name = "cost")
    private Double cost;

    /**
     * 是否采购
     */
    @Column(name = "is_purchased")
    private boolean isPurchased = false;

    /**
     * 是否已到货
     */
    @Column(name = "is_arrived")
    private boolean isArrived = false;

    /**
     * 船
     */
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ship_id")
    private Ship ship;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
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

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }
}
