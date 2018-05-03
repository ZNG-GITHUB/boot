package com.zng.stock.project.service;

import com.zng.common.entity.ResponseModel;

import javax.validation.constraints.NotNull;

/**
 * Created by John.Zhang on 2018/5/3.
 */
public interface ProjectService {
    ResponseModel getByShipyardId(Long shipyardId);
}
