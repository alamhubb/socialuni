package com.socialuni.social.common.sdk.dao.repository;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;

import javax.persistence.EntityManager;

/**
 * @author wulinghui
 * @version 1.0
 * @module Socialuni
 * @date 2022/9/29 10:48
 * @since 1.0
 */
public interface DelegateExtendBaseFactory {
    ExtendBaseRepository newExtendBaseRepository(JpaEntityInformation entityInformation, EntityManager entityManager);
}
