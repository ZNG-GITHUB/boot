package com.zng.stock.project.entity;

import com.zng.common.entity.CommonEntity;
import com.zng.stock.delivery.entity.DeliveryNote;
import com.zng.stock.shipyard.entity.Shipyard;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.TABLE;

/**
 * 项目实体类
 */
@Entity
@Table(name = "project")
public class Project extends CommonEntity{

    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy = TABLE, generator = "sequence_generator_project")
    @GenericGenerator(
            name = "sequence_generator_project",
            strategy = "org.hibernate.id.enhanced.TableGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "segment_value", value = "project"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled")
            })
    private Long id;

    @Column(name="project_name")
    private String projectName;

    /**
     * 是否送审
     */
    @Column(name = "is_get_auditing")
    private boolean isGetAuditing;

    /**
     * 是否退审
     */
    @Column(name = "is_back_auditing")
    private boolean isBackAuditing;

    /**
     * 合同金额
     */
    @Column(name = "contract_amount")
    private Double contractAmount;

    /**
     * 成本汇总
     */
    @Column(name = "cost_summary")
    private Double costSummary;

    /**
     * 送货单
     */
    @ManyToOne
    @JoinColumn(name = "delivery_note_id")
    private DeliveryNote deliveryNote;

    /**
     * 船厂
     */
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "shipyard_id")
    private Shipyard shipyard;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public boolean isGetAuditing() {
        return isGetAuditing;
    }

    public void setGetAuditing(boolean getAuditing) {
        isGetAuditing = getAuditing;
    }

    public boolean isBackAuditing() {
        return isBackAuditing;
    }

    public void setBackAuditing(boolean backAuditing) {
        isBackAuditing = backAuditing;
    }

    public Double getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(Double contractAmount) {
        this.contractAmount = contractAmount;
    }

    public Double getCostSummary() {
        return costSummary;
    }

    public void setCostSummary(Double costSummary) {
        this.costSummary = costSummary;
    }

    public Shipyard getShipyard() {
        return shipyard;
    }

    public void setShipyard(Shipyard shipyard) {
        this.shipyard = shipyard;
    }

    public DeliveryNote getDeliveryNote() {
        return deliveryNote;
    }

    public void setDeliveryNote(DeliveryNote deliveryNote) {
        this.deliveryNote = deliveryNote;
    }
}
