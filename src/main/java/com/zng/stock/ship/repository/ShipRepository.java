package com.zng.stock.ship.repository;

import com.zng.common.repository.InitRepository;
import com.zng.stock.ship.entity.Ship;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by John.Zhang on 2018/5/4.
 */
@Repository
public interface ShipRepository extends InitRepository<Ship,Long>{

    @Query("select s from #{#entityName} s where s.project.id = ?1 and s.isDeleted = 0")
    List<Ship> findByProjectId(Long projectId);

    @Query("select s from #{#entityName} s where s.project.id = ?1 and s.shipNo like %?2% and s.isDeleted = 0")
    List<Ship> findByProjectIdAndNo(Long projectId, String shipNo);
}
