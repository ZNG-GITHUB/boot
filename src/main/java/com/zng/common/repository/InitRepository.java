package com.zng.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

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

    @Query("select en from #{#entityName} en where en.isDeleted = 0 and en.id in (?1)")
    List<T> findInListSoftly(List<ID> idList);

    @Query("select en from #{#entityName} en where en.isDeleted = 0 and en.id = ?1")
    T findByIdSoftly(ID id);

    @Modifying
    @Query("update #{#entityName} en set en.isDeleted = 1 where en.id = ?1")
    void deleteByIdSoftly(ID id);

    @Modifying
    @Query("update #{#entityName} en set en.isDeleted = 1 where en.id in (?1)")
    void deleteInListSoftly(List<ID> idList);

}
