package com.zng.stock.ship.service.impl;

import com.zng.common.entity.ResponseModel;
import com.zng.common.entity.TreeNode;
import com.zng.common.util.TreeUtil;
import com.zng.stock.project.entity.Project;
import com.zng.stock.project.repository.ProjectRepository;
import com.zng.stock.ship.dto.ShipNameDto;
import com.zng.stock.ship.entity.Ship;
import com.zng.stock.ship.repository.ShipRepository;
import com.zng.stock.ship.service.ShipService;
import com.zng.stock.ship.view.ShipSearchRequest;
import com.zng.stock.shipyard.entity.Shipyard;
import com.zng.stock.shipyard.repository.ShipyardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John.Zhang on 2018/5/4.
 */
@Service
public class ShipServiceImpl implements ShipService {

    @Autowired
    private ShipRepository shipRepository;
    @Autowired
    private ShipyardRepository shipyardRepository;
    @Autowired
    private ProjectRepository projectRepository;

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

    @Override
    public ResponseModel tree() {
        List<TreeNode> shipyardNodes = shipyardRepository.findTreeNodes();
        List<TreeNode> projectNodes = projectRepository.findTreeNodes();
        List<TreeNode> shipNodes = shipRepository.findTreeNodes();

        for(TreeNode node : projectNodes){
            List<TreeNode> children = getChildNodes(node.getValue(),shipNodes);
            if(!CollectionUtils.isEmpty(children)){
                node.setChildren(children);
            }
        }

        for(TreeNode node : shipyardNodes){
            List<TreeNode> children = getChildNodes(node.getValue(),projectNodes);
            if(!CollectionUtils.isEmpty(children)){
                node.setChildren(children);
            }
        }

        return new ResponseModel(TreeUtil.buildTree(shipyardNodes));
    }

    private List<TreeNode> getChildNodes(Long key,List<TreeNode> nodes) {
        List<TreeNode> list = new ArrayList<>();
        for(TreeNode node : nodes){
            if(node.getParentValue() != null && node.getParentValue().equals(key)){
                list.add(node);
            }
        }
        return list;
    }
}
