package com.zng.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by John.Zhang on 2017/12/19.
 */
@NoRepositoryBean
public interface InitRepository<T,ID extends Serializable>
        extends JpaRepository<T,ID>,JpaSpecificationExecutor<T> {

    @Query("select en from #{#entityName} en where en.isDeleted = 0")
    List<T> findAllSoftly();

}
