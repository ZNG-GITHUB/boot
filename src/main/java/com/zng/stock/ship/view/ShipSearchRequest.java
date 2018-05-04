package com.zng.stock.ship.view;

/**
 * Created by John.Zhang on 2018/5/4.
 */
public class ShipSearchRequest {

    private Long projectId;

    private String shipNo;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getShipNo() {
        return shipNo;
    }

    public void setShipNo(String shipNo) {
        this.shipNo = shipNo;
    }
}
