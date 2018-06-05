package com.zng.stock.product.controller;

import com.zng.common.entity.CommonTableRequest;
import com.zng.common.entity.ResponseModel;
import com.zng.stock.product.service.ElectricalService;
import com.zng.stock.product.view.ElectricalSaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("electrical")
public class ElectricalController {

    @Autowired
    private ElectricalService electricalService;

    @PostMapping("table")
    public ResponseModel purchaseTable(@RequestBody CommonTableRequest request){
        return electricalService.purchaseTable(request);
    }

    @PostMapping("save")
    public ResponseModel save(@RequestBody ElectricalSaveRequest request){
        return electricalService.save(request);
    }

}
