package com.socialuni.social.community.sdk.dao.mapper;

import com.socialuni.social.common.api.constant.CommonRedisKey;
import com.socialuni.social.common.api.enumeration.PublishDataCacheable;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface TalkMapper {

//    @Cacheable(cacheNames = CommonRedisKey.queryTalkIdsByAndUser, key = "#talkUserGender+'-'+#status+'-'+#minAge+'-'+#maxAge+'-'+#disableUnderageContent")
    @PublishDataCacheable(cacheNames = CommonRedisKey.queryTalkIdsByAndUser, key = "#talkUserGender+'-'+#status+'-'+#minAge+'-'+#maxAge+'-'+#disableUnderageContent" , modelClassName = {"com.socialuni.social.community.sdk.dao.DO.SocialuniTalkDO"})
    List<Long> queryTalkIdsByAndUser(
            @Param("talkUserGender") String talkUserGender,
            @Param("minAge") Integer minAge,
            @Param("maxAge") Integer maxAge,
            @Param("status") String status,
            @Param("disableUnderageContent") Boolean disableUnderageContent
    );

    @Cacheable(cacheNames = CommonRedisKey.queryTalkIdsByTalkCondition, key = "#status+'-'+#adCode+'-'+#talkVisibleGender+'-'+#mineUserGender+'-'+#devId+'-'+#disableUnderageContent+'-'+#disableContentHasContactInfo+'-'+#disableContentHasQrCode")
    List<Long> queryTalkIdsByTalkCondition(
            @Param("testDevId") Integer testDevId,
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
    List<Long> queryTalkIdsByAndTag(@Param("tagIds") List<Integer> tagIds);
    @Cacheable(cacheNames = CommonRedisKey.queryTalkIdsByAndCircle, key = "#circleId")
    List<Long> queryTalkIdsByAndCircle(@Param("circleId") Integer circleId);

    @Cacheable(cacheNames = CommonRedisKey.queryTalkIdsByAndUserExpand)
    List<Long> queryTalkIdsByAndUserExpand();

//    List<Integer> queryMineTalkIdsByCom(@Param("userId") Long userId, @Param("statusList") List<String> statusList);


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
//            @Param("mineUserId") Long mineUserId,
//            @Param("mineTalkStatus") List<String> mineTalkStatus,
            @Param("circleId") Integer circleId,
            @Param("hasPeopleImgTalkNeedIdentity") Boolean hasPeopleImgTalkNeedIdentity
    );*/

}
