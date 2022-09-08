package com.socialuni.sdk.logic.service.user;

import com.socialuni.sdk.config.SocialAppConfig;
import com.socialuni.sdk.logic.domain.user.SocialAddUserImgDomain;
import com.socialuni.sdk.logic.domain.user.SocialDeleteUserImgDomain;
import com.socialuni.sdk.logic.domain.user.SocialEditUserDomain;
import com.socialuni.sdk.logic.entity.UniUserRegistryDomain;
import com.socialuni.sdk.factory.RO.user.CenterMineUserDetailROFactory;
import com.socialuni.sdk.factory.RO.user.CenterUserDetailROFactory;
import com.socialuni.sdk.feignAPI.SocialuniUserAPI;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.model.QO.user.*;
import com.socialuni.sdk.model.RO.user.*;
import com.socialuni.sdk.platform.tencent.TencentCloud;
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
            mineUserDetailRO = CenterMineUserDetailROFactory.getMineUserDetail();
        }
        return new ResultRO<>(mineUserDetailRO);
    }

    public ResultRO<CenterUserDetailRO> queryUserDetail(String userId) {
        SocialuniUserDO detailUserDO = SocialuniUserUtil.getUserByUid(userId);

        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserAllowNull();

        CenterUserDetailRO userDetailRO;
        if (SocialAppConfig.serverIsChild()) {
            ResultRO<CenterUserDetailRO> resultRO = socialuniUserAPI.queryUserDetail(userId);
            userDetailRO = new CenterUserDetailRO(resultRO.getData());
        } else {
            userDetailRO = CenterUserDetailROFactory.getUserDetailRO(detailUserDO, mineUser);
        }
        return new ResultRO<>(userDetailRO);
    }


    public ResultRO<SocialuniMineUserDetailRO> editUser(SocialUserEditQO socialUserEditQO) {
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserNotNull();
        SocialMineUserDetailRO socialMineUserDetailRO = socialEditUserDomain.editUser(socialUserEditQO, mineUser);

        if (SocialAppConfig.serverIsChild()) {
            return socialuniUserAPI.editUser(socialUserEditQO);
        }

        SocialuniMineUserDetailRO centerMineUserDetailRO = CenterMineUserDetailROFactory.getMineUserDetail(socialMineUserDetailRO, mineUser);

        return ResultRO.success(centerMineUserDetailRO);
    }

    public ResultRO<SocialuniMineUserDetailRO> addUserImg(SocialUserImgAddQO socialUserImgAddQO) {
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserNotNull();

        SocialMineUserDetailRO socialMineUserDetailRO = socialAddUserImgDomain.addUserImg(socialUserImgAddQO, mineUser);

        if (SocialAppConfig.serverIsChild()) {
            return socialuniUserAPI.addUserImg(socialUserImgAddQO);
        }
        SocialuniMineUserDetailRO centerMineUserDetailRO = CenterMineUserDetailROFactory.getMineUserDetail(socialMineUserDetailRO, mineUser);

        return ResultRO.success(centerMineUserDetailRO);
    }

    public ResultRO<SocialuniMineUserDetailRO> deleteUserImg(CenterUserImgDeleteQO centerUserImgDeleteQO) {
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserNotNull();

        Integer userImgId = UnionIdDbUtil.getUserImgUnionIdByUidNotNull(centerUserImgDeleteQO.getUserImgId());

        SocialMineUserDetailRO socialMineUserDetailRO = socialDeleteUserImgDomain.deleteUserImg(new SocialUserImgDeleteQO(userImgId), mineUser);
        if (SocialAppConfig.serverIsChild()) {
            return socialuniUserAPI.deleteUserImg(centerUserImgDeleteQO);
        }
        SocialuniMineUserDetailRO centerMineUserDetailRO = CenterMineUserDetailROFactory.getMineUserDetail(socialMineUserDetailRO, mineUser);

        return ResultRO.success(centerMineUserDetailRO);
    }

    public ResultRO<SocialUserIdentityAuthPreCheckRO> identityAuthPreCheck(SocialUserIdentityAuthQO socialUseIdentityAuthQO) {
        Integer resScore = TencentCloud.imgAuthGetScore(socialUseIdentityAuthQO.getIdImgUrl(), socialUseIdentityAuthQO.getSelfieImgUrl());
        return null;
    }

    public ResultRO<SocialUserIdentityAuthRO> identityAuth(SocialUserIdentityAuthQO socialUseIdentityAuthQO) {
        return null;
    }
}
