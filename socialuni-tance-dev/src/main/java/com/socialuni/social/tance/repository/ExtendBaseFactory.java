package com.socialuni.social.tance.repository;

import com.socialuni.social.common.repository.DelegateExtendBaseFactory;
import com.socialuni.social.common.repository.ExtendBaseRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

/**
 * @author wulinghui
 * @version 1.0
 * @module Socialuni
 * @date 2022/9/29 10:50
 * @since 1.0
 */
@Component
public class ExtendBaseFactory implements DelegateExtendBaseFactory {
    @Override
    public ExtendBaseRepository newExtendBaseRepository(JpaEntityInformation entityInformation, EntityManager entityManager) {
        return new ExtendBaseRepository(entityInformation,entityManager);
    }
}
