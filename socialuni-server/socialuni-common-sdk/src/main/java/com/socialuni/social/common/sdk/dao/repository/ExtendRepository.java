package com.socialuni.social.common.sdk.dao.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 * JPA扩展仓库，基类。
 * 扩展一些公共，常用的方法。 比如某字段的自增。
 * @author wulinghui
 * @version 1.0
 * @module api-factory
 * @date 2022/9/1 9:49
 * @since 1.0
 */
//因为要公用，所以必须要通用，不能失去本身的Spring Data JPA给我们提供的默认方法，所有我们继承相关的Repository类
@NoRepositoryBean
public interface ExtendRepository<T, ID> extends PagingAndSortingRepository<T, ID>, QueryByExampleExecutor<T> {
    /**
     * 自增
     * @param id id
     * @param columnName 字段名
     * @param count 数量
     * @return
     */
    boolean incr(ID id,String columnName,Long count);

    boolean isNew(T entity);
}