package com.zng.stock.product.service;

import com.zng.common.entity.CommonTableRequest;
import com.zng.common.entity.ResponseModel;
import com.zng.stock.product.view.ProductSaveRequest;
import com.zng.stock.product.view.ProductSearchRequest;

import javax.validation.constraints.NotNull;

public interface ProductService {
    ResponseModel table(CommonTableRequest request);

    ResponseModel getByShipId(ProductSearchRequest request);

    ResponseModel save(ProductSaveRequest request);

    ResponseModel info(@NotNull Long id);

    ResponseModel delete(@NotNull Long id);
}
