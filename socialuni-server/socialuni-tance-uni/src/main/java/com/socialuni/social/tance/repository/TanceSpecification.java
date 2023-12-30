package com.socialuni.social.tance.repository;

import com.socialuni.social.common.sdk.dao.repository.SuperInfo;
import com.socialuni.social.tance.sdk.model.TanceRequestModel;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author wulinghui
 * @version 1.0
 * @module api-factory
 * @date 2022/9/6 11:02
 * @since 1.0
 */
public class TanceSpecification<T> implements Specification<T> {
    Specification<T> delegate;

    public TanceSpecification(Specification<T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate tancePredicate = null;
        if(TanceRequestModel.enable()){
            tancePredicate = criteriaBuilder.equal(root.get(SuperInfo.TANCE_FIELD_NAME), TanceRequestModel.tanceId());
        }
        Predicate predicate;
        if(delegate != null){
            predicate = delegate.toPredicate(root, query, criteriaBuilder);
            if(tancePredicate != null) predicate = criteriaBuilder.and(tancePredicate,predicate);
        }else{
            predicate = tancePredicate;
        }
        return predicate;
    }
}
