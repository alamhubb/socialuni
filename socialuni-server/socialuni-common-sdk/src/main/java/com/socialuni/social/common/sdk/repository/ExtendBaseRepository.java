package com.socialuni.social.common.sdk.repository;

import org.springframework.data.jpa.provider.PersistenceProvider;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * @author wulinghui
 * @version 1.0
 * @module api-factory
 * @date 2022/9/1 9:55
 * @since 1.0
 */
public class ExtendBaseRepository<T, ID >
        extends SimpleJpaRepository<T, ID> implements ExtendRepository<T, ID> {
    public static final String NO_WHERE = " where 1=1 ";
    protected final EntityManager entityManager;
    protected final JpaEntityInformation entityInformation;
    private volatile PersistenceProvider provider;
    public ExtendBaseRepository(JpaEntityInformation entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        // Keep the EntityManager around to used from the newly introduced methods.
        this.entityManager = entityManager;
        this.entityInformation = entityInformation;
    }

    @Transactional
    @Override
    public boolean incr(ID id, String columnName, Long count) {
        String entityName = entityInformation.getEntityName();
        String idName = entityInformation.getIdAttribute().getName();
        String sql = String.format("update %s set %s = %s + %s where %s = %s",entityName,columnName,columnName,count,idName,id);
        sql = decorateSql(sql);
        Query nativeQuery = entityManager.createQuery(sql);
//        Query nativeQuery = entityManager.createNativeQuery(sql); 实体名，不是表名。
        return nativeQuery.executeUpdate() == 1;
    }

    /**
     * 采用懒加载。
     * @return
     */
    public PersistenceProvider getProvider() {
        if(provider == null){
            synchronized (this){
                if(provider == null){
                    this.provider = PersistenceProvider.fromEntityManager(entityManager);
                }
            }
        }
        return provider;
    }

    /**
     * 修饰sql,如添加
     * @param sql
     * @return
     */
    protected String decorateSql(String sql){
        return sql;
    }

    /**
     * 修饰target对象。
     * @param target
     * @return target 被修饰后的对象。
     */
    protected Object decorateTarget(Object target){
        return target;
    }
}