package com.socialuni.social.tance.dev.repository;

import cn.hutool.core.util.ObjectUtil;
import com.socialuni.social.common.sdk.dao.repository.ExtendBaseRepository;
import com.socialuni.social.common.sdk.dao.repository.SuperInfo;
import com.socialuni.social.tance.dev.model.TanceRequestModel;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Optional;

import static org.springframework.data.jpa.repository.query.QueryUtils.*;

/**
 * @author wulinghui
 * @version 1.0
 * @module api-factory
 * @date 2022/9/1 9:55
 * @since 1.0
 */
public class TanceBaseRepository<T, ID >
        extends ExtendBaseRepository<T, ID> {

    public TanceBaseRepository(JpaEntityInformation entityInformation, EntityManager entityManager) {
//        super(entityInformation,new TanceEntityManager( entityManager)); // 废弃。
        super(entityInformation,entityManager);
    }






    /**
     * 修饰sql,如添加
     * @param sql
     * @return
     */
    @Override
    protected String decorateSql(String sql){
        if(TanceRequestModel.enable()){
            return String.format("%s and %s = %s",sql, SuperInfo.TANCE_COMMON_NAME,TanceRequestModel.tanceId());
        }else{
            return sql;
        }
    }

    /**
     * 修饰target对象。
     * @param target
     * @return target 被修饰后的对象。
     */
    @Override
    protected Object decorateTarget(Object target){
        decorateObject(target);
        return target;
    }

    /**
     * 修改对象,设置tanceId
     * @param target
     */
    public static void decorateObject(Object target){
        if(TanceRequestModel.enable()){
            if(target instanceof SuperInfo){
                SuperInfo superInfo = (SuperInfo)  target;
                Long tanceId = TanceRequestModel.tanceId();
                superInfo.setTanceId(tanceId);
            }
        }
    }
    /** 判断租户ID和当前的租户是否一致。
     * @return true 符合规矩。
     */
    public boolean judgeOk(T target){
        if(TanceRequestModel.enable()){
            if(target instanceof SuperInfo){
                SuperInfo superInfo = (SuperInfo)  target;
                Long curTanceId = TanceRequestModel.tanceId();
                if(ObjectUtil.notEqual(curTanceId, superInfo.getTanceId())){
                    return false;
                }
            }
            return true;
        }else {
            return true;
        }
    }

    /** 不满足租户规则，就返回null
     * @see #judgeOk(Object)
     * @param target
     * @return
     */
    public T map(T target){
        if(judgeOk(target)){
            return target;
        }else{
            return null;
        }
    }
    /**
     * 校验抛异常。
     * @see SuperInfo
     * @see #judgeOk(Object)
     * @return
     */
    public void validOk(T target){
        if( !judgeOk(target) ){
            throw new RuntimeException("该数据不属于当前租户");
        }
    }

    private String getDeleteAllQueryString() {
        return this.decorateSql( getQueryString(DELETE_ALL_QUERY_STRING, entityInformation.getEntityName()) + NO_WHERE );
    }

    private String getCountQueryString() {
        String countQuery = String.format(COUNT_QUERY_STRING, getProvider().getCountQueryPlaceholder(), "%s");
        return this.decorateSql( getQueryString(countQuery, entityInformation.getEntityName()) + NO_WHERE );
    }

    //////////////////////////////////////////////
    ///////////         Override
    /////////////////////////////////////////////

    @Override
    public Optional<T> findById(ID id) {
        Optional<T> byId = super.findById(id);
        return byId.map(this::map);
    }

    @Override
    public void delete(T entity) {
        this.decorateTarget(entity);
        super.delete(entity);
    }
    @Override
    @Transactional
    public void deleteAllByIdInBatch(Iterable<ID> ids) {

        Assert.notNull(ids, "Ids must not be null!");

        if (!ids.iterator().hasNext()) {
            return;
        }

        String queryString = String.format(this.decorateSql(DELETE_ALL_QUERY_BY_ID_STRING), entityInformation.getEntityName(),
                entityInformation.getIdAttribute().getName());

        Query query = this.entityManager.createQuery(queryString);
        query.setParameter("ids", ids);

        query.executeUpdate();
    }
    @Override
    @Transactional
    public void deleteAllInBatch(Iterable<T> entities) {
        entities.forEach(this::decorateTarget);
        super.deleteAllInBatch(entities);
    }
    @Override
    @Transactional
    public void deleteAllInBatch() {
        entityManager.createQuery(getDeleteAllQueryString()).executeUpdate();
    }
    //
    @Override
    public T getOne(ID id) {
        T one = super.getOne(id);
        return map(one);
    }
    @Override
    public T getById(ID id) {
        T one = super.getById(id);
        return map(one);
    }
    @Override
    protected <S extends T> TypedQuery<S> getQuery(@Nullable Specification<S> spec, Class<S> domainClass, Sort sort) {
//        if (ClassUtil.isAssignable(SuperInfo.class,domainClass)) {
//            System.out.println("1111111");
//        }
        return super.getQuery(new TanceSpecification(spec),domainClass,sort);
    }
    @Override
    protected <S extends T> TypedQuery<Long> getCountQuery(@Nullable Specification<S> spec, Class<S> domainClass) {
        return super.getCountQuery(new TanceSpecification(spec),domainClass);
    }

    @Override
    public <S extends T> boolean exists(Example<S> example) {
        this.decorateTarget(example.getProbe());
        return super.exists(example);
    }
    @Override
    public long count() {
        return entityManager.createQuery(getCountQueryString(), Long.class).getSingleResult();
    }
    @Transactional
    @Override
    public <S extends T> S save(S entity) {
       this.decorateTarget(entity);
       return super.save(entity);
    }
}