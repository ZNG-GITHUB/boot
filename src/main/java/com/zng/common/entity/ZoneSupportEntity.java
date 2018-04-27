package com.zng.common.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class ZoneSupportEntity extends CommonEntity{

    @Column(name = "zone_id",nullable = false)
    private Long zoneId;

    public Long getZoneId() {
        return zoneId;
    }

    public void setZoneId(Long zoneId) {
        this.zoneId = zoneId;
    }
}
