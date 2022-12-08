package com.socialuni.social.tance.repository;


import com.socialuni.social.tance.entity.SocialuniUnionIdDo;
import com.socialuni.social.tance.sdk.model.SocialuniUnionIdModler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SocialuniUnionIdRepository extends JpaRepository<SocialuniUnionIdDo, Integer> {
    SocialuniUnionIdDo findByUuid(String unionId);

    SocialuniUnionIdDo findOneById(Integer id);

    @Query(value = "select uuid from SocialuniUnionIdDo where contentType=:contentType")
    List<String> findAllUuidByContentType(String contentType);
}

