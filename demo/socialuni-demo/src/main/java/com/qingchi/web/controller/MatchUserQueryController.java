/*
package com.qingchi.web.controller;


import com.socialuni.api.model.RO.ResultRO;
import com.socialuni.center.sdk.utils.UnionIdDbUtil;
import com.socialuni.sdk.constant.GenderType;
import com.socialuni.constant.MatchType;
import com.socialuni.sdk.constant.status.UserStatus;
import com.socialuni.entity.model.DO.user.UserDO;
import com.socialuni.sdk.model.RO.UserDetailVO;
import com.socialuni.sdk.repository.TalkRepository;
import com.socialuni.sdk.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

*/
/**
 * 查询用户详情
 *
 * @author qinkaiyuan
 * @since 1.0.0
 *//*

@RestController
@RequestMapping("match")
public class MatchUserQueryController {
    @Resource
    private UserRepository userRepository;
    @Resource
    private UserService userMatchService;
    @Resource
    private TalkRepository talkRepository;

    */
/**
     * 1.没有token 显示没登录情况下的首页
     * 2.有token但无效，报错，让用户重新登录，前台清空token，用户可在前台跳过登录，到1.
     * 3.有效token，正常查询
     * * 三种情况
     * * 1 未注册用户给他展示颜值偏高的用户
     * * 2 新用户，给他展示最高颜值的用户，如果seeCount小于多少就展示颜值偏高的用户，但是他的颜值呢？给一个平均人的水平，并且新用户友好给65分；
     * * 3 正常用户，按规则展示
     *
     * @return
     *//*

    @PostMapping("queryMatchUsers")
    public ResultRO<List<UserDetailVO>> queryMatchUsers(UserDO user, @RequestBody UserMatchQueryVO queryVO) {
        //查询所有talk按时间排序，然后并且显示用户
//        List<TalkDO> talkDOS = talkRepository.findTop20ByImgsIsNotNullAndStatusInOrderByUpdateTimeDesc(CommonStatus.contentEnableStatus);
        List<UserDO> userDOs;

        List<Integer> userIdsInt = UnionIdDbUtil.getContentIdsByUserUnionIds(queryVO.getUserIds(),  user);


        //如果未登录查所有
        if (user == null) {
            //未登录情况下查询首页匹配用户
            //如果用户为空，则未登录用户
            //查询颜值最高的那批用户，男生女生都算着
            userDOs = userRepository.queryMatchUsersByUserNotLogged(userIdsInt, UserStatus.enable);
        } else {
            //查询出符合条件的用户信息显示
            */
/**
             * 查询列表
             * 1.显示颜值小于等于自己的
             * 2.当前用户没有看过的，在matchrequest表没记录的
             * 3.在线 或者 上次在线时间
             * 4.距离
             * 5.打开了匹配的，如果长时间不使用匹配则会自动关闭，因为经常有人匹配你，你没反应，会造成别人匹配浪费，降低用户体验，别人多余操作
             * 排序规则
             * 1.根据颜值分* 1-10随机数排序
             *//*

            //如果用户被查看次数小于50(定义常量方便修改，不合适时修改)，则不修改face_ratio,只修改喜欢次数和被查看次数
            //查看的时候只查看异性？以后可以支持同性

            */
/*List<MatchRequest> matchRequests = new ArrayList<>();
                matchRequests.add(new MatchRequest(user, userTemp));*//*

            //requestRepository.saveAll(matchRequests);
            //更新他们的被查看次数等信息
            if (MatchType.ilike.equals(queryVO.getMatchType())) {
                userDOs = userRepository.queryILikeMatchUsers(user.getId(), userIdsInt, MatchType.like, UserStatus.enable);
            } else if (MatchType.likeMe.equals(queryVO.getMatchType())) {
                userDOs = userRepository.queryLikeMeMatchUsers(user.getId(), userIdsInt, MatchType.like, UserStatus.enable);
            } else {
                //可以查看所有人，从喜欢那里控制可以喜欢谁
                userDOs = userRepository.queryMatchUsers(user.getId(), GenderType.genders, userIdsInt, MatchType.like, MatchType.operatedes, UserStatus.enable);
            }
            //更新他们的被查看次数等信息
            if (!userDOs.isEmpty()) {
                userMatchService.updateUserListFaceContent(userDOs);
            }
        }
        List<UserDetailVO> talkUserMatchVOS = UserDetailBO.userDOToVOS(userDOs);
        return new ResultRO<>(talkUserMatchVOS);
    }

    */
/*if (user.getSeeCount() > MatchConstants.FACE_RATIO_OPEN_NUM) {
                    } else {
                        //随机给看一些颜值比较好看的人，然后被查看次数10W次以后，则给看同级别的人
                        int tempFaceRatio = (int) ((MatchConstants.FACE_RATIO_BASE_VALUE +
                                IntegerUtils.ranDOm.nextInt(MatchConstants.FACE_RATIO_RANDOM_RANGE)
                                + IntegerUtils.ranDOm.nextDouble()) * MatchConstants.FACE_RATIO_BASE_MULTIPLE);
                        //无敌分，提高用户体验，所有人都可以看所有人
                        tempFaceRatio = 2000000;

                        //性别，年龄，地区可筛选。未来还可以筛选话题
                        //按用户在线时间排序，未来综合地理位置排序，现在可以先不限制，还是限制吧，因为公告上会说明，公告上说明颜值分和正义值？

                        userDOs = userRepository.queryMatchUsers(user.getId(), user.getGender(), tempFaceRatio + MatchConstants.ALLOW_HIGH_USER_FACE, queryVO.getUserIds());
                    }*//*

}*/
