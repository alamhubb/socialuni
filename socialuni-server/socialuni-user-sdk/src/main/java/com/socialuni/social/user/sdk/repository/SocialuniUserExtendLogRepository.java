package com.socialuni.social.user.sdk.repository;

import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserExpandDo;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserExtendFriendLogDo;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SocialuniUserExtendLogRepository extends JpaRepository<SocialuniUserExtendFriendLogDo, Integer> {
    @Query("select s.userId from SocialuniUserExtendFriendLogDo s where s.adCode like concat(:adCode, '%') group by s.userId order by s.id desc ")
    List<Integer> findUserIdsByLickAdCode(String adCode);

}

