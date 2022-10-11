package com.socialuni.social.user.sdk.api;


import com.socialuni.social.user.sdk.model.SocialuniUserModel;

import java.util.Date;
import java.util.List;

public interface UserApi  {
    List<Integer> findAllUserIds();

    SocialuniUserModel findOneByUnionId(Integer id);

    SocialuniUserModel savePut(SocialuniUserModel user);


    List<SocialuniUserModel> findCanUnfreezeViolationUser(String userStatus, Date curDate);

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
    List<SocialuniUserModel> queryMatchUsers(Integer userId, List<String> genders, List<Integer> ids,
                                             String status, List<String> statuses,  String userStatus);


    List<SocialuniUserModel> queryLikeMeMatchUsers(Integer userId,  List<Integer> ids,String status, String userStatus);


    List<SocialuniUserModel> queryILikeMatchUsers(Integer userId,List<Integer> ids, String status,String userStatus);


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

