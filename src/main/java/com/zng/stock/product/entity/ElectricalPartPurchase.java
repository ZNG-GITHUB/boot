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

    /**
     * 名称
     */
    @Column(name="part_name")
    private String partName;

    /**
     * 材质
     */
    @Column(name="part_material")
    private String partMaterial;

    /**
     * 规格
     */
    @Column(name="part_specification")
    private String partSpecification;

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
     * 总成本
     */
    @Column(name="total_price")
    private Integer totalPrice;

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
