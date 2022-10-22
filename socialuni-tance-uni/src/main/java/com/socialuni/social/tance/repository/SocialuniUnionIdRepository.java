package com.socialuni.social.tance.repository;


import com.socialuni.social.tance.entity.SocialuniUnionIdDo;
import com.socialuni.social.tance.sdk.model.SocialuniUnionIdModler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialuniUnionIdRepository extends JpaRepository<SocialuniUnionIdDo, Integer> {
    SocialuniUnionIdModler findByUuid(String unionId);

    SocialuniUnionIdModler findOneById(Integer id);
}

