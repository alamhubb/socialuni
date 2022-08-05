package com.socialuni.center.web.serive;

import com.socialuni.api.model.QO.user.CenterUserIdQO;
import com.socialuni.api.model.QO.user.CenterUserImgDeleteQO;
import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import com.socialuni.api.model.RO.user.CenterUserDetailRO;
import com.socialuni.center.web.entity.UniUserRegistryDomain;
import com.socialuni.center.web.factory.RO.user.CenterMineUserDetailROFactory;
import com.socialuni.center.web.factory.RO.user.CenterUserDetailROFactory;
import com.socialuni.center.web.model.DO.UniContentUnionIdDO;
import com.socialuni.center.web.repository.UniContentUnionIdRepository;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.center.web.utils.UnionIdDbUtil;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.ContentType;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.user.*;
import com.socialuni.social.model.model.RO.user.SocialMineUserDetailRO;
import com.socialuni.social.model.model.RO.user.SocialUserIdentityAuthPreCheckRO;
import com.socialuni.social.model.model.RO.user.SocialUserIdentityAuthRO;
import com.socialuni.center.web.domain.user.SocialAddUserImgDomain;
import com.socialuni.center.web.domain.user.SocialDeleteUserImgDomain;
import com.socialuni.center.web.domain.user.SocialEditUserDomain;
import com.socialuni.center.web.platform.tencent.TencentCloud;
import com.socialuni.center.web.utils.DevAccountUtils;
import com.socialuni.center.web.utils.SocialUserUtil;
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

    public ResultRO<CenterMineUserDetailRO> registryUser(SocialProviderLoginQO loginQO) {
        Integer dataDevId = DevAccountUtils.getDataDevIdNotNull();
        UserDO mineUserDO;
        UniContentUnionIdDO uniContentUnionIdDO = uniContentUnionIdRepository.findByDataDevIdAndDataContentUnionId(dataDevId, Integer.valueOf(loginQO.getUnionId()));
        if (uniContentUnionIdDO != null) {
            mineUserDO = SocialUserUtil.getNotNull(uniContentUnionIdDO.getContentId());
        } else {
            mineUserDO = socialuniUserRegistryDomain.registryUser(dataDevId, loginQO);
            uniContentUnionIdDO = new UniContentUnionIdDO(ContentType.user, DevAccountUtils.getDataDevIdNotNull(), Integer.valueOf(loginQO.getUnionId()), DevAccountUtils.getDevIdNotNull(), mineUserDO.getId());
            uniContentUnionIdDO = uniContentUnionIdRepository.save(uniContentUnionIdDO);
        }
        CenterMineUserDetailRO mineUser = CenterMineUserDetailROFactory.getMineUserDetail(mineUserDO);
        return new ResultRO<>(mineUser);
    }

    public ResultRO<CenterMineUserDetailRO> getMineUser() {
        CenterMineUserDetailRO mineUser = CenterMineUserDetailROFactory.getMineUserDetail();
        return new ResultRO<>(mineUser);
    }

    public ResultRO<CenterMineUserDetailRO> queryThirdUser() {
        UserDO mineUserDO = CenterUserUtil.getMineUserAllowNull();
        if (mineUserDO == null) {
            return ResultRO.success();
        }
        CenterMineUserDetailRO mineUser = CenterMineUserDetailROFactory.getMineUserDetail(mineUserDO);
        return new ResultRO<>(mineUser);
    }

    public ResultRO<CenterUserDetailRO> queryUserDetail(CenterUserIdQO centerUserIdQO) {
        UserDO detailUserDO = CenterUserUtil.get(centerUserIdQO.getUserId());

        UserDO mineUser = CenterUserUtil.getMineUserAllowNull();

        CenterUserDetailRO userDetailRO;

        if (mineUser != null && detailUserDO.getId().equals(mineUser.getId())) {
            userDetailRO = CenterMineUserDetailROFactory.getMineUserDetail(detailUserDO);
        } else {
            userDetailRO = CenterUserDetailROFactory.getUserDetailRO(detailUserDO, mineUser);
        }

        return new ResultRO<>(userDetailRO);
    }


    public ResultRO<CenterMineUserDetailRO> editUser(SocialUserEditQO socialUserEditQO) {
        UserDO mineUser = CenterUserUtil.getMineUserNotNull();
        SocialMineUserDetailRO socialMineUserDetailRO = socialEditUserDomain.editUser(socialUserEditQO, mineUser);

        CenterMineUserDetailRO centerMineUserDetailRO = CenterMineUserDetailROFactory.getMineUserDetail(socialMineUserDetailRO, mineUser);

        return ResultRO.success(centerMineUserDetailRO);
    }

    public ResultRO<CenterMineUserDetailRO> addUserImg(SocialUserImgAddQO socialUserImgAddQO) {
        UserDO mineUser = CenterUserUtil.getMineUserNotNull();

        SocialMineUserDetailRO socialMineUserDetailRO = socialAddUserImgDomain.addUserImg(socialUserImgAddQO, mineUser);

        CenterMineUserDetailRO centerMineUserDetailRO = CenterMineUserDetailROFactory.getMineUserDetail(socialMineUserDetailRO, mineUser);

        return ResultRO.success(centerMineUserDetailRO);
    }

    public ResultRO<CenterMineUserDetailRO> deleteUserImg(CenterUserImgDeleteQO centerUserImgDeleteQO) {
        UserDO mineUser = CenterUserUtil.getMineUserNotNull();

        Integer userImgId = UnionIdDbUtil.getUserImgIdByUid(centerUserImgDeleteQO.getUserImgId());

        SocialMineUserDetailRO socialMineUserDetailRO = socialDeleteUserImgDomain.deleteUserImg(new SocialUserImgDeleteQO(userImgId), mineUser);

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
