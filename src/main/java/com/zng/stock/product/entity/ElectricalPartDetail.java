package com.zng.stock.product.entity;


import com.zng.common.entity.CommonEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import static javax.persistence.GenerationType.TABLE;

@Entity
@Table(name = "electrical_part_detail")
public class ElectricalPartDetail extends CommonEntity {

    @Id
    @Column(name = "electrical_detail_id")
    @GeneratedValue(strategy = TABLE, generator = "sequence_generator_electrical_detail")
    @GenericGenerator(
            name = "sequence_generator_electrical_detail",
            strategy = "org.hibernate.id.enhanced.TableGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "segment_value", value = "electrical_detail"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled")
            })
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "part_id")
    private ElectricalPart electricalPart;

    /**
     * 价格
     */
    @Column(name="price")
    private Double price;

    /**
     * 数量
     */
    @Column(name="count")
    private Integer count;

    /**
     * 总值
     */
    @Column(name="total_value")
    private Double totalValue;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }
}
