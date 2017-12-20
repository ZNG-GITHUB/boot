package com.zng.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Created by John.Zhang on 2017/12/20.
 */
public class MyRepositoryFactoryBean <R extends JpaRepository<T, I>, T, I extends Serializable>
        extends JpaRepositoryFactoryBean<R,T,I> {

    /**
     * Creates a new {@link JpaRepositoryFactoryBean} for the given repository interface.
     *
     * @param repositoryInterface must not be {@literal null}.
     */
    public MyRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    @SuppressWarnings("rawtypes")
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager em) {
        return new CustomRepositoryFactory(em);
    }

    private static class CustomRepositoryFactory<T, I extends Serializable>
            extends JpaRepositoryFactory {

        private final EntityManager em;

        public CustomRepositoryFactory(EntityManager em) {
            super(em);
            this.em = em;
        }

        @SuppressWarnings("unchecked")
        protected Object getTargetRepository(RepositoryMetadata metadata) {
            return new InitRepositoryImpl<T,I>((Class<T>) metadata.getDomainType(), em);
        }

        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return InitRepositoryImpl.class;
        }
    }
}
