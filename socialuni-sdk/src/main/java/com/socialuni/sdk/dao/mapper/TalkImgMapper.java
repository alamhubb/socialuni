package com.socialuni.sdk.dao.mapper;

import com.socialuni.sdk.dao.DO.talk.SocialTalkImgDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface TalkImgMapper {
    @Cacheable(cacheNames = "talkImgsTalkIdAndAuth", key = "#talkUid")
    List<SocialTalkImgDO> queryTalkImgsTop3ByTalkUidOrderByIdAsc(
            @Param("talkUid") Integer talkUid,
            @Param("hasPeopleImgTalkNeedIdentity") Boolean hasPeopleImgTalkNeedIdentity
    );
}
