package com.zng.stock.ship.service.impl;

import com.zng.common.entity.ResponseModel;
import com.zng.stock.ship.dto.ShipNameDto;
import com.zng.stock.ship.entity.Ship;
import com.zng.stock.ship.repository.ShipRepository;
import com.zng.stock.ship.service.ShipService;
import com.zng.stock.ship.view.ShipSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John.Zhang on 2018/5/4.
 */
@Service
public class ShipServiceImpl implements ShipService {

    @Autowired
    private ShipRepository shipRepository;

    @Override
    public ResponseModel getByProjectId(ShipSearchRequest request) {

        Long projectId = request.getProjectId();
        String shipNo = request.getShipNo();
        List<Ship> list = shipRepository.findByProjectIdAndNo(projectId,shipNo);

        List<ShipNameDto> dtos = new ArrayList<>();
        for(Ship ship : list){
            ShipNameDto dto = new ShipNameDto();
            dto.setId(ship.getId());
            dto.setName(ship.getShipName());
            dto.setNo(ship.getShipNo());
            dtos.add(dto);
        }

        return new ResponseModel(dtos);
    }
}
