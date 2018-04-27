package com.zng.stock.delivery.entity;

import com.zng.common.entity.CommonEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.TABLE;

@Entity
@Table(name = "delivery_note")
public class DeliveryNote extends CommonEntity {

    @Id
    @Column(name = "delivery_id")
    @GeneratedValue(strategy = TABLE, generator = "sequence_generator_delivery")
    @GenericGenerator(
            name = "sequence_generator_delivery",
            strategy = "org.hibernate.id.enhanced.TableGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "segment_value", value = "delivery"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled")
            })
    private Long id;


    /**
     * 送货单名称
     */
    @Column(name = "note_name")
    private String noteName;

    /**
     * 送货单号
     */
    @Column(name = "note_code",nullable = false,unique = true,length = 50)
    private String noteCode;

    /**
     * 送货时间
     */
    @Column(name = "delivery_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryDate;

    /**
     * 运费
     */
    @Column(name = "freight")
    private Double freight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public String getNoteCode() {
        return noteCode;
    }

    public void setNoteCode(String noteCode) {
        this.noteCode = noteCode;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }
}
