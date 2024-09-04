package com.socialuni.social.user.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.*;
import com.socialuni.social.tance.sdk.constant.SocialuniDevConfig;
import com.socialuni.social.user.sdk.api.user.SocialuniUserAPI;
import com.socialuni.social.common.api.constant.SocialuniSystemConst;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.factory.SocialuniUserROFactory;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class SocialuniUserService {
    @Resource
    SocialuniUserAPI socialuniUserAPI;

    public ResultRO<SocialuniUserRO> queryUserDetail(String userId) {
        if (SocialuniDevConfig.hasCenterServer()) {
            return socialuniUserAPI.queryUserDetail(userId);
        } else {
            SocialuniUserRO userDetailRO;
            SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserAllowNull();
            SocialuniUserDo detailUserDO = SocialuniUserUtil.getUserByUuid(userId);
            if (mineUser != null && detailUserDO.getUnionId().equals(mineUser.getUnionId())) {
                userDetailRO = SocialuniUserROFactory.getMineUserRO(mineUser);
            } else {
                userDetailRO = SocialuniUserROFactory.getUserRO(detailUserDO, mineUser);
            }
            return new ResultRO<>(userDetailRO);
        }
    }
}
