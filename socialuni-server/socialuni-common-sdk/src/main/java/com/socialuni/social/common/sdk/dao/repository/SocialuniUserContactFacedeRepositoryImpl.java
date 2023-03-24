package com.socialuni.social.common.sdk.dao.repository;

import com.socialuni.social.common.api.entity.SocialuniUserContactBaseDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

public class SocialuniUserContactFacedeRepositoryImpl<T extends SocialuniUserContactBaseDO> {
    private final SocialuniUserContactFacedeRepository<T> userRepository;

    public SocialuniUserContactFacedeRepositoryImpl(EntityManager entityManager, Class<T> tClass) {
        JpaRepositoryFactory jpaRepositoryFactory = new JpaRepositoryFactory(entityManager);
        this.userRepository = jpaRepositoryFactory.getRepository(SocialuniUserContactFacedeRepository.class);
    }

    public SocialuniUserContactFacedeRepository<T> getUserRepository() {
        return userRepository;
    }

    // your custom methods here
}