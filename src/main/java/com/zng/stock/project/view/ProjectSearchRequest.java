package com.zng.stock.project.view;

/**
 * Created by John.Zhang on 2018/5/4.
 */
public class ProjectSearchRequest {

    private Long shipyardId;

    private String projectName;

    public Long getShipyardId() {
        return shipyardId;
    }

    public void setShipyardId(Long shipyardId) {
        this.shipyardId = shipyardId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
