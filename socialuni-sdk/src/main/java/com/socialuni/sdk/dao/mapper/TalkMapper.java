package com.socialuni.sdk.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface TalkMapper {
    List<Integer> queryTalkIdsByAndUser(
            @Param("talkUserGender") String talkUserGender,
            @Param("minAge") Integer minAge,
            @Param("maxAge") Integer maxAge,
            @Param("status") String status,
            @Param("adCode") String adCode,
            @Param("talkVisibleGender") String talkVisibleGender,
            @Param("mineUserGender") String mineUserGender,
            @Param("devId") Integer devId,
            @Param("queryTime") Date queryTime,
//            @Param("mineUserId") Integer mineUserId,
//            @Param("mineTalkStatus") List<String> mineTalkStatus,
            @Param("hasPeopleImgTalkNeedIdentity") Boolean hasPeopleImgTalkNeedIdentity
    );

    List<Integer> queryTalkIdsByAndTag(@Param("tagIds") List<Integer> tagIds);

    List<Integer> queryTalkIdsByAndCircle(@Param("circleId") Integer circleId);

    List<Integer> queryTalkIdsByAndUserExpand();

    List<Integer> queryMineTalkIdsByCom(
            @Param("userId") Integer userId,
            @Param("status") List<String> statusList);


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
