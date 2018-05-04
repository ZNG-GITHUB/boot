package com.zng.stock.ship.service;

import com.zng.common.entity.ResponseModel;
import com.zng.stock.ship.view.ShipSearchRequest;

/**
 * Created by John.Zhang on 2018/5/4.
 */
public interface ShipService {
    ResponseModel getByProjectId(ShipSearchRequest request);
}
