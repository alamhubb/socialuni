package com.socialuni.social.common.api.constant;

/**
 * redisTemplate封装
 *
 */
public class CommonRedisKey {
    public static final String userById = "userById";
    public static final String findUserIdsByStatus = "findUserIdsByStatus";
    public static final String findUserIdsByType = "findUserIdsByType";
    public static final String findAllUserId = "findAllUserId";
    public static final String tokenByToken = "tokenByToken";
    //系统
    public static final String shell = "shell";


    public static final String queryMineTalkIds = "queryMineTalkIds";
    public static final String queryMineTalkIdsByCom = "queryMineTalkIdsByCom";
    public static final String queryUserTalkIds = "queryUserTalkIds";
    public static final String queryUserFollowsTalkIds = "queryUserFollowsTalkIds";


    public static final String queryTalkIdsByAndUser = "queryTalkIdsByAndUser";
    public static final String queryTalkIdsByTalkCondition = "queryTalkIdsByTalkCondition";
    public static final String queryTalkIdsByAndTag = "queryTalkIdsByAndTag";
    public static final String queryTalkIdsByAndCircle = "queryTalkIdsByAndCircle";
    public static final String queryTalkIdsByAndUserExpand = "queryTalkIdsByAndUserExpand";



    public static final String queryTalkIdsByTab = "queryTalkIdsByTab";
    public static final String queryTalkIdsByTagVisibleGender = "queryTalkIdsByTagVisibleGender";
    public static final String queryTalkIdsByTagIdsAndTagVisibleGender = "queryTalkIdsByTagIdsAndTagVisibleGender";
//    public static final String queryTalkIdsByAdCodeAndGender = "queryTalkIdsByAdCodeAndGender";
    public static final String queryTalkIdsByGenderAndAgeAndAdCodeAndGender = "queryTalkIdsByGenderAndAgeAndAdCodeAndGender";

    public static final String queryUserFollowUserIds = "queryUserFollowUserIds";
    //关注关系
    public static final String queryUserAndBeUserFollow = "queryUserAndBeUserFollow";
    //抱抱
    public static final String findHugByTalkIdAndUserId = "findHugByTalkIdAndUserId";
    public static final String findUserPhoneByUserId = "findUserPhoneByUserId";
    public static final String findByPhoneNumAndDevId = "findByPhoneNumAndDevId";
    public static final String findUserFansDetailByUserId = "findUserFansDetailByUserId";
    public static final String talkById = "talkById";


    public static final String commonFindByUserIdAndBeUserId = "commonFindByUserIdAndBeUserId";

}