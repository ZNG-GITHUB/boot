package com.zng.stock.product.service;

import com.zng.common.entity.CommonTableRequest;
import com.zng.common.entity.ResponseModel;
import com.zng.stock.product.view.ElectricalSaveRequest;

public interface ElectricalService {

    ResponseModel purchaseTable(CommonTableRequest request, Long prodectId);

    ResponseModel save(ElectricalSaveRequest request);
}
