package com.zng.stock.ship.entity;

import com.zng.common.entity.CommonEntity;
import com.zng.stock.project.entity.Project;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import static javax.persistence.GenerationType.TABLE;

/**
 * 船实体类
 */
@Entity
@Table(name = "ship")
public class Ship extends CommonEntity {

    @Id
    @Column(name = "ship_id")
    @GeneratedValue(strategy = TABLE, generator = "sequence_generator_ship")
    @GenericGenerator(
            name = "sequence_generator_ship",
            strategy = "org.hibernate.id.enhanced.TableGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "segment_value", value = "ship"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled")
            })
    private Long id;

    /**
     * 船名
     */
    @Column(name = "ship_name")
    private String shipName;

    /**
     * 船号
     */
    @Column(name = "ship_no")
    private String shipNo;

    /**
     * 船级社
     */
    @Column(name = "classification_society")
    private String classificationSociety;

    /**
     * 项目
     */
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
