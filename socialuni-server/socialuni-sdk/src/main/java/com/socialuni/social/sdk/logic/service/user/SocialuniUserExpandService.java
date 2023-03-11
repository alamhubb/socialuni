package com.socialuni.social.sdk.logic.service.user;

import cn.hutool.core.collection.CollUtil;
import com.socialuni.social.common.api.constant.DateTimeType;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.model.SocialuniPageQueryQO;
import com.socialuni.social.common.api.model.user.SocialuniUserDetailRO;
import com.socialuni.social.common.sdk.utils.ListConvertUtil;
import com.socialuni.social.sdk.constant.user.SocialuniUserExtendFriendsPageType;
import com.socialuni.social.sdk.dao.utils.user.SocialuniUserExtendFriendLogDOUtil;
import com.socialuni.social.sdk.feignAPI.user.SocialuniUserAPI;
import com.socialuni.social.sdk.logic.domain.user.SocialuniEditExpandDomain;
import com.socialuni.social.sdk.logic.factory.RO.user.SocialuniUserDetailROFactory;
import com.socialuni.social.sdk.logic.service.bussiness.SocialuniGetUserContactInfoDomain;
import com.socialuni.social.user.sdk.constant.SocialuniUserStatus;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.QO.SocialUserContactInfoEditQO;
import com.socialuni.social.user.sdk.model.QO.SocialUserSchoolNameEditQO;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniMineUserDetailRO;
import com.socialuni.social.user.sdk.model.QO.user.SocialuniUserExtendFriendQueryQO;
import com.socialuni.social.user.sdk.repository.SocialuniUserExpandRepository;
import com.socialuni.social.user.sdk.repository.SocialuniUserExtendFriendLogRepository;
import com.socialuni.social.user.sdk.repository.SocialuniUserHugRepository;
import com.socialuni.social.user.sdk.repository.SocialuniUserRepository;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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

    public ResultRO<SocialuniMineUserDetailRO> editUserSchoolName(SocialUserSchoolNameEditQO socialMineUserDetailQO) {

        SocialuniMineUserDetailRO socialuniMineUserDetailRO = socialuniEditExpandDomain.editUserSchoolName(socialMineUserDetailQO);

        return ResultRO.success(socialuniMineUserDetailRO);
    }

    public ResultRO<SocialuniMineUserDetailRO> editUserContactInfo(SocialUserContactInfoEditQO socialMineUserDetailQO) {

        SocialuniMineUserDetailRO socialuniMineUserDetailRO = socialuniEditExpandDomain.editUserContactInfo(socialMineUserDetailQO);

        return ResultRO.success(socialuniMineUserDetailRO);
    }

    //查询最近在线的用户
    public ResultRO<List<SocialuniUserDetailRO>> queryExtendFriendUsers(SocialuniPageQueryQO<SocialuniUserExtendFriendQueryQO> socialuniPageQueryQO) {
        SocialuniUserExtendFriendQueryQO socialuniUserExtendFriendQueryQO = socialuniPageQueryQO.getQueryData();

        Date queryTime = socialuniPageQueryQO.getQueryTime();

        String pageType = socialuniUserExtendFriendQueryQO.getPageType();
        if (!SocialuniUserExtendFriendsPageType.allTypes.contains(pageType)) {
            throw new SocialParamsException("错误的扩列页面类型");
        }
        List<Integer> pageTypeUserIds = null;
        //赞个人主页。本周内获得赞最多的吗，就先上线一个最近的。
        if (pageType.equals(SocialuniUserExtendFriendsPageType.hot)) {
            //热门id列表, 然后把他们的赞排序
            List<Integer> hugHotUserIds = socialuniUserHugRepository.findUserIdsOrderByHugNum();
            pageTypeUserIds = hugHotUserIds;
        } else if (pageType.equals(SocialuniUserExtendFriendsPageType.city)) {

        }
        //查询本周内上线的用户
        Date curDate = new Date();
        long lastWeekTime = curDate.getTime() - DateTimeType.week;
        Date lastWeekDate = new Date(lastWeekTime);
        //周id列表
        List<Integer> weekUserIds = socialuniUserExtendFriendLogRepository.findUserIdByUpdateTimeLessThan(lastWeekDate, queryTime);
        //查询打开了联系方式的用户
        List<Integer> openContactIds = socialuniUserExpandRepository.findUserIdsByOpenContactInfoOrderByUpdateTimeDesc();
        //查询用户状态不为封禁的
        List<Integer> userIds = socialuniUserRepository.findUserIdsByStatusOrderByUpdateTimeDesc(SocialuniUserStatus.enable);
        List<Integer> queryIds;
        if (pageTypeUserIds == null) {
            queryIds = ListConvertUtil.intersectionMany(weekUserIds, openContactIds, userIds);
        } else {
            queryIds = ListConvertUtil.intersectionMany(pageTypeUserIds, weekUserIds, openContactIds, userIds);
        }
        List<Integer> pageQueryIds = CollUtil.sub(queryIds,0,10);
        List<SocialuniUserDo> userDos = SocialuniUserUtil.getUsers(pageQueryIds);

        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserAllowNull();

        List<SocialuniUserDetailRO> socialuniUserDetailROS = SocialuniUserDetailROFactory.getUserDetailList(userDos, mineUser);

        //查询的时候更新用户的扩列信息
        SocialuniUserExtendFriendLogDOUtil.createUserExtendFriendLog();

        return ResultRO.success(socialuniUserDetailROS);
    }

    //查询最近在线的用户
    public ResultRO<String> getUserContactInfo(String userId) {
        String userContactInfo = socialuniGetUserContactInfoDomain.getUserContactInfo(userId);
        return ResultRO.success(userContactInfo);
    }
}
