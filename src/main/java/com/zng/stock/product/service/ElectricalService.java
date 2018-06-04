package com.zng.stock.product.service;

import com.zng.common.entity.CommonTableRequest;
import com.zng.common.entity.ResponseModel;

public interface ElectricalService {

    ResponseModel purchaseTable(CommonTableRequest request);
}
