package com.socialuni.social.sdk.redis;

/**
 * redisTemplate封装
 *
 */
public class RedisKeysConst {
    //系统
    public static final String springKey = "::";

    public static final String userById = "userById";
    //系统
    public static final String shell = "shell";


    public static final String queryMineTalkIds = "queryMineTalkIds";
    public static final String queryUserTalkIds = "queryUserTalkIds";
    public static final String queryUserFollowsTalkIds = "queryUserFollowsTalkIds";

    public static final String queryTalkIdsByTab = "queryTalkIdsByTab";
    public static final String queryTalkIdsByTagVisibleGender = "queryTalkIdsByTagVisibleGender";
    public static final String queryTalkIdsByTagIdsAndTagVisibleGender = "queryTalkIdsByTagIdsAndTagVisibleGender";
//    public static final String queryTalkIdsByAdCodeAndGender = "queryTalkIdsByAdCodeAndGender";
    public static final String queryTalkIdsByGenderAndAgeAndAdCodeAndGender = "queryTalkIdsByGenderAndAgeAndAdCodeAndGender";

    public static final String queryUserFollowUserIds = "queryUserFollowUserIds";
}