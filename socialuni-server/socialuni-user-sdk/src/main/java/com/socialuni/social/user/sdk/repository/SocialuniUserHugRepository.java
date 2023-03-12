package com.socialuni.social.user.sdk.repository;


import com.socialuni.social.user.sdk.model.DO.SocialuniUserHugDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SocialuniUserHugRepository extends JpaRepository<SocialuniUserHugDo, Integer> {

    //按赞的数量排序

    @Query("select s.userId from SocialuniUserHugDo s order by s.hugNum desc,s.updateTime desc ")
    List<Integer> findUserIdsOrderByHugNum();
}

