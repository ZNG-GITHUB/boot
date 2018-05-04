package com.zng.stock.project.service;

import com.zng.common.entity.ResponseModel;
import com.zng.stock.project.view.ProjectSearchRequest;

/**
 * Created by John.Zhang on 2018/5/3.
 */
public interface ProjectService {
    ResponseModel getByShipyardId(ProjectSearchRequest shipyardId);
}
