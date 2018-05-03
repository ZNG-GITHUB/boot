package com.zng.stock.project.service.impl;

import com.zng.common.entity.ResponseModel;
import com.zng.stock.project.dto.ProjectNameDto;
import com.zng.stock.project.entity.Project;
import com.zng.stock.project.respository.ProjectRespository;
import com.zng.stock.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John.Zhang on 2018/5/3.
 */
@Service
public class ProjectServiceImpl implements ProjectService {


    @Autowired
    private ProjectRespository projectRespository;

    @Override
    public ResponseModel getByShipyardId(Long shipyardId) {

        List<Project> projects  = projectRespository.findByShipyardId(shipyardId);

        List<ProjectNameDto> dtos = new ArrayList<>();
        for(Project project : projects){
            ProjectNameDto dto = new ProjectNameDto();
            dto.setId(project.getId());
            dto.setName(project.getProjectName());
            dtos.add(dto);
        }

        return new ResponseModel(dtos);
    }
}
