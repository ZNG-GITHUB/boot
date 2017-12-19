package com.zng.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Created by John.Zhang on 2017/12/19.
 */
@NoRepositoryBean
public interface InitRepository<T,ID extends Serializable>
        extends JpaRepository<T,ID>,JpaSpecificationExecutor<T> {
}
