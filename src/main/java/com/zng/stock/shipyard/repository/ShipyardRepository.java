package com.zng.stock.shipyard.repository;

import com.zng.common.entity.TreeNode;
import com.zng.common.repository.InitRepository;
import com.zng.stock.shipyard.entity.Shipyard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipyardRepository extends InitRepository<Shipyard,Long> {

    @Query("select new com.zng.common.entity.TreeNode(s.id,s.shipyardName) from #{#entityName} s where s.isDeleted = 0")
    List<TreeNode> findTreeNodes();
}
