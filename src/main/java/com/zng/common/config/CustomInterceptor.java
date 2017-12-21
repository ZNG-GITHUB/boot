package com.zng.common.config;

import com.zng.common.entity.CommonEntity;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * Created by John.Zhang on 2017/12/21.
 */
public class CustomInterceptor extends EmptyInterceptor {
    @Override
    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {

        if(entity instanceof CommonEntity){
            CommonEntity commonEntity = (CommonEntity)entity;
            commonEntity.setDeleted(false);
            super.onFlushDirty(commonEntity,id,state,state,propertyNames,types);
        }else {
            super.onDelete(entity, id, state, propertyNames, types);
        }
    }
}
