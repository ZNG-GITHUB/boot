package com.zng.common.repository.support;

/**
 * Created By Zng on 2017/12/20
 */
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.query.Jpa21Utils;
import org.springframework.data.jpa.repository.query.JpaEntityGraph;
import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.util.Optionals;
import org.springframework.util.Assert;

/**
 * Default implementation of {@link CQueryHints}.
 *
 * @author Christoph Strobl
 * @author Oliver Gierke
 * @since 2.0
 */
public class CustomQueryHints implements CQueryHints {

    private final JpaEntityInformation<?, ?> information;
    private final CrudMethodMetadata metadata;
    private final Optional<EntityManager> entityManager;

    /**
     * Creates a new {@link org.springframework.data.jpa.repository.support.DefaultQueryHints} instance for the given {@link JpaEntityInformation},
     * {@link CrudMethodMetadata}, {@link EntityManager} and whether to include fetch graphs.
     *
     * @param information must not be {@literal null}.
     * @param metadata must not be {@literal null}.
     * @param entityManager must not be {@literal null}.
     */
    private CustomQueryHints(JpaEntityInformation<?, ?> information, CrudMethodMetadata metadata,
                              Optional<EntityManager> entityManager) {

        this.information = information;
        this.metadata = metadata;
        this.entityManager = entityManager;
    }

    /**
     * Creates a new {@link CQueryHints} instance for the given {@link JpaEntityInformation}, {@link CrudMethodMetadata}
     * and {@link EntityManager}.
     *
     * @param information must not be {@literal null}.
     * @param metadata must not be {@literal null}.
     * @return
     */
    public static CQueryHints of(JpaEntityInformation<?, ?> information, CrudMethodMetadata metadata) {

        Assert.notNull(information, "JpaEntityInformation must not be null!");
        Assert.notNull(metadata, "CrudMethodMetadata must not be null!");

        return new CustomQueryHints(information, metadata, Optional.empty());
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.jpa.repository.support.QueryHints#withFetchGraphs()
     */
    @Override
    public CQueryHints withFetchGraphs(EntityManager em) {
        return new CustomQueryHints(this.information, this.metadata, Optional.of(em));
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Iterable#iterator()
     */
    @Override
    public Iterator<Entry<String, Object>> iterator() {
        return asMap().entrySet().iterator();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.jpa.repository.support.QueryHints#asMap()
     */
    @Override
    public Map<String, Object> asMap() {

        Map<String, Object> hints = new HashMap<>();

        hints.putAll(metadata.getQueryHints());
        hints.putAll(getFetchGraphs());

        return hints;
    }

    private Map<String, Object> getFetchGraphs() {

        return Optionals
                .mapIfAllPresent(entityManager, metadata.getEntityGraph(),
                        (em, graph) -> Jpa21Utils.tryGetFetchGraphHints(em, getEntityGraph(graph), information.getJavaType()))
                .orElse(Collections.emptyMap());
    }

    private JpaEntityGraph getEntityGraph(EntityGraph entityGraph) {

        String fallbackName = information.getEntityName() + "." + metadata.getMethod().getName();
        return new JpaEntityGraph(entityGraph, fallbackName);
    }
}
