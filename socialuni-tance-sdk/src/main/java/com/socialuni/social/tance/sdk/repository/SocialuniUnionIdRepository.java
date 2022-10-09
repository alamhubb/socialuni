package com.socialuni.social.tance.sdk.repository;


import com.socialuni.social.tance.sdk.model.SocialuniUnionIdDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialuniUnionIdRepository extends JpaRepository<SocialuniUnionIdDO, Integer> {
    SocialuniUnionIdDO findByUuid(String unionId);

    SocialuniUnionIdDO findOneById(Integer id);
}

