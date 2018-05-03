package com.zng.stock.project.respository;

import com.zng.common.repository.InitRepository;
import com.zng.stock.project.entity.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by John.Zhang on 2018/5/3.
 */
@Repository
public interface ProjectRespository extends InitRepository<Project,Long> {

    @Query("select p from #{#entityName} p where p.shipyard.id = ?1 and p.isDeleted = 0")
    List<Project> findByShipyardId(Long shipyardId);
}
