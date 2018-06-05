package com.zng.stock.product.entity;

import com.zng.common.entity.CommonEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import static javax.persistence.GenerationType.TABLE;

/**
 * 库存领用表
 */
@Entity
@Table(name = "electrical_part_use")
public class ElectricalPartUse extends CommonEntity {

    @Id
    @Column(name = "electrical_use_id")
    @GeneratedValue(strategy = TABLE, generator = "sequence_generator_electrical_use")
    @GenericGenerator(
            name = "sequence_generator_electrical_use",
            strategy = "org.hibernate.id.enhanced.TableGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "segment_value", value = "electrical_use"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled")
            })
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "part_detail_id")
    private ElectricalPartDetail electricalPartDetail;

    @Column(name = "used_count")
    private Integer usedCount;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "part_purchase_id")
    private ElectricalPartPurchase electricalPartPurchase;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ElectricalPartDetail getElectricalPartDetail() {
        return electricalPartDetail;
    }

    public void setElectricalPartDetail(ElectricalPartDetail electricalPartDetail) {
        this.electricalPartDetail = electricalPartDetail;
    }

    public Integer getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(Integer usedCount) {
        this.usedCount = usedCount;
    }

    public ElectricalPartPurchase getElectricalPartPurchase() {
        return electricalPartPurchase;
    }

    public void setElectricalPartPurchase(ElectricalPartPurchase electricalPartPurchase) {
        this.electricalPartPurchase = electricalPartPurchase;
    }
}
