package com.zng.stock.project.repository;

import com.zng.common.entity.TreeNode;
import com.zng.common.repository.InitRepository;
import com.zng.stock.project.entity.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by John.Zhang on 2018/5/3.
 */
@Repository
public interface ProjectRepository extends InitRepository<Project,Long> {

    @Query("select p from #{#entityName} p where p.shipyard.id = ?1 and p.isDeleted = 0")
    List<Project> findByShipyardId(Long shipyardId);

    @Query("select p from #{#entityName} p where p.shipyard.id = ?1 and p.projectName like %?2% and p.isDeleted = 0")
    List<Project> findByShipyardIdAndName(Long shipyardId, String projectName);

    @Query("select new com.zng.common.entity.TreeNode(p.id,p.projectName,p.shipyard.id) from #{#entityName} p where p.isDeleted = 0")
    List<TreeNode> findTreeNodes();
}
