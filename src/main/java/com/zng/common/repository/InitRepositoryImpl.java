package com.zng.common.repository;

import com.zng.common.entity.CommonEntity;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Created by John.Zhang on 2017/12/20.
 */
public class InitRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements InitRepository<T, ID>{

    private static final String ID_MUST_NOT_BE_NULL = "The given id must not be null!";

    private final EntityManager entityManager;
    private @Nullable CrudMethodMetadata metadata;
    private final JpaEntityInformation<T, ?> entityInformation;

    public InitRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.entityInformation = entityInformation;
    }

    public InitRepositoryImpl(Class<T> domainClass, EntityManager em) {
        this(JpaEntityInformationSupport.getEntityInformation(domainClass, em), em);
    }


    @Override
    @Transactional
    public void deleteById(ID id) {
        Assert.notNull(id, ID_MUST_NOT_BE_NULL);

        delete(findById(id).orElseThrow(() -> new EmptyResultDataAccessException(
                String.format("No %s entity with id %s exists!", entityInformation.getJavaType(), id), 1)));
    }

    @Override
    @Transactional
    public void delete(T entity) {
        Assert.notNull(entity, "The entity must not be null!");
        if(entityManager.contains(entity)){
            if(entity instanceof CommonEntity){
                CommonEntity commonEntity = (CommonEntity) entity;
                commonEntity.setDeleted(true);
                entityManager.merge(commonEntity);
            }
        }
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<? extends T> entities) {
        Assert.notNull(entities, "The given Iterable of entities not be null!");

        for (T entity : entities) {
            delete(entity);
        }
    }

    @Override
    public void deleteInBatch(Iterable<T> entities) {
        super.deleteInBatch(entities);
    }

    @Override
    public void deleteAll() {
        super.deleteAll();
    }

    @Override
    public void deleteAllInBatch() {
        super.deleteAllInBatch();
    }

    @Override
    public Optional<T> findById(ID id) {
        return super.findById(id);
    }

    @Override
    public T getOne(ID id) {
        return super.getOne(id);
    }

    @Override
    public boolean existsById(ID id) {
        return super.existsById(id);
    }

    @Override
    public List<T> findAll() {
        return super.findAll();
    }

    @Override
    public List<T> findAllById(Iterable<ID> ids) {
        return super.findAllById(ids);
    }

    @Override
    public List<T> findAll(Sort sort) {
        return super.findAll(sort);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return super.findAll(pageable);
    }

    @Override
    public Optional<T> findOne(@Nullable Specification<T> spec) {
        return super.findOne(spec);
    }

    @Override
    public List<T> findAll(@Nullable Specification<T> spec) {
        return super.findAll(spec);
    }

    @Override
    public Page<T> findAll(@Nullable Specification<T> spec, Pageable pageable) {
        return super.findAll(spec, pageable);
    }

    @Override
    public List<T> findAll(@Nullable Specification<T> spec, Sort sort) {
        return super.findAll(spec, sort);
    }

    @Override
    public <S extends T> Optional<S> findOne(Example<S> example) {
        return super.findOne(example);
    }

    @Override
    public <S extends T> long count(Example<S> example) {
        return super.count(example);
    }

    @Override
    public <S extends T> boolean exists(Example<S> example) {
        return super.exists(example);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        return super.findAll(example);
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return super.findAll(example, sort);
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return super.findAll(example, pageable);
    }

    @Override
    public long count() {
        return super.count();
    }

    @Override
    public long count(@Nullable Specification<T> spec) {
        return super.count(spec);
    }

    @Override
    @Transactional
    public <S extends T> S save(S entity) {
        if (entityInformation.isNew(entity)) {
            entityManager.persist(entity);
            return entity;
        } else {
            return entityManager.merge(entity);
        }
    }

    @Override
    public <S extends T> S saveAndFlush(S entity) {
        return super.saveAndFlush(entity);
    }

    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        return super.saveAll(entities);
    }

    @Override
    public void flush() {
        super.flush();
    }

    @Override
    protected <S extends T> Page<S> readPage(TypedQuery<S> query, Class<S> domainClass, Pageable pageable, @Nullable Specification<S> spec) {
        return super.readPage(query, domainClass, pageable, spec);
    }

    @Override
    protected TypedQuery<T> getQuery(@Nullable Specification<T> spec, Pageable pageable) {
        return super.getQuery(spec, pageable);
    }

    @Override
    protected <S extends T> TypedQuery<S> getQuery(@Nullable Specification<S> spec, Class<S> domainClass, Pageable pageable) {
        return super.getQuery(spec, domainClass, pageable);
    }

    @Override
    protected TypedQuery<T> getQuery(@Nullable Specification<T> spec, Sort sort) {
        return super.getQuery(spec, sort);
    }

    @Override
    protected <S extends T> TypedQuery<S> getQuery(@Nullable Specification<S> spec, Class<S> domainClass, Sort sort) {
        return super.getQuery(spec, domainClass, sort);
    }

    @Override
    protected TypedQuery<Long> getCountQuery(@Nullable Specification<T> spec) {
        return super.getCountQuery(spec);
    }

    @Override
    protected <S extends T> TypedQuery<Long> getCountQuery(@Nullable Specification<S> spec, Class<S> domainClass) {
        return super.getCountQuery(spec, domainClass);
    }

    @Override
    public List<T> findAllSoftly() {
        return null;
    }
}
