package com.socialuni.social.sdk.logic.service.user;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.sdk.feignAPI.user.SocialuniMineUserAPI;
import com.socialuni.social.sdk.logic.domain.user.SocialAddUserImgDomain;
import com.socialuni.social.sdk.logic.domain.user.SocialDeleteUserImgDomain;
import com.socialuni.social.sdk.logic.entity.UniUserRegistryDomain;
import com.socialuni.social.user.sdk.factory.SocialuniMineUserDetailROFactory;
import com.socialuni.social.tance.sdk.api.SocialuniUnionIdInterface;
import com.socialuni.social.common.api.constant.SocialuniSystemConst;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.logic.domain.SocialEditUserDomain;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.QO.SocialUserEditQO;
import com.socialuni.social.user.sdk.model.QO.SocialUserImgDeleteQO;
import com.socialuni.social.user.sdk.model.QO.SocialuniUserImgDeleteQO;
import com.socialuni.social.common.api.model.user.SocialuniMineUserDetailRO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class SocialuniMineUserService {
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
    SocialuniMineUserAPI socialuniMineUserAPI;

    public ResultRO<SocialuniMineUserDetailRO> getMineUser() {
        SocialuniMineUserDetailRO mineUserDetailRO;
        if (SocialuniSystemConst.serverIsChild()) {
            ResultRO<SocialuniMineUserDetailRO> resultRO = socialuniMineUserAPI.getMineUser();
            mineUserDetailRO = new SocialuniMineUserDetailRO(resultRO.getData());
        } else {
            mineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail();
        }
        return new ResultRO<>(mineUserDetailRO);
    }


    public ResultRO<SocialuniMineUserDetailRO> editUser(SocialUserEditQO socialUserEditQO) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();
        socialEditUserDomain.editUser(socialUserEditQO, mineUser);

        SocialuniMineUserDetailRO socialMineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);

        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniMineUserAPI.editUser(socialUserEditQO);
        }

        return ResultRO.success(socialMineUserDetailRO);
    }

    public ResultRO<SocialuniMineUserDetailRO> addUserImg(SocialuniImgAddQO socialUserImgAddQO) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();

        SocialuniMineUserDetailRO socialMineUserDetailRO = socialAddUserImgDomain.addUserImg(socialUserImgAddQO, mineUser);

        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniMineUserAPI.addUserImg(socialUserImgAddQO);
        }

        return ResultRO.success(socialMineUserDetailRO);
    }

    public ResultRO<SocialuniMineUserDetailRO> randomUserAvatar() {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();

        SocialuniMineUserDetailRO socialMineUserDetailRO = socialAddUserImgDomain.randomUserAvatar(mineUser);

        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniMineUserAPI.randomUserAvatar();
        }

        return ResultRO.success(socialMineUserDetailRO);
    }

    public ResultRO<SocialuniMineUserDetailRO> addUserAvatarImg(SocialuniImgAddQO socialUserImgAddQO) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();


        SocialuniMineUserDetailRO socialMineUserDetailRO = socialAddUserImgDomain.addUserAvatarImg(socialUserImgAddQO, mineUser);

        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniMineUserAPI.addUserAvatarImg(socialUserImgAddQO);
        }

        return ResultRO.success(socialMineUserDetailRO);
    }


    public ResultRO<SocialuniMineUserDetailRO> deleteUserImg(SocialuniUserImgDeleteQO centerUserImgDeleteQO) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();

        Integer userImgId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(centerUserImgDeleteQO.getUserImgId());

        SocialuniMineUserDetailRO socialMineUserDetailRO = socialDeleteUserImgDomain.deleteUserImg(new SocialUserImgDeleteQO(userImgId), mineUser);
        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniMineUserAPI.deleteUserImg(centerUserImgDeleteQO);
        }
        return ResultRO.success(socialMineUserDetailRO);
    }
}
