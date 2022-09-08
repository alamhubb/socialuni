package com.socialuni.sdk.logic.service.user;

import com.socialuni.sdk.config.SocialAppConfig;
import com.socialuni.sdk.logic.domain.user.SocialAddUserImgDomain;
import com.socialuni.sdk.logic.domain.user.SocialDeleteUserImgDomain;
import com.socialuni.sdk.logic.domain.user.SocialEditUserDomain;
import com.socialuni.sdk.logic.entity.UniUserRegistryDomain;
import com.socialuni.sdk.factory.RO.user.CenterMineUserDetailROFactory;
import com.socialuni.sdk.factory.RO.user.CenterUserDetailROFactory;
import com.socialuni.sdk.feignAPI.SocialuniUserAPI;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.QO.user.*;
import com.socialuni.sdk.model.RO.user.*;
import com.socialuni.sdk.platform.tencent.TencentCloud;
import com.socialuni.sdk.dao.repository.UniContentUnionIdRepository;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.sdk.utils.UnionIdDbUtil;
import com.socialuni.social.web.sdk.model.ResultRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class CenterUserService {
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

    public ResultRO<CenterMineUserDetailRO> getMineUser() {
        CenterMineUserDetailRO mineUserDetailRO;
        if (SocialAppConfig.serverIsChild()) {
            ResultRO<CenterMineUserDetailRO> resultRO = socialuniUserAPI.getMineUser();
            mineUserDetailRO = new CenterMineUserDetailRO(resultRO.getData());
        } else {
            mineUserDetailRO = CenterMineUserDetailROFactory.getMineUserDetail();
        }
        return new ResultRO<>(mineUserDetailRO);
    }

    public ResultRO<CenterUserDetailRO> queryUserDetail(String userId) {
        SocialUserDO detailUserDO = SocialUserUtil.getUserByUid(userId);

        SocialUserDO mineUser = SocialUserUtil.getMineUserAllowNull();

        CenterUserDetailRO userDetailRO;
        if (SocialAppConfig.serverIsChild()) {
            ResultRO<CenterUserDetailRO> resultRO = socialuniUserAPI.queryUserDetail(userId);
            userDetailRO = new CenterUserDetailRO(resultRO.getData());
        } else {
            userDetailRO = CenterUserDetailROFactory.getUserDetailRO(detailUserDO, mineUser);
        }
        return new ResultRO<>(userDetailRO);
    }


    public ResultRO<CenterMineUserDetailRO> editUser(SocialUserEditQO socialUserEditQO) {
        SocialUserDO mineUser = SocialUserUtil.getMineUserNotNull();
        SocialMineUserDetailRO socialMineUserDetailRO = socialEditUserDomain.editUser(socialUserEditQO, mineUser);

        if (SocialAppConfig.serverIsChild()) {
            return socialuniUserAPI.editUser(socialUserEditQO);
        }

        CenterMineUserDetailRO centerMineUserDetailRO = CenterMineUserDetailROFactory.getMineUserDetail(socialMineUserDetailRO, mineUser);

        return ResultRO.success(centerMineUserDetailRO);
    }

    public ResultRO<CenterMineUserDetailRO> addUserImg(SocialUserImgAddQO socialUserImgAddQO) {
        SocialUserDO mineUser = SocialUserUtil.getMineUserNotNull();

        SocialMineUserDetailRO socialMineUserDetailRO = socialAddUserImgDomain.addUserImg(socialUserImgAddQO, mineUser);

        if (SocialAppConfig.serverIsChild()) {
            return socialuniUserAPI.addUserImg(socialUserImgAddQO);
        }
        CenterMineUserDetailRO centerMineUserDetailRO = CenterMineUserDetailROFactory.getMineUserDetail(socialMineUserDetailRO, mineUser);

        return ResultRO.success(centerMineUserDetailRO);
    }

    public ResultRO<CenterMineUserDetailRO> deleteUserImg(CenterUserImgDeleteQO centerUserImgDeleteQO) {
        SocialUserDO mineUser = SocialUserUtil.getMineUserNotNull();

        Integer userImgId = UnionIdDbUtil.getUserImgUnionIdByUidNotNull(centerUserImgDeleteQO.getUserImgId());

        SocialMineUserDetailRO socialMineUserDetailRO = socialDeleteUserImgDomain.deleteUserImg(new SocialUserImgDeleteQO(userImgId), mineUser);
        if (SocialAppConfig.serverIsChild()) {
            return socialuniUserAPI.deleteUserImg(centerUserImgDeleteQO);
        }
        CenterMineUserDetailRO centerMineUserDetailRO = CenterMineUserDetailROFactory.getMineUserDetail(socialMineUserDetailRO, mineUser);

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
