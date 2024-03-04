package com.socialuni.social.common.sdk.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface TalkImgMapper {
    @Cacheable(cacheNames = "getTalkImgIdsByTalkId", key = "#talkId")
    List<Integer> queryTalkImgIdsTop3ByTalkIdOrderByIdAsc(
            @Param("talkId") Integer talkId
    );
}
