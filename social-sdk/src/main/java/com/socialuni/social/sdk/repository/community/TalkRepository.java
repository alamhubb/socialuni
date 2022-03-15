package com.socialuni.social.sdk.repository.community;


import com.socialuni.social.entity.model.DO.talk.TalkDO;
import com.socialuni.social.sdk.redis.redisKey.RedisKeysConst;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TalkRepository extends JpaRepository<TalkDO, Integer> {
    @Cacheable(cacheNames = RedisKeysConst.talkById, key = "#id")
    TalkDO findOneById(Integer id);

    //清池使用的
    TalkDO findOneBySocialuniUid(String uid);

    /**
     * 查询可用的全局置顶的动态，为官方置顶的动态
     *
     * @param status
     * @param globalTop
     * @return
     */
    //缓存
    @Cacheable(cacheNames = "stickTalks", key = "{#devId}")
    List<TalkDO> findTop2ByStatusAndDevIdAndGlobalTopGreaterThanOrderByGlobalTopDesc(String status, Integer devId, Integer globalTop);

    //查询自己talk和他人详情talk
    @Query(value = "SELECT t.id FROM TalkDO t where t.status in (:status) and t.userId=:userId order by t.createTime desc")
    List<Integer> queryTalkIdsByUser(
            @Param("userId") Integer userId,
            @Param("status") List<String> status,
            Pageable pageable);

    @Query(value = "SELECT t.id FROM TalkDO t where t.status in (:status) and t.userId=:userId order by t.updateTime desc")
    List<Integer> queryTalkIdsByUserOrderByUpdateTime(
            @Param("userId") Integer userId,
            @Param("status") List<String> status,
            Pageable pageable);

    //查询自己关注的用户列表，包含自己的,类似朋友圈
    @Query(value = "select t.id from TalkDO t where ((t.userId =:userId and t.status in (:onlyUserSeeStatus)) or (t.userId in (:userIds) and t.status =:status)) order by t.createTime desc ")
    List<Integer> queryTalkIdsByUserFollow(
            @Param("userId") Integer userId,
            @Param("onlyUserSeeStatus") List<String> onlyUserSeeStatus,
            @Param("userIds") List<Integer> userIds,
            @Param("status") String status,
            Pageable pageable);


    //查询某性别的talkids
    //只有为all才走前面，其他的都走后面逻辑
    @Cacheable(cacheNames = RedisKeysConst.queryTalkIdsByTagVisibleGender, key = "#talkVisibleGender+'-'+#mineUserGender")
    @Query(nativeQuery = true, value = "SELECT DISTINCT ttg.talk_id as talk_id FROM tag tg,talk_tag ttg WHERE ttg.tag_id = tg.id AND ((:talkVisibleGender = 'all' and tg.visible_gender = 'all') or (:mineUserGender is null or tg.visible_gender = :mineUserGender))")
    List<Integer> queryTalkIdsByTagVisibleGender(
            @Param("talkVisibleGender") String talkVisibleGender,
            @Param("mineUserGender") String mineUserGender);

    //查询某性别和包含tags下的talkids
    //只有为all才走前面，其他的都走后面逻辑
    @Cacheable(cacheNames = RedisKeysConst.queryTalkIdsByTagIdsAndTagVisibleGender, key = "#talkVisibleGender+'-'+#mineUserGender+'-'+#tagIds")
    @Query(nativeQuery = true, value = "SELECT DISTINCT ttg.talk_id as talk_id FROM tag tg,talk_tag ttg WHERE ttg.tag_id = tg.id AND ((:talkVisibleGender = 'all' and tg.visible_gender = 'all') or (:mineUserGender is null or tg.visible_gender = :mineUserGender)) and tg.id in (:tagIds)")
    List<Integer> queryTalkIdsByTagIdsAndTagVisibleGender(
            @Param("tagIds") List<Integer> tagIds);

    //    @Cacheable(cacheNames = RedisKeysConst.queryTalkIdsByAdCodeAndGender, key = "#adCode+'-'+#talkGender+'-'+#mineUserGender+'-'+#devId")
    @Query(nativeQuery = true, value = "SELECT t.id FROM talk t " +
            "where t.global_top = 0 " +
            "and (t.status = :status)" +
            "and (:adCode is null or t.ad_code like concat(:adCode,'%')) " +
            "and (t.visible_type = 'fullNetwork' or (t.visible_type = 'selfSoft' and t.dev_id = :devId)) " +
            "and (:talkVisibleGender = 'all' or (:mineUserGender is null or t.visible_gender = 'all' or t.visible_gender = :mineUserGender) or t.visible_gender = :talkVisibleGender) " +
            "order by t.update_time desc limit 1000")
    List<Integer> queryTalkIdsByAdCodeAndGender(
            @Param("status") String status,
            @Param("adCode") String adCode,
            @Param("talkVisibleGender") String talkVisibleGender,
            @Param("mineUserGender") String mineUserGender,
            @Param("devId") Integer devId);

    /*@Cacheable(cacheNames = RedisKeysConst.queryTalkIdsByGenderAndAgeAndAdCodeAndGender, key = "#userGender+'-'+#minAge+'-'+#maxAge+'-'+#adCode+'-'+#talkGender+'-'+#mineUserGender+'-'+#devId")
    @Query(nativeQuery = true, value = "SELECT t.id FROM s_talk t,s_content c,u_dev_content d,c_user u " +
            "where c.global_top = 0 " +
            "and c.content_type = '动态' and c.id = t.content_id and c.user_id = u.id and c.id = d.content_id " +
            "and (:userGender is null or u.gender = :userGender) " +
            "and u.age between :minAge and :maxAge " +
            "and (c.status = :status)" +
            "and (:adCode is null or t.ad_code like concat(:adCode,'%')) " +
            "and (t.visible_type = 'fullNetwork' or (t.visible_type = 'selfSoft' and d.dev_id = :devId)) " +
            "and (:talkGender = 'all' or (:mineUserGender is null or t.visible_gender = 'all' or t.visible_gender = :mineUserGender) or t.visible_gender = :talkGender) " +
            "order by c.update_time desc limit 1000")
    List<Integer> queryTalkIdsByGenderAndAgeAndAdCodeAndGender(
            @Param("userGender") String userGender,
            @Param("minAge") Integer minAge,
            @Param("maxAge") Integer maxAge,
            @Param("status") String status,
            @Param("adCode") String adCode,
            @Param("talkGender") String talkGender,
            @Param("mineUserGender") String mineUserGender,
            @Param("devId") Integer devId);*/


    @Cacheable(cacheNames = RedisKeysConst.queryTalkIdsByGenderAndAgeAndAdCodeAndGender, key = "#talkUserGender+'-'+#minAge+'-'+#maxAge+'-'+#adCode+'-'+#talkVisibleGender+'-'+#mineUserGender+'-'+#devId")
    @Query(nativeQuery = true, value = "SELECT t.id FROM talk t,user u " +
            "where t.global_top = 0 " +
            "and t.user_id = u.id " +
            //筛选女生、男生发布的、不筛选值为null
            "and (:talkUserGender is null or u.gender = :talkUserGender) " +
            //规则1， 动态   and   talkdovi = 'all' 或者 动态dovi = talkusergender 这个校验，有了下面就不需要校验 用户性别了
            //校验发布专属动态的用户性别必须与talk性别一致，才能显示
            "and (t.visible_gender = 'all' or u.gender = t.visible_gender) " +
            "and u.age between :minAge and :maxAge " +
            "and (t.status = :status)" +
            "and (:adCode is null or t.ad_code like concat(:adCode,'%')) " +
            "and (:devId is null or t.visible_type = 'fullNetwork' or (t.visible_type = 'selfSoft' and t.dev_id = :devId)) " +
            //如果为全部，则显示全部的，和性别一致的。 如果筛选专属，则不为all，走or，则筛选的是性别一致的，所以判断没问题
            //只有全部走前面逻辑，其他都走后面的逻辑，需要为这么写，只有为全部的时候才需要判断，这俩一致，都为全部，其他情况就是展示和用户性别一致的专属动态
            "and ((:talkVisibleGender = 'all' and t.visible_gender = 'all') or (:mineUserGender is null or t.visible_gender = :mineUserGender)) " +
            "and t.id in (:talkIds) " +
            "order by t.update_time desc limit 1000")
    List<Integer> queryTalkIdsByGenderAndAgeAndAdCodeAndGender(
            @Param("talkUserGender") String talkUserGender,
            @Param("minAge") Integer minAge,
            @Param("maxAge") Integer maxAge,
            @Param("status") String status,
            @Param("adCode") String adCode,
            @Param("talkVisibleGender") String talkVisibleGender,
            @Param("mineUserGender") String mineUserGender,
            @Param("talkIds") List<Integer> talkIds,
            @Param("devId") Integer devId);


    @Query(nativeQuery = true, value = "SELECT t.id FROM talk t,tag tg,talk_tag ttg,user u WHERE t.id = ttg.talk_id AND ttg.tag_id = tg.id AND t.user_id = u.id " +
            "and t.global_top = 0 " +
            "and tg.id in (:tagIds) " +
            //筛选女生、男生发布的、不筛选值为null
            "and (:talkUserGender is null or u.gender = :talkUserGender) " +
            //规则1， 动态   and   talkdovi = 'all' 或者 动态dovi = talkusergender 这个校验，有了下面就不需要校验 用户性别了
            //校验发布专属动态的用户性别必须与talk性别一致，才能显示
            "and (t.visible_gender = 'all' or u.gender = t.visible_gender) " +
            "and u.age between :minAge and :maxAge " +
            "and (t.status = :status)" +
            "and (:adCode is null or t.ad_code like concat(:adCode,'%')) " +
            "and (:devId is null or t.visible_type = 'fullNetwork' or (t.visible_type = 'selfSoft' and t.dev_id = :devId)) " +
            //如果为全部，则显示全部的，和性别一致的。 如果筛选专属，则不为all，走or，则筛选的是性别一致的，所以判断没问题
            //只有全部走前面逻辑，其他都走后面的逻辑，需要为这么写，只有为全部的时候才需要判断，这俩一致，都为全部，其他情况就是展示和用户性别一致的专属动态
            "and ((:talkVisibleGender = 'all' and t.visible_gender = 'all') or (:mineUserGender is null or t.visible_gender = :mineUserGender)) " +
            "group by t.id " +
            "order by t.update_time desc limit 1000")
    List<Integer> queryTalkIdsByCom(
            @Param("talkUserGender") String talkUserGender,
            @Param("minAge") Integer minAge,
            @Param("maxAge") Integer maxAge,
            @Param("status") String status,
            @Param("adCode") String adCode,
            @Param("talkVisibleGender") String talkVisibleGender,
            @Param("mineUserGender") String mineUserGender,
            @Param("tagIds") List<Integer> tagIds,
            @Param("devId") Integer devId);

    @Query(nativeQuery = true, value = "SELECT DISTINCT t.id FROM talk t,tag tg,talk_tag ttg,user u WHERE t.id = ttg.talk_id AND ttg.tag_id = tg.id AND t.user_id = u.id " +
            "and t.global_top = 0 " +
            "and (:tagIds is null or tg.id in (:tagIds)) " +
            //筛选女生、男生发布的、不筛选值为null
            "and (:talkUserGender is null or u.gender = :talkUserGender) " +
            //规则1， 动态   and   talkdovi = 'all' 或者 动态dovi = talkusergender 这个校验，有了下面就不需要校验 用户性别了
            //校验发布专属动态的用户性别必须与talk性别一致，才能显示
            "and (t.visible_gender = 'all' or u.gender = t.visible_gender) " +
            "and u.age between :minAge and :maxAge " +
            "and (t.status = :status)" +
            "and (:adCode is null or t.ad_code like concat(:adCode,'%')) " +
            "and (:devId is null or t.visible_type = 'fullNetwork' or (t.visible_type = 'selfSoft' and t.dev_id = :devId)) " +
            //如果为全部，则显示全部的，和性别一致的。 如果筛选专属，则不为all，走or，则筛选的是性别一致的，所以判断没问题
            //只有全部走前面逻辑，其他都走后面的逻辑，需要为这么写，只有为全部的时候才需要判断，这俩一致，都为全部，其他情况就是展示和用户性别一致的专属动态
            "and ((:talkVisibleGender = 'all' and t.visible_gender = 'all') or (:mineUserGender is null or t.visible_gender = :mineUserGender)) " +
            "order by t.update_time desc",
            countQuery = "select 10"
    )
    List<Integer> queryTalkIdsByComPageable(
            @Param("talkUserGender") String talkUserGender,
            @Param("minAge") Integer minAge,
            @Param("maxAge") Integer maxAge,
            @Param("status") String status,
            @Param("adCode") String adCode,
            @Param("talkVisibleGender") String talkVisibleGender,
            @Param("mineUserGender") String mineUserGender,
            @Param("tagIds") List<Integer> tagIds,
            @Param("devId") Integer devId,
            Pageable pageable);


    //上个版本的代码用来备份
    @Cacheable(cacheNames = RedisKeysConst.queryTalkIdsByGenderAndAgeAndAdCodeAndGender, key = "#talkUserGender+'-'+#minAge+'-'+#maxAge+'-'+#adCode+'-'+#talkVisibleGender+'-'+#mineUserGender+'-'+#devId")
    @Query(nativeQuery = true, value = "SELECT t.id FROM talk t,user u " +
            "where t.global_top = 0 " +
            "and t.user_id = u.id " +
            //筛选女生、男生发布的、不筛选值为null
            "and (:talkUserGender is null or u.gender = :talkUserGender) " +
            "and u.age between :minAge and :maxAge " +
            "and (t.status = :status)" +
            "and (:adCode is null or t.ad_code like concat(:adCode,'%')) " +
            "and (:devId is null or t.visible_type = 'fullNetwork' or (t.visible_type = 'selfSoft' and t.dev_id = :devId)) " +
            "and (:talkVisibleGender = 'all' or (:mineUserGender is null or t.visible_gender = 'all' or t.visible_gender = :mineUserGender) or t.visible_gender = :talkVisibleGender) " +
            "and ((t.visible_gender = 'all' and t.visible_gender = :talkVisibleGender) or (mineUserGender is null or t.visible_gender = :mineUserGender)) " +
            "and t.id in (:talkIds) " +
            "order by t.update_time desc limit 1000")
    List<Integer> queryTalkIdsByGenderAndAgeAndAdCodeAndGender1(
            @Param("talkUserGender") String talkUserGender,
            @Param("minAge") Integer minAge,
            @Param("maxAge") Integer maxAge,
            @Param("status") String status,
            @Param("adCode") String adCode,
            @Param("talkVisibleGender") String talkVisibleGender,
            @Param("mineUserGender") String mineUserGender,
            @Param("talkIds") List<Integer> talkIds,
            @Param("devId") Integer devId);

    @Query(nativeQuery = true, value = "SELECT t.id FROM talk t " +
            "where t.id in (:talkIds) " +
            "and t.user_id in (:userIds) " +
            "order by t.update_time desc")
    List<Integer> queryTalkIdsTop10ByGenderAgeAndLikeAdCode(
            @Param("talkIds") List<Integer> talkIds,
            @Param("userIds") List<Integer> userIds);

    @Query(value = "SELECT t.id FROM TalkDO t where t.id in (:talkIds) and ((t.updateTime<:queryTime) or (:queryTime is null)) order by t.updateTime desc")
    List<Integer> queryTalkIdsByIds(
            @Param("talkIds") List<Integer> talkIds,
            @Param("queryTime") Date queryTime,
            Pageable pageable);


    //根据性别年龄和同城
    //tag的性别等于app性别，或者用户性别等于tag性别
    /*@Cacheable(cacheNames = "queryTalkIdsTop10ByGenderAgeAndLikeAdCode", key = "#talkIds+'-'+#userIds+'-'+#tagTalkIds+'-'+#userId+'-'+#status+'-'+#adCode+'-'+#talkGender+'-'+#mineUserGender+'-'+#devId+'-'+#pageable.pageSize+'-'+#pageable.pageNumber")
    @Query(nativeQuery = true, value = "SELECT t.id FROM talk t " +
            "where " +
            "t.user_id in (:userIds) " +
            "and t.id in (:tagTalkIds) " +
            "and (t.status = :status or (t.user_id =:userId and t.status =:onlyUserSeeStatus)) " +
            "and (:adCode is null or t.ad_code like concat(:adCode,'%')) " +
            "and t.id not in (:talkIds) " +
            "and (t.visible_type = 'fullNetwork' or (t.visible_type = 'selfSoft' and t.dev_id = :devId))" +
            "and (:talkGender = 'all' or (:mineUserGender is null or t.visible_gender = 'all' or t.visible_gender = :mineUserGender) or t.visible_gender = :talkGender) " +
            "and t.global_top = 0 " +
            "order by t.update_time desc")
    List<Integer> queryTalkIdsTop10ByGenderAgeAndLikeAdCode(
            @Param("talkIds") List<Integer> talkIds,
            @Param("userIds") List<Integer> userIds,
            @Param("tagTalkIds") List<Integer> tagTalkIds,
            @Param("userId") Integer userId,
            @Param("status") String status,
            @Param("onlyUserSeeStatus") String onlyUserSeeStatus,
            @Param("adCode") String adCode,
            @Param("talkGender") String talkGender,
            @Param("mineUserGender") String mineUserGender,
            @Param("devId") Integer devId,
            Pageable pageable);*/

    @Query(nativeQuery = true, value = "SELECT t.id FROM talk t " +
            "where t.global_top = 0 " +
            "and t.user_id in (:userIds) " +
            "and t.id in (:tagTalkIds) " +
            "and (t.status = :status or (t.user_id =:userId and t.status =:onlyUserSeeStatus)) " +
            "and (:adCode is null or t.ad_code like concat(:adCode,'%')) " +
            "and (t.visible_type = 'fullNetwork' or (t.visible_type = 'selfSoft' and t.dev_id = :devId)) " +
            "and (:talkVisibleGender = 'all' or (:mineUserGender is null or t.visible_gender = 'all' or t.visible_gender = :mineUserGender) or t.visible_gender = :talkVisibleGender) " +
            "order by t.update_time desc limit 1000")
    List<Integer> queryTalkIdsTop10ByGenderAgeAndLikeAdCode(
            @Param("userIds") List<Integer> userIds,
            @Param("tagTalkIds") List<Integer> tagTalkIds,
            @Param("userId") Integer userId,
            @Param("status") String status,
            @Param("onlyUserSeeStatus") String onlyUserSeeStatus,
            @Param("adCode") String adCode,
            @Param("talkVisibleGender") String talkVisibleGender,
            @Param("mineUserGender") String mineUserGender,
            @Param("devId") Integer devId);

    //查询user指定时间内发帖数量的，限制发帖数量的
    Integer countByUserIdAndCreateTimeBetween(Integer userId, Date startDate, Date endDate);

    List<TalkDO> findTop2000ByStatusAndViolateTypeOrderByIdDesc(String status, String violateType);

    //查询关键词触发次数时使用
    Page<TalkDO> findByStatusNotInOrderByIdDesc(Pageable pageable, List<String> status);
/*

    //供后台统计使用**************************************************************************************
    @Query(nativeQuery = true, value = "SELECT COUNT(0),DATE_FORMAT(t.create_time,'%Y-%m-%d')AS DAY " +
            "FROM talk t" +
            " WHERE " +
            "t.create_time BETWEEN '2020-01-01' AND now() " +
            "GROUP BY DATE_FORMAT(t.create_time,'%Y-%m-%d')")
    List<Object> countAndCreateTimeByCreateTimeBetween();



    Page<TalkDO> findByStatusNotInAndContentLikeOrderByIdDesc(Pageable pageable, List<String> status, String content);

    List<TalkDO> findTop20ByUserIdOrderByIdDesc(Integer userId);

    //弃用的*************************************************************************************

    @Query(nativeQuery = true, value = "SELECT t.* FROM talk t,position p,user u WHERE t.user_id = u.id and u.age between :minAge and :maxAge and u.gender in (:genders) and t.id not in (:talkIds) and t.status in (:status) and t.position_id = p.`id` and p.`lon` BETWEEN :lon-:distance AND :lon+:distance AND p.`lat` BETWEEN :lat-:distance AND :lat+:distance ORDER BY FLOOR(UNIX_TIMESTAMP(t.`update_time`)/3600) DESC,t.id desc LIMIT :limitNum")
    List<TalkDO> findTalksByPosition(@Param("status") List<String> status, @Param("genders") List<String> genders, @Param("minAge") Integer minAge, @Param("maxAge") Integer maxAge, @Param("talkIds") List<Integer> talkIds, @Param("lon") Double lon, @Param("lat") Double lat, @Param("distance") Double distance, @Param("limitNum") Integer limitNum);

    @Query(nativeQuery = true, value = "SELECT distinct t.* FROM talk t,position p,talk_tag g,user u WHERE t.user_id = u.id and u.age between :minAge and :maxAge and u.gender in (:genders) and t.position_id = p.`id` and t.id = g.talk_id and t.id not in (:talkIds) and t.status in (:status) and g.tag_id in (:tagIds) and p.`lon` BETWEEN :lon-:distance AND :lon+:distance AND p.`lat` BETWEEN :lat-:distance AND :lat+:distance ORDER BY FLOOR(UNIX_TIMESTAMP(t.`update_time`)/3600) DESC,t.id desc LIMIT :limitNum")
    List<TalkDO> findTalksByPositionAndTags(@Param("status") List<String> status, @Param("genders") List<String> genders, @Param("minAge") Integer minAge, @Param("maxAge") Integer maxAge, @Param("talkIds") List<Integer> talkIds, @Param("lon") Double lon, @Param("lat") Double lat, @Param("distance") Double distance, @Param("limitNum") Integer limitNum, @Param("tagIds") List<Integer> tagIds);
*/


    //新增自己可见预审核状态动态，弃用这个
    //缓存
//    List<TalkDO> findTop10ByStatusInAndUserGenderInAndUserAgeBetweenAndIdNotInAndDistrictAdCodeLikeAndTagsIdInOrderByUpdateTimeDesc(List<String> status, List<String> genders, Integer minAge, Integer maxAge, List<Integer> talkIds, String adCode, List<Integer> tagIds);

}