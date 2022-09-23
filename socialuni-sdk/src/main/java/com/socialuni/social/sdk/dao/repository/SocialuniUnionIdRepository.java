package com.socialuni.social.sdk.dao.repository;


import com.socialuni.social.sdk.dao.DO.SocialuniUnionIdDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialuniUnionIdRepository extends JpaRepository<SocialuniUnionIdDO, Integer> {
    SocialuniUnionIdDO findByUuid(String unionId);

    SocialuniUnionIdDO findOneById(Integer id);

}

