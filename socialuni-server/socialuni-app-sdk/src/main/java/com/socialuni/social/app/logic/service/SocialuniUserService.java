package com.socialuni.social.app.logic.service;

import com.socialuni.social.app.factory.SocialuniMineUserDetailROFactory;
import com.socialuni.social.app.factory.SocialuniUserDetailROFactory;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.*;
import com.socialuni.social.expand.logic.domain.SocialuniGetUserContactInfoDomain;
import com.socialuni.social.user.sdk.api.user.SocialuniUserAPI;
import com.socialuni.social.userImg.dao.DO.SocialuniUserImgDo;
import com.socialuni.social.userImg.utils.SocialuniUserImgDOUtil;
import com.socialuni.social.follow.facotry.SocialuniContentUserROFactory;
import com.socialuni.social.userImg.factory.UserImgROFactory;
import com.socialuni.social.common.api.constant.SocialuniSystemConst;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.content.constant.SocialuniUserStatus;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.common.sdk.dao.repository.SocialuniUserRepository;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
@Slf4j
public class SocialuniUserService {
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

    //查询最近在线的用户
    public ResultRO<String> getUserContactInfo(String userId) {
        String userContactInfo = socialuniGetUserContactInfoDomain.getUserContactInfo(userId);
        return ResultRO.success(userContactInfo);
    }
}
