package com.zng.stock.ship.controller;

import com.zng.common.entity.ResponseModel;
import com.zng.stock.ship.service.ShipService;
import com.zng.stock.ship.view.ShipSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * Created by John.Zhang on 2018/5/4.
 */
@RestController
@RequestMapping("ship")
public class ShipController {

    @Autowired
    private ShipService shipService;

    @PostMapping("byprojectId")
    public ResponseModel getByProjectId(@RequestBody ShipSearchRequest request){
        return shipService.getByProjectId(request);
    }

    @PostMapping("tree")
    public ResponseModel tree(){
        return shipService.tree();
    }

}
