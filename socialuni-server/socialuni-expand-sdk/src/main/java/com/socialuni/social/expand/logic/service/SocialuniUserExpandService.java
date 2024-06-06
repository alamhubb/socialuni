package com.socialuni.social.expand.logic.service;

import cn.hutool.core.collection.CollUtil;
import com.socialuni.social.common.api.constant.DateTimeType;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.model.SocialuniPageQueryQO;
import com.socialuni.social.common.api.model.user.SocialuniUserExtendDetailRO;
import com.socialuni.social.common.sdk.constant.SocialuniConst;
import com.socialuni.social.common.sdk.constant.UserType;
import com.socialuni.social.common.sdk.utils.ListConvertUtil;
import com.socialuni.social.expand.dao.repository.SocialuniUserExpandRepository;
import com.socialuni.social.expand.factory.SocialuniUserExtendDetailROFactory;
import com.socialuni.social.expand.logic.domain.SocialuniEditExpandDomain;
import com.socialuni.social.expand.logic.domain.SocialuniGetUserContactInfoDomain;
import com.socialuni.social.expand.model.SocialuniUserExpandDetailEditRO;
import com.socialuni.social.likee.dao.repository.SocialuniLikeUseRepository;
import com.socialuni.social.sdk.constant.user.SocialuniUserExtendFriendsPageType;
import com.socialuni.social.common.sdk.utils.SocialuniRequestUtil;
import com.socialuni.social.common.sdk.dao.repository.SocialuniUserRepository;
import com.socialuni.social.content.constant.SocialuniUserStatus;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.QO.SocialUserContactInfoEditQO;
import com.socialuni.social.user.sdk.model.QO.SocialUserSchoolNameEditQO;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.user.sdk.model.QO.user.SocialuniUserExtendFriendQueryQO;
import com.socialuni.social.user.sdk.repository.*;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SocialuniUserExpandService {
    @Resource
    SocialuniEditExpandDomain socialuniEditExpandDomain;
    @Resource
    SocialuniUserRepository socialuniUserRepository;
    @Resource
    SocialuniGetUserContactInfoDomain socialuniGetUserContactInfoDomain;
    @Resource
    SocialuniUserExpandRepository socialuniUserExpandRepository;
    @Resource
    SocialuniUserHugRepository socialuniUserHugRepository;
    @Resource
    SocialuniUserExtendFriendLogRepository socialuniUserExtendFriendLogRepository;
    @Resource
    SocialuniUserExtendLogRepository socialuniUserExtendLogRepository;

    public ResultRO<SocialuniUserExpandDetailEditRO> editUserSchoolName(SocialUserSchoolNameEditQO socialMineUserDetailQO) {

        SocialuniUserExpandDetailEditRO socialuniMineUserDetailRO = socialuniEditExpandDomain.editUserSchoolName(socialMineUserDetailQO);
//        SocialuniMineUserDetailRO socialuniMineUserDetailRO = socialuniEditExpandDomain.editUserSchoolName(socialMineUserDetailQO);

//        SocialuniMineUserDetailRO socialuniMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(socialMineUserDetailQO);

        return ResultRO.success(socialuniMineUserDetailRO);
    }

    public ResultRO<SocialuniUserExpandDetailEditRO> editUserContactInfo(SocialUserContactInfoEditQO socialMineUserDetailQO) {

        return ResultRO.success(socialuniEditExpandDomain.editUserContactInfo(socialMineUserDetailQO));
    }

    @Resource
    SocialuniLikeUseRepository socialuniLikeUseRepository;

    //查询最近在线的用户
    public ResultRO<List<SocialuniUserExtendDetailRO>> queryExtendOperateUsers(SocialuniPageQueryQO<SocialuniUserExtendFriendQueryQO> socialuniPageQueryQO) {
        SocialuniUserExtendFriendQueryQO socialuniUserExtendFriendQueryQO = socialuniPageQueryQO.getQueryData();

        Date queryTime = socialuniPageQueryQO.getQueryTime();
        String pageType = socialuniUserExtendFriendQueryQO.getPageType();
        if (!SocialuniUserExtendFriendsPageType.allTypes.contains(pageType)) {
            throw new SocialParamsException("错误的扩列页面类型");
        }

        //按照最近上一次被喜欢的用户顺序排序。
        List<Integer> beLikeUserIds = socialuniLikeUseRepository.findBeLikeUserIdsOrderByUpdateTimeDesc();

        //按照最近上一次被喜欢的用户顺序排序。
        List<Integer> operateUserIds = socialuniUserRepository.findUserIdsByType(UserType.operation);

        List<Integer> pageTypeUserIds = ListConvertUtil.intersection(beLikeUserIds, operateUserIds);

        pageTypeUserIds.addAll(operateUserIds);
        pageTypeUserIds = pageTypeUserIds.stream().distinct().collect(Collectors.toList());

        List<Integer> queryIds = ListConvertUtil.intersection(pageTypeUserIds, operateUserIds);
        Integer pageSize = socialuniPageQueryQO.getPageSize();
        Integer pageNum = socialuniPageQueryQO.getPageNum();
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        List<Integer> pageQueryIds = CollUtil.sub(queryIds, (pageNum - 1) * pageSize, pageNum * pageSize);
        List<SocialuniUserDo> userDos = SocialuniUserUtil.getUsers(pageQueryIds);

        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserAllowNull();

        List<SocialuniUserExtendDetailRO> socialuniUserDetailROS = SocialuniUserExtendDetailROFactory.getUserExtendDetailList(userDos, mineUser);

        return ResultRO.success(socialuniUserDetailROS);
    }

    //查询最近在线的用户
    public ResultRO<List<SocialuniUserExtendDetailRO>> queryExtendFriendUsers(SocialuniPageQueryQO<SocialuniUserExtendFriendQueryQO> socialuniPageQueryQO) {
        SocialuniUserExtendFriendQueryQO socialuniUserExtendFriendQueryQO = socialuniPageQueryQO.getQueryData();

        Date queryTime = socialuniPageQueryQO.getQueryTime();

        String pageType = socialuniUserExtendFriendQueryQO.getPageType();
        if (!SocialuniUserExtendFriendsPageType.allTypes.contains(pageType)) {
            throw new SocialParamsException("错误的扩列页面类型");
        }

        //查询的时候更新用户的扩列信息
//        SocialuniUserExtendFriendLogDOUtil.createUserExtendFriendLog();

        List<Integer> pageTypeUserIds = new ArrayList<>();
        //查询本周内上线的用户
        Date curDate = new Date();
        long lastWeekTime = curDate.getTime() - DateTimeType.year;
        Date lastWeekDate = new Date(lastWeekTime);
        //赞个人主页。本周内获得赞最多的吗，就先上线一个最近的。
        if (pageType.equals(SocialuniUserExtendFriendsPageType.hot)) {
            //热门id列表, 然后把他们的赞排序
            List<Integer> hugHotUserIds = socialuniUserHugRepository.findUserIdsOrderByHugNum();
            pageTypeUserIds = hugHotUserIds;
        } else if (pageType.equals(SocialuniUserExtendFriendsPageType.city)) {
            //
            String adCode = SocialuniRequestUtil.getCityAdCode();
            //如果首页，不筛选地理位置
            if (SocialuniConst.chinaDistrictCode.equals(adCode) || adCode == null) {
                adCode = null;
            } else {
                //如果为空，为0或者为中国，则查询全部
                //话题校验
                //老版本走着里没啥问题，去判断到底多少，也能为空
                int adCodeInt = Integer.parseInt(adCode);
                if (adCodeInt % 100 == 0) {
                    adCodeInt = adCodeInt / 100;
                    if (adCodeInt % 100 == 0) {
                        adCodeInt = adCodeInt / 100;
                    }
                }
                adCode = String.valueOf(adCodeInt);
            }
            if (!StringUtils.isEmpty(adCode)) {
                pageTypeUserIds = socialuniUserExtendLogRepository.findUserIdsByLickAdCode(adCode);
            }
        } else if (pageType.equals(SocialuniUserExtendFriendsPageType.recently)) {
            //周id列表, 一周内使用过此功能的用户，你最近没用过这个功能，没必要展示你
            pageTypeUserIds = socialuniUserExtendFriendLogRepository.findUserIdByUpdateTimeLessThan(lastWeekDate);
        }
//        List<Integer> weekUserIds = socialuniUserExtendFriendLogRepository.findUserIdByUpdateTimeLessThan(lastWeekDate);
        //分页应该使用排序的表
        //查询打开了联系方式的用户
//        List<Integer> openContactIds = socialuniUserExpandRepository.findUserIdsByOpenContactInfoOrderByLastOnlineTimeDesc(queryTime);
        //查询用户状态不为封禁的
        List<Integer> userIds = socialuniUserRepository.findUserIdsByStatus(SocialuniUserStatus.enable);
        List<Integer> queryIds;
        //暂时不加最近在线，和开启联系方式等功能
//        if (pageTypeUserIds == null) {
//            queryIds = ListConvertUtil.intersectionMany(openContactIds, userIds);
//        } else {
////            pageTypeUserIds,
////            queryIds = ListConvertUtil.intersectionMany(openContactIds,  userIds);
////            queryIds = ListConvertUtil.intersectionMany(userIds);
//            queryIds = userIds;
//        }
        queryIds = ListConvertUtil.intersection(pageTypeUserIds, userIds);
        Integer pageSize = socialuniPageQueryQO.getPageSize();
        Integer pageNum = socialuniPageQueryQO.getPageNum();
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        List<Integer> pageQueryIds = CollUtil.sub(queryIds, (pageNum - 1) * pageSize, pageNum * pageSize);
        List<SocialuniUserDo> userDos = SocialuniUserUtil.getUsers(pageQueryIds);

        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserAllowNull();

        List<SocialuniUserExtendDetailRO> socialuniUserDetailROS = SocialuniUserExtendDetailROFactory.getUserExtendDetailList(userDos, mineUser);

        return ResultRO.success(socialuniUserDetailROS);
    }

    //查询最近在线的用户
    public ResultRO<String> getUserContactInfo(String userId) {
        String userContactInfo = socialuniGetUserContactInfoDomain.getUserContactInfo(userId);
        return ResultRO.success(userContactInfo);
    }
}
