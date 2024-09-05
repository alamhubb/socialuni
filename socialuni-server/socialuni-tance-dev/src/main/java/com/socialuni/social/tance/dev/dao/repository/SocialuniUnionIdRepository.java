package com.socialuni.social.tance.dev.dao.repository;


import com.socialuni.social.tance.dev.entity.SocialuniUnionIdDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SocialuniUnionIdRepository extends JpaRepository<SocialuniUnionIdDo, Integer> {
    SocialuniUnionIdDo findByUuid(String unionId);

    SocialuniUnionIdDo findOneById(Integer id);

    SocialuniUnionIdDo findFirstByUnionId(Long id);
    SocialuniUnionIdDo findFirstById(Integer id);

    @Query(value = "select unionId from SocialuniUnionIdDo where contentType=:contentType")
    List<Long> findAllUnionIdByContentType(String contentType);

    @Query(value = "select id from SocialuniUnionIdDo where contentType=:contentType")
    List<Long> findAllUnionIdsByContentType(String contentType);
}

