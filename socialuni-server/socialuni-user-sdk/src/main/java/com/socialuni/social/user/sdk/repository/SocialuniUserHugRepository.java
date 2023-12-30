package com.socialuni.social.user.sdk.repository;


import com.socialuni.social.user.sdk.model.DO.SocialuniUserHugDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SocialuniUserHugRepository extends JpaRepository<SocialuniUserHugDo, Integer> {

    //按赞的数量排序

    @Query(nativeQuery = true, value = "select s.user_id from s_user_hug s order by s.hug_num desc,s.updateTime desc ")
    List<Integer> findUserIdsOrderByHugNum();
}

