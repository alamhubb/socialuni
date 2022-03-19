package com.socialuni.social.sdk.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface TalkMapper {
    List<Integer> queryTalkIdsByCom(
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
            @Param("circleId") Integer circleId
    );

    List<Integer> queryMineTalkIdsByCom(
            @Param("userId") Integer userId,
            @Param("status") List<String> statusList,
            @Param("circleId") Integer circleId);
}
