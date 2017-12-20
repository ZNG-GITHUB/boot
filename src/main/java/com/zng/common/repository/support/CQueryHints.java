package com.zng.common.repository.support;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/**
 * Created By Zng on 2017/12/20
 */
public interface CQueryHints extends Iterable<Map.Entry<String, Object>> {
    /**
     * Creates and returns a new {@link org.springframework.data.jpa.repository.support.QueryHints} instance including {@link javax.persistence.EntityGraph}.
     *
     * @param em must not be {@literal null}.
     * @return new instance of {@link org.springframework.data.jpa.repository.support.QueryHints}.
     */
    CQueryHints withFetchGraphs(EntityManager em);

    /**
     * Get the query hints as a {@link Map}.
     *
     * @return never {@literal null}.
     */
    Map<String, Object> asMap();

    /**
     * Null object implementation of {@link org.springframework.data.jpa.repository.support.QueryHints}.
     *
     * @author Oliver Gierke
     * @since 2.0
     */
    static enum NoHints implements CQueryHints {

        INSTANCE;

        /*
         * (non-Javadoc)
         * @see org.springframework.data.jpa.repository.support.QueryHints#asMap()
         */
        @Override
        public Map<String, Object> asMap() {
            return Collections.emptyMap();
        }

        /*
         * (non-Javadoc)
         * @see java.lang.Iterable#iterator()
         */
        @Override
        public Iterator<Map.Entry<String, Object>> iterator() {
            return Collections.emptyIterator();
        }

        /*
         * (non-Javadoc)
         * @see org.springframework.data.jpa.repository.support.QueryHints#withFetchGraphs(javax.persistence.EntityManager)
         */
        @Override
        public CQueryHints withFetchGraphs(EntityManager em) {
            return this;
        }
    }
}
