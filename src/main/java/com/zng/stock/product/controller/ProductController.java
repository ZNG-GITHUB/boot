package com.zng.stock.product.controller;

import com.zng.common.entity.ResponseModel;
import com.zng.stock.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("table")
    public ResponseModel table(){
        return productService.table();
    }

}
