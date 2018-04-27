package com.zng.stock.shipyard.entity;

import com.zng.common.entity.CommonEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import static javax.persistence.GenerationType.TABLE;


/**
 * 船厂实体类
 */
@Entity
@Table(name = "shipyard")
public class Shipyard extends CommonEntity {

    @Id
    @Column(name = "shipyard_id")
    @GeneratedValue(strategy = TABLE, generator = "sequence_generator_shipyard")
    @GenericGenerator(
            name = "sequence_generator_shipyard",
            strategy = "org.hibernate.id.enhanced.TableGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "segment_value", value = "shipyard"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled")
            })
    private Long id;

    /**
     * 船厂名称
     */
    @Column(name = "shipyard_name")
    private String shipyardName;

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
}
