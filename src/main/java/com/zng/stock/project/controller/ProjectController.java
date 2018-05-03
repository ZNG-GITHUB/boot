package com.zng.stock.project.controller;

import com.zng.common.entity.CommonTableRequest;
import com.zng.common.entity.ResponseModel;
import com.zng.stock.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * Created by John.Zhang on 2018/5/3.
 */
@RestController
@RequestMapping("project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("byshipyardId/{shipyardId}")
    public ResponseModel getByShipyardId(@PathVariable("shipyardId")@NotNull Long shipyardId){
        return projectService.getByShipyardId(shipyardId);
    }

}
