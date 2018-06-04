package com.zng.stock.product.entity;

import com.zng.common.entity.CommonEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import static javax.persistence.GenerationType.TABLE;

@Entity
@Table(name = "electrical_part")
public class ElectricalPart extends CommonEntity{

    @Id
    @Column(name = "electrical_part_id")
    @GeneratedValue(strategy = TABLE, generator = "sequence_generator_electrical_part")
    @GenericGenerator(
            name = "sequence_generator_electrical_part",
            strategy = "org.hibernate.id.enhanced.TableGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "segment_value", value = "electrical_part"),
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
     * 数量
     */
    @Column(name="part_count")
    private Integer partCount;


    /**
     * 备注
     */
    @Column(name="remarks")
    private String remarks;

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

    public Integer getPartCount() {
        return partCount;
    }

    public void setPartCount(Integer partCount) {
        this.partCount = partCount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
