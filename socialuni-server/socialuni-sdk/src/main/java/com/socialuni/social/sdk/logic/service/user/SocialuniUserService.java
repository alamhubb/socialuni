package com.socialuni.social.sdk.logic.service.user;

import com.socialuni.social.common.api.constant.DateTimeType;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.*;
import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.sdk.constant.user.SocialuniUserExtendFriendsPageType;
import com.socialuni.social.sdk.dao.utils.content.SocialuniUserImgDOUtil;
import com.socialuni.social.sdk.feignAPI.user.SocialuniUserAPI;
import com.socialuni.social.sdk.logic.domain.user.SocialAddUserImgDomain;
import com.socialuni.social.sdk.logic.domain.user.SocialDeleteUserImgDomain;
import com.socialuni.social.sdk.logic.entity.UniUserRegistryDomain;
import com.socialuni.social.sdk.logic.factory.RO.user.SocialuniContentUserROFactory;
import com.socialuni.social.sdk.logic.factory.RO.user.SocialuniMineUserDetailROFactory;
import com.socialuni.social.sdk.logic.factory.RO.user.SocialuniUserDetailROFactory;
import com.socialuni.social.sdk.logic.factory.UserImgROFactory;
import com.socialuni.social.sdk.logic.service.bussiness.SocialuniGetUserContactInfoDomain;
import com.socialuni.social.tance.sdk.api.SocialuniUnionIdInterface;
import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.constant.SocialuniUserStatus;
import com.socialuni.social.user.sdk.logic.domain.SocialEditUserDomain;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserImgDo;
import com.socialuni.social.user.sdk.model.QO.SocialUserEditQO;
import com.socialuni.social.user.sdk.model.QO.SocialUserImgDeleteQO;
import com.socialuni.social.user.sdk.model.QO.SocialuniUserIdQO;
import com.socialuni.social.user.sdk.model.QO.SocialuniUserImgDeleteQO;
import com.socialuni.social.user.sdk.model.QO.user.SocialuniUserExtendFriendQueryQO;
import com.socialuni.social.user.sdk.model.factory.SocialuniUserROFactory;
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
public class SocialuniUserService {
    @Resource
    SocialEditUserDomain socialEditUserDomain;
    @Resource
    SocialAddUserImgDomain socialAddUserImgDomain;
    @Resource
    SocialDeleteUserImgDomain socialDeleteUserImgDomain;
    @Resource
    UniUserRegistryDomain socialuniUserRegistryDomain;
    @Resource
    SocialuniUnionIdInterface uniContentUnionIdRepository;
    @Resource
    SocialuniUserAPI socialuniUserAPI;
    @Resource
    SocialuniUserRepository socialuniUserRepository;
    @Resource
    SocialuniGetUserContactInfoDomain socialuniGetUserContactInfoDomain;


    public ResultRO<SocialuniUserDetailRO> queryUserDetail(String userId) {
        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniUserAPI.queryUserDetail(userId);
        } else {
            SocialuniUserDetailRO userDetailRO;
            SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserAllowNull();
            SocialuniUserDo detailUserDO = SocialuniUserUtil.getUserByUuid(userId);
            if (mineUser != null && detailUserDO.getUnionId().equals(mineUser.getUnionId())) {
                userDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);
            } else {
                userDetailRO = SocialuniUserDetailROFactory.getUserDetailRO(detailUserDO, mineUser);
            }
            return new ResultRO<>(userDetailRO);
        }
    }

    public ResultRO<List<SocialuniUserImgRO>> getUserImgList(String userId) {
        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniUserAPI.getUserImgList(userId);
        } else {
            Integer userUnionId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(userId);
            List<SocialuniUserImgDo> imgs50 = SocialuniUserImgDOUtil.getUserImgsTop50(userUnionId);

            List<SocialuniUserImgRO> imgs50Ro = UserImgROFactory.userImgDOToVOS(imgs50);

            return new ResultRO<>(imgs50Ro);
        }
    }

    public ResultRO<List<SocialuniContentUserRO>> queryRecentlyUsers() {
        List<SocialuniUserDo> userDos = socialuniUserRepository.findTop10ByStatusOrderByIdDesc(SocialuniUserStatus.enable);

        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();
        List<SocialuniContentUserRO> userROS = SocialuniContentUserROFactory.toList(userDos, mineUser);
        return ResultRO.success(userROS);
    }

    @Resource
    SocialuniUserHugRepository socialuniUserHugRepository;
    @Resource
    SocialuniUserExtendFriendLogRepository socialuniUserExtendFriendLogRepository;

    //查询最近在线的用户
    public ResultRO<List<SocialuniContentUserRO>> queryExtendFriendUsers(SocialuniUserExtendFriendQueryQO socialuniUserExtendFriendQueryQO) {
        String pageType = socialuniUserExtendFriendQueryQO.getPageType();
        if (!SocialuniUserExtendFriendsPageType.allTypes.contains(pageType)) {
            throw new SocialParamsException("错误的扩列页面类型");
        }

        //赞个人主页。本周内获得赞最多的吗，就先上线一个最近的。
        if (pageType.equals(SocialuniUserExtendFriendsPageType.hot)) {
            //查询本周内上线的用户
            Date curDate = new Date();
            long lastWeekTime = curDate.getTime() - DateTimeType.week;
            Date lastWeekDate = new Date(lastWeekTime);
            //周id列表
            List<Integer> weekUserIds = socialuniUserExtendFriendLogRepository.findUserIdByUpdateTimeLessThan(lastWeekDate);
            //热门id列表
            List<Integer> hugHotUserIds = socialuniUserHugRepository.findUserIdsOrderByHugNum();


            //然后把他们的赞排序
        } else if (pageType.equals(SocialuniUserExtendFriendsPageType.recently)) {

        } else if (pageType.equals(SocialuniUserExtendFriendsPageType.city)) {

        }
        throw new SocialParamsException("错误的扩列页面类型");

        //查询热门的


        //查询最近的

        //查询同城的


//        List<SocialuniUserDo> userDos = socialuniUserRepository.findTop10ByStatusOrderByIdDesc(SocialuniUserStatus.enable);
//
//        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();
//        List<SocialuniContentUserRO> userROS = SocialuniContentUserROFactory.toList(userDos, mineUser);
//        return ResultRO.success(userROS);
    }

    //查询最近在线的用户
    public ResultRO<String> getUserContactInfo(String userId) {
        String userContactInfo = socialuniGetUserContactInfoDomain.getUserContactInfo(userId);
        return ResultRO.success(userContactInfo);
    }
}
