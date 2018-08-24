package com.zng.system.menu.repository;

import com.zng.common.repository.InitRepository;
import com.zng.system.menu.entity.Menu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author John.Zhang
 * @version 1.0
 * @date 2018/8/24
 */
@Repository
public interface MenuRepository extends InitRepository<Menu,Long> {

    @Query("select en from #{#entityName} en where en.isDeleted = 0 and en.isHide = 0")
    List<Menu> findShowMenuAllSoftly();

}
