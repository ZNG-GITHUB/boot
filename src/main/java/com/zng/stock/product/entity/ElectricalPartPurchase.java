package com.zng.stock.product.entity;

import com.zng.common.entity.CommonEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import static javax.persistence.GenerationType.TABLE;

/**
 * 采购单
 */
@Entity
@Table(name = "electrical_part_purchase")
public class ElectricalPartPurchase extends CommonEntity {

    @Id
    @Column(name = "electrical_purchase_id")
    @GeneratedValue(strategy = TABLE, generator = "sequence_generator_electrical_purchase")
    @GenericGenerator(
            name = "sequence_generator_electrical_purchase",
            strategy = "org.hibernate.id.enhanced.TableGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "segment_value", value = "electrical_purchase"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled")
            })
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "part_id")
    private ElectricalPart electricalPart;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * 总数量
     */
    @Column(name="total_count")
    private Integer totalCount;


    /**
     * 采购数量
     */
    @Column(name="purchase_count")
    private Integer purchaseCount;

    /**
     * 采购单价
     */
    @Column(name="purchase_price")
    private Double purchasePrice;

    /**
     * 总成本
     */
    @Column(name="total_price")
    private Double totalPrice;

    /**
     * 备注
     */
    @Column(name="remarks")
    private String remarks;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ElectricalPart getElectricalPart() {
        return electricalPart;
    }

    public void setElectricalPart(ElectricalPart electricalPart) {
        this.electricalPart = electricalPart;
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

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
