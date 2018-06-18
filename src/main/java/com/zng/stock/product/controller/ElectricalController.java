package com.zng.stock.product.controller;

import com.zng.common.entity.CommonTableRequest;
import com.zng.common.entity.ResponseModel;
import com.zng.stock.product.service.ElectricalService;
import com.zng.stock.product.view.ElectricalSaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("electrical")
public class ElectricalController {

    @Autowired
    private ElectricalService electricalService;

    @PostMapping("table/{productId}")
    public ResponseModel purchaseTable(@RequestBody CommonTableRequest request,@PathVariable @NotNull Long productId){
        return electricalService.purchaseTable(request,productId);
    }

    @PostMapping("save")
    public ResponseModel save(@RequestBody ElectricalSaveRequest request){
        return electricalService.save(request);
    }

}
