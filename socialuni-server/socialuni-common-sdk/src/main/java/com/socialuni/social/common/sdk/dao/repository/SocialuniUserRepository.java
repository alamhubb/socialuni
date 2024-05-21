package com.socialuni.social.common.sdk.dao.repository;


import cn.hutool.core.bean.BeanUtil;
import com.socialuni.social.common.api.constant.CommonRedisKey;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface SocialuniUserRepository extends JpaRepository<SocialuniUserDo, Integer> {
    //    @Cacheable(cacheNames = CommonRedisKey.findAllUserId)
    @Query(value = "select u.userId from SocialuniUserDo u")
    List<Integer> findAllUserIds();

    @Cacheable(cacheNames = CommonRedisKey.userById, key = "#id")
    SocialuniUserDo findOneByUnionId(Integer id);

    @Caching(
            evict = {
                    @CacheEvict(cacheNames = CommonRedisKey.findUserIdsByStatus, allEntries = true),
                    @CacheEvict(cacheNames = CommonRedisKey.findAllUserId, allEntries = true)
            },
            put = {@CachePut(cacheNames = CommonRedisKey.userById, key = "#user.unionId")}
    )
    default SocialuniUserDo savePut(SocialuniUserDo user) {
        return this.save(BeanUtil.toBean(user, SocialuniUserDo.class));
    }

    @Cacheable(cacheNames = CommonRedisKey.findUserIdsByType, key = "#type")
    @Query("select s.userId from SocialuniUserDo s where s.type = :type")
    List<Integer> findUserIdsByType(String type);

    @Query(value = "select u from SocialuniUserDo u,SocialUserViolationDo su where u.status = :userStatus and u.id = su.userId and su.violationEndTime < :curDate")
    List<SocialuniUserDo> findCanUnfreezeViolationUser(@Param("userStatus") String userStatus, @Param("curDate") Date curDate);

    List<SocialuniUserDo> findTop10ByStatusOrderByIdDesc(@Param("userStatus") String userStatus);

    @Cacheable(cacheNames = CommonRedisKey.findUserIdsByStatus)
    @Query("select s.userId from SocialuniUserDo s where s.status = :status")
    List<Integer> findUserIdsByStatus(String status);

    /*@Modifying
    @Transactional
    @Query(value = "update UserDO u set u.seeCount = u.seeCount+1 where u in (:users)")
    Integer updateUsersSeeCount(List<UserDO> users);

    @Modifying
    @Transactional
    @Query(value = "update UserDO u set u.onlineFlag = false where u.onlineFlag = true and u.lastOnlineTime < :oneHourBeforeDate")
    Integer updateUsersOnlineFlag(Date oneHourBeforeDate);

    //如果他的日期小于当前日期，并且他的vipflag为1
    @Modifying
    @Transactional
    @Query(value = "update UserDO u set u.vipFlag = false where u.vipFlag= true and u.vipEndDate <= :date")
    Integer updateUserVipFlag(Date date);

    //如果他的日期小于当前日期，并且他的vipflag为1
    @Modifying
    @Transactional
    @Query(value = "update UserDO u set u.status = :status where u.status = :vioStatus and u.violationEndTime <= :date")
    Integer updateUserVioStatus(@Param("status") String vioStatus, @Param("status") String status, @Param("date") Date date);

    //查询违规，和封禁已过期的用户
    List<UserDO> findByStatusAndViolationEndTimeBefore(String status, Date date);


    Optional<UserDO> findFirstByPhoneNumOrderByIdAsc(String phoneNum);


    List<UserDO> findByIdCardStatus(String status);
*/
    /*@Query("SELECT DISTINCT u FROM User u,Message m where (u = m.user AND m.receiveUser =:user ) OR ( u = m.receiveUser AND m.user = :user ) ORDER BY m.date DESC")
    List<User> queryUserByMessage(@Param("user") User user);*/


    /**
     * 查询用户表左连接匹配记录表，如果有别人喜欢了他的记录，查询匹配状态为打开
     * 用户不为自己
     * 颜值小于等于自己，
     * 用户喜欢了自己
     * 用户自己没喜欢也讨厌过
     *
     * @param userId
     * @return
     */
    @Query(nativeQuery = true,
            value = "SELECT DISTINCT u.*, m.status FROM " +
                    "user_img img,user u LEFT JOIN match_request m ON u.`id`=m.`user_id` AND m.`receive_user_id`=:userId " +
                    "WHERE 1=1 " +
                    "and u.id=img.user_id and img.status = :userStatus " +
                    //匹配开着
//                    "u.open_match = TRUE " +
                    //不为自己，查询出自己来方便查看自己的样子
//                    "AND u.id!=:userId " +
                    //所有异性
                    "AND (u.gender in (:genders) " +
                    //颜值小于自己或者等于自己的
//                    "AND " +
//                    "(u.`face_ratio`<=:faceRatio " +
                    //喜欢了自己的
                    "OR " +
                    "u.id IN (SELECT m.user_id FROM match_request m " +
                    "WHERE m.`receive_user_id` = :userId AND m.`status`=:status)) " +
//                    ") " +
                    //除去自己不喜欢的和自己喜欢过的
                    "AND u.id NOT IN (SELECT m.receive_user_id FROM match_request m " +
                    "WHERE m.user_id = :userId and m.status in (:statuses)) " +
                    "AND u.id NOT IN (:ids) " +
                    "AND u.status = :userStatus " +
                    "AND u.is_self_auth = true " +
                    "AND u.id != :userId " +
                    "ORDER BY " +
                    //不显示自己了
//                    "u.id=:userId DESC, " +
                    "m.`status` DESC, " +
                    "u.`online_flag` DESC," +
                    //按5分钟时段排序5*60*1000 5分钟
                    "FLOOR(UNIX_TIMESTAMP(u.`last_online_time`)/3600) DESC," +
                    "u.face_ratio * (FLOOR(1 + RAND()*10)) DESC LIMIT 20")
    List<SocialuniUserDo> queryMatchUsers(@Param("userId") Integer userId, @Param("genders") List<String> genders, @Param("ids") List<Integer> ids,
                                          @Param("status") String status, @Param("statuses") List<String> statuses, @Param("userStatus") String userStatus);

    @Query(nativeQuery = true,
            value = "SELECT DISTINCT u.*, m.status FROM " +
                    "user_img img,user u LEFT JOIN match_request m ON u.`id`=m.`user_id` AND m.`receive_user_id`=:userId " +
                    "WHERE 1=1 " +
                    "and u.id=img.user_id " +
                    "and u.id IN (SELECT m.user_id FROM match_request m " +
                    "WHERE m.`receive_user_id` = :userId AND m.`status`=:status) " +
                    //除去自己不喜欢的和自己喜欢过的
                    "AND u.id NOT IN (:ids) " +
                    "AND u.status = :userStatus " +
                    "AND u.is_self_auth = true " +
                    "ORDER BY " +
                    "u.`online_flag` DESC," +
                    //按5分钟时段排序5*60*1000 5分钟
                    "FLOOR(UNIX_TIMESTAMP(u.`last_online_time`)/3600) DESC," +
                    "u.face_ratio * (FLOOR(1 + RAND()*10)) DESC LIMIT 20")
    List<SocialuniUserDo> queryLikeMeMatchUsers(@Param("userId") Integer userId, @Param("ids") List<Integer> ids, @Param("status") String status, @Param("userStatus") String userStatus);

    @Query(nativeQuery = true,
            value = "SELECT DISTINCT u.*, m.status FROM " +
                    "user_img img,user u LEFT JOIN match_request m ON u.`id`=m.`receive_user_id` AND m.`user_id`=:userId " +
                    "WHERE 1=1 " +
                    "and u.id=img.user_id " +
                    "and u.id IN (SELECT m.receive_user_id FROM match_request m " +
                    "WHERE m.`user_id` = :userId AND m.`status`=:status) " +
                    //除去自己不喜欢的和自己喜欢过的
                    "AND u.id NOT IN (:ids) " +
                    "AND u.status = :userStatus " +
                    "AND u.is_self_auth = true " +
                    "ORDER BY " +
                    "u.`online_flag` DESC," +
                    //按5分钟时段排序5*60*1000 5分钟
                    "FLOOR(UNIX_TIMESTAMP(u.`last_online_time`)/3600) DESC," +
                    "u.face_ratio * (FLOOR(1 + RAND()*10)) DESC LIMIT 20")
    List<SocialuniUserDo> queryILikeMatchUsers(@Param("userId") Integer userId, @Param("ids") List<Integer> ids, @Param("status") String status, @Param("userStatus") String userStatus);


    //注释未登录的展示颜值限制"AND (u.`face_ratio`<=65000) " +
    //按5分钟时段排序5*60*1000 5分钟
   /* @Query(nativeQuery = true,
            value = "SELECT DISTINCT u.* FROM user u,user_img img " +
                    "WHERE u.id=img.user_id " +
                    "and u.id not in (:ids) " +
                    "AND u.status = :userStatus " +
                    "AND u.is_self_auth = true " +
                    "ORDER BY " +
                    "u.`online_flag` DESC," +
                    "FLOOR(UNIX_TIMESTAMP(u.`last_online_time`)/3600) DESC," +
                    "u.face_ratio * (FLOOR(1 + RAND()*10)) DESC LIMIT 20")
    List<UserDO> queryMatchUsersByUserNotLogged(@Param("ids") List<Integer> ids, @Param("userStatus") String userStatus);
*/
    /*@Query("select u.faceRatio from UserDO u")
    Double[] queryUserFaceValue();

    @Query("select distinct u.faceValue from UserDO u where u.gender = '男'")
    Integer[] queryUserFaceValueByBoy();

    @Query("select distinct u.faceValue from UserDO u where u.gender = '女'")
    Integer[] queryUserFaceValueByGirl();*/

}

