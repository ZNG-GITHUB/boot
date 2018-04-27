package com.zng.stock.product.service.impl;

import com.zng.common.entity.ResponseModel;
import com.zng.stock.product.entity.Product;
import com.zng.stock.product.respository.ProductRespository;
import com.zng.stock.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRespository productRespository;

    @Override
    public ResponseModel table() {
        List<Product> list = productRespository.findAll();
        return new ResponseModel(list);
    }
}
