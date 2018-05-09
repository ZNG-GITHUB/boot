package com.zng.stock.product.controller;

import com.zng.common.entity.CommonTableRequest;
import com.zng.common.entity.ResponseModel;
import com.zng.stock.product.service.ProductService;
import com.zng.stock.product.view.ProductSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("table")
    public ResponseModel table(@RequestBody CommonTableRequest request){
        return productService.table(request);
    }

    @PostMapping("byshipId")
    public ResponseModel getByShipId(@RequestBody ProductSearchRequest request){
        return productService.getByShipId(request);
    }

}
