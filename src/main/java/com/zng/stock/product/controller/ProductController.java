package com.zng.stock.product.controller;

import com.zng.common.entity.CommonTableRequest;
import com.zng.common.entity.ResponseModel;
import com.zng.stock.product.service.ProductService;
import com.zng.stock.product.view.ProductSaveRequest;
import com.zng.stock.product.view.ProductSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

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

    @PostMapping("save")
    public ResponseModel save(@RequestBody ProductSaveRequest request){
        return productService.save(request);
    }

    @PostMapping("info/{id}")
    public ResponseModel info(@PathVariable @NotNull Long id){
        return productService.info(id);
    }

    @PostMapping("del/{id}")
    public ResponseModel delete(@PathVariable @NotNull Long id){
        return productService.delete(id);
    }

}
