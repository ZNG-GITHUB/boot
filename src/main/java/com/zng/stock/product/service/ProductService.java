package com.zng.stock.product.service;

import com.zng.common.entity.CommonTableRequest;
import com.zng.common.entity.ResponseModel;
import com.zng.stock.product.view.ProductSearchRequest;

public interface ProductService {
    ResponseModel table(CommonTableRequest request);

    ResponseModel getByShipId(ProductSearchRequest request);
}
