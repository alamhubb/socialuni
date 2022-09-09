package com.socialuni.sdk.logic.service.user;

import com.socialuni.sdk.config.SocialAppConfig;
import com.socialuni.sdk.logic.factory.RO.user.SocialuniMineUserDetailROFactory;
import com.socialuni.sdk.logic.factory.RO.user.SocialuniUserDetailROFactory;
import com.socialuni.sdk.logic.domain.user.SocialAddUserImgDomain;
import com.socialuni.sdk.logic.domain.user.SocialDeleteUserImgDomain;
import com.socialuni.sdk.logic.domain.user.SocialEditUserDomain;
import com.socialuni.sdk.logic.entity.UniUserRegistryDomain;
import com.socialuni.sdk.feignAPI.SocialuniUserAPI;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.model.QO.user.*;
import com.socialuni.sdk.model.RO.user.*;
import com.socialuni.sdk.logic.platform.tencent.TencentCloud;
import com.socialuni.sdk.dao.repository.UniContentUnionIdRepository;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import com.socialuni.sdk.utils.UnionIdDbUtil;
import com.socialuni.social.web.sdk.model.ResultRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    UniContentUnionIdRepository uniContentUnionIdRepository;
    @Resource
    SocialuniUserAPI socialuniUserAPI;

    public ResultRO<SocialuniMineUserDetailRO> getMineUser() {
        SocialuniMineUserDetailRO mineUserDetailRO;
        if (SocialAppConfig.serverIsChild()) {
            ResultRO<SocialuniMineUserDetailRO> resultRO = socialuniUserAPI.getMineUser();
            mineUserDetailRO = new SocialuniMineUserDetailRO(resultRO.getData());
        } else {
            mineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail();
        }
        return new ResultRO<>(mineUserDetailRO);
    }

    public ResultRO<SocialuniUserDetailRO> queryUserDetail(String userId) {
        SocialuniUserDO detailUserDO = SocialuniUserUtil.getUserByUid(userId);

        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserAllowNull();

        SocialuniUserDetailRO userDetailRO;
        if (SocialAppConfig.serverIsChild()) {
            ResultRO<SocialuniUserDetailRO> resultRO = socialuniUserAPI.queryUserDetail(userId);
            userDetailRO = new SocialuniUserDetailRO(resultRO.getData());
        } else {
            userDetailRO = SocialuniUserDetailROFactory.getUserDetailRO(detailUserDO, mineUser);
        }
        return new ResultRO<>(userDetailRO);
    }


    public ResultRO<SocialuniMineUserDetailRO> editUser(SocialUserEditQO socialUserEditQO) {
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserNotNull();
        SocialuniMineUserDetailRO socialMineUserDetailRO = socialEditUserDomain.editUser(socialUserEditQO, mineUser);

        if (SocialAppConfig.serverIsChild()) {
            return socialuniUserAPI.editUser(socialUserEditQO);
        }

        return ResultRO.success(socialMineUserDetailRO);
    }

    public ResultRO<SocialuniMineUserDetailRO> addUserImg(SocialUserImgAddQO socialUserImgAddQO) {
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserNotNull();

        SocialuniMineUserDetailRO socialMineUserDetailRO = socialAddUserImgDomain.addUserImg(socialUserImgAddQO, mineUser);

        if (SocialAppConfig.serverIsChild()) {
            return socialuniUserAPI.addUserImg(socialUserImgAddQO);
        }

        return ResultRO.success(socialMineUserDetailRO);
    }

    public ResultRO<SocialuniMineUserDetailRO> deleteUserImg(SocialuniUserImgDeleteQO centerUserImgDeleteQO) {
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserNotNull();

        Integer userImgId = UnionIdDbUtil.getUnionIdByUidNotNull(centerUserImgDeleteQO.getUserImgId());

        SocialuniMineUserDetailRO socialMineUserDetailRO = socialDeleteUserImgDomain.deleteUserImg(new SocialUserImgDeleteQO(userImgId), mineUser);
        if (SocialAppConfig.serverIsChild()) {
            return socialuniUserAPI.deleteUserImg(centerUserImgDeleteQO);
        }
        return ResultRO.success(socialMineUserDetailRO);
    }

    public ResultRO<SocialUserIdentityAuthPreCheckRO> identityAuthPreCheck(SocialUserIdentityAuthQO socialUseIdentityAuthQO) {
        Integer resScore = TencentCloud.imgAuthGetScore(socialUseIdentityAuthQO.getIdImgUrl(), socialUseIdentityAuthQO.getSelfieImgUrl());
        return null;
    }

    public ResultRO<SocialUserIdentityAuthRO> identityAuth(SocialUserIdentityAuthQO socialUseIdentityAuthQO) {
        return null;
    }
}
