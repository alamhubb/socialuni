package com.socialuni.social.sdk.dao.mapper;

import com.socialuni.social.sdk.dao.redis.redisKey.CommonRedisKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface TalkMapper {
    @Cacheable(cacheNames = CommonRedisKey.queryTalkIdsByAndUser, key = "#talkUserGender+'-'+#status+'-'+#minAge+'-'+#maxAge+'-'+#disableUnderageContent")
    List<Integer> queryTalkIdsByAndUser(
            @Param("talkUserGender") String talkUserGender,
            @Param("minAge") Integer minAge,
            @Param("maxAge") Integer maxAge,
            @Param("status") String status,
            @Param("disableUnderageContent") Boolean disableUnderageContent
    );

    @Cacheable(cacheNames = CommonRedisKey.queryTalkIdsByTalkCondition, key = "#status+'-'+#adCode+'-'+#talkVisibleGender+'-'+#mineUserGender+'-'+#devId+'-'+#disableUnderageContent+'-'+#disableContentHasContactInfo+'-'+#disableContentHasQrCode")
    List<Integer> queryTalkIdsByTalkCondition(
            @Param("status") String status,
            @Param("adCode") String adCode,
            @Param("talkVisibleGender") String talkVisibleGender,
            @Param("mineUserGender") String mineUserGender,
            @Param("devId") Integer devId,
            @Param("disableUnderageContent") Boolean disableUnderageContent,
            @Param("disableContentHasContactInfo") Boolean disableContentHasContactInfo,
            @Param("disableContentHasQrCode") Boolean disableContentHasQrCode);

    //mybatis 一个参数时自定义属性名不生效 所以使用list
    @Cacheable(cacheNames = CommonRedisKey.queryTalkIdsByAndTag, key = "#tagIds")
    List<Integer> queryTalkIdsByAndTag(@Param("tagIds") List<Integer> tagIds);
    @Cacheable(cacheNames = CommonRedisKey.queryTalkIdsByAndCircle, key = "#circleId")
    List<Integer> queryTalkIdsByAndCircle(@Param("circleId") Integer circleId);

    @Cacheable(cacheNames = CommonRedisKey.queryTalkIdsByAndUserExpand)
    List<Integer> queryTalkIdsByAndUserExpand();

//    List<Integer> queryMineTalkIdsByCom(@Param("userId") Integer userId, @Param("statusList") List<String> statusList);


    /*List<Integer> queryTalkIdsByCom(
            @Param("talkUserGender") String talkUserGender,
            @Param("minAge") Integer minAge,
            @Param("maxAge") Integer maxAge,
            @Param("status") String status,
            @Param("adCode") String adCode,
            @Param("talkVisibleGender") String talkVisibleGender,
            @Param("mineUserGender") String mineUserGender,
            @Param("tagIds") List<Integer> tagIds,
            @Param("devId") Integer devId,
//            @Param("queryTime") Date queryTime,
//            @Param("mineUserId") Integer mineUserId,
//            @Param("mineTalkStatus") List<String> mineTalkStatus,
            @Param("circleId") Integer circleId,
            @Param("hasPeopleImgTalkNeedIdentity") Boolean hasPeopleImgTalkNeedIdentity
    );*/

}
