package com.socialuni.social.tance.dev.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SocialuniUnionIdRepository extends JpaRepository<SocialuniUnionIdDo, Integer> {
    SocialuniUnionIdDo findByUuid(String unionId);

    SocialuniUnionIdDo findOneById(Integer id);

    SocialuniUnionIdDo findFirstByUnionId(Long id);

    @Query(value = "select uuid from SocialuniUnionIdDo where contentType=:contentType")
    List<String> findAllUuidByContentType(String contentType);

    @Query(value = "select id from SocialuniUnionIdDo where contentType=:contentType")
    List<Long> findAllUnionIdsByContentType(String contentType);
}

