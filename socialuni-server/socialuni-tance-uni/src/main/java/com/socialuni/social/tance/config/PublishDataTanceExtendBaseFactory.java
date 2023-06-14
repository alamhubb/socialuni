package com.socialuni.social.tance.config;

import com.socialuni.social.common.sdk.dao.repository.DelegateExtendBaseFactory;
import com.socialuni.social.common.sdk.dao.repository.ExtendBaseRepository;
import com.socialuni.social.tance.repository.PublishDataTanceBaseRepository;
import org.springframework.context.annotation.Primary;
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
@Primary
@Component
public class PublishDataTanceExtendBaseFactory implements DelegateExtendBaseFactory {
    @Override
    public ExtendBaseRepository newExtendBaseRepository(JpaEntityInformation entityInformation, EntityManager entityManager) {
        return new PublishDataTanceBaseRepository(entityInformation,entityManager);
    }
}
