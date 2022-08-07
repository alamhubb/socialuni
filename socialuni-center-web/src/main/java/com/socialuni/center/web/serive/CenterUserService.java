package com.socialuni.center.web.serive;

import com.socialuni.center.web.domain.user.SocialAddUserImgDomain;
import com.socialuni.center.web.domain.user.SocialDeleteUserImgDomain;
import com.socialuni.center.web.domain.user.SocialEditUserDomain;
import com.socialuni.center.web.entity.UniUserRegistryDomain;
import com.socialuni.center.web.factory.RO.user.CenterMineUserDetailROFactory;
import com.socialuni.center.web.model.DO.UniContentUnionIdDO;
import com.socialuni.center.web.model.DO.UniUserAccountDO;
import com.socialuni.center.web.model.DO.dev.DevAccountDO;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.center.web.model.QO.user.*;
import com.socialuni.center.web.model.RO.user.*;
import com.socialuni.center.web.platform.tencent.TencentCloud;
import com.socialuni.center.web.repository.UniContentUnionIdRepository;
import com.socialuni.center.web.repository.UniUserAccountRepository;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.center.web.utils.DevAccountUtils;
import com.socialuni.center.web.utils.SocialUserUtil;
import com.socialuni.center.web.utils.UnionIdDbUtil;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.exception.SocialParamsException;
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
    UniUserAccountRepository uniUserAccountRepository;

    public ResultRO<CenterMineUserDetailRO> registryUser(SocialProviderLoginQO loginQO) {
        //注册只向三方开发，所以不能为自己
        Integer dataDevId = DevAccountUtils.getDevIdNotNull();
        if (dataDevId == 1){
            throw new SocialParamsException("开发者信息错误");
        }
        String thirdUserId = loginQO.getUnionId();
        SocialUserDO mineUserDO;
        UniUserAccountDO uniUserAccountDO = uniUserAccountRepository.findByDevIdAndThirdUserId(dataDevId, thirdUserId);
        if (uniUserAccountDO != null) {
            mineUserDO = SocialUserUtil.getNotNull(uniUserAccountDO.getUserId());
        } else {
            mineUserDO = socialuniUserRegistryDomain.registryUser(dataDevId, loginQO);
            UnionIdDbUtil.createUserUid(mineUserDO.getId());
            //生成id
        }
        CenterMineUserDetailRO mineUser = CenterMineUserDetailROFactory.getMineUserDetail(mineUserDO);
        return new ResultRO<>(mineUser);
    }

    public ResultRO<CenterMineUserDetailRO> getMineUser() {
        CenterMineUserDetailRO mineUser = CenterMineUserDetailROFactory.getMineUserDetail();
        return new ResultRO<>(mineUser);
    }

    public ResultRO<CenterMineUserDetailRO> queryThirdUser() {
        SocialUserDO mineUserDO = CenterUserUtil.getMineUserAllowNull();
        if (mineUserDO == null) {
            return ResultRO.success();
        }
        CenterMineUserDetailRO mineUser = CenterMineUserDetailROFactory.getMineUserDetail(mineUserDO);
        return new ResultRO<>(mineUser);
    }

    public ResultRO<CenterUserDetailRO> queryUserDetail(CenterUserIdQO centerUserIdQO) {
        SocialUserDO detailUserDO = CenterUserUtil.get(centerUserIdQO.getUserId());

        SocialUserDO mineUser = CenterUserUtil.getMineUserAllowNull();

        CenterUserDetailRO userDetailRO = new CenterUserDetailRO();

        if (mineUser != null && detailUserDO.getId().equals(mineUser.getId())) {
//            userDetailRO = CenterMineUserDetailROFactory.getMineUserDetail(detailUserDO);
        } else {
//            userDetailRO = CenterUserDetailROFactory.getUserDetailRO(detailUserDO, mineUser);
        }

        return new ResultRO<>(userDetailRO);
    }


    public ResultRO<CenterMineUserDetailRO> editUser(SocialUserEditQO socialUserEditQO) {
        SocialUserDO mineUser = CenterUserUtil.getMineUserNotNull();
        SocialMineUserDetailRO socialMineUserDetailRO = socialEditUserDomain.editUser(socialUserEditQO, mineUser);

        CenterMineUserDetailRO centerMineUserDetailRO = CenterMineUserDetailROFactory.getMineUserDetail(socialMineUserDetailRO, mineUser);

        return ResultRO.success(centerMineUserDetailRO);
    }

    public ResultRO<CenterMineUserDetailRO> addUserImg(SocialUserImgAddQO socialUserImgAddQO) {
        SocialUserDO mineUser = CenterUserUtil.getMineUserNotNull();

        SocialMineUserDetailRO socialMineUserDetailRO = socialAddUserImgDomain.addUserImg(socialUserImgAddQO, mineUser);

        CenterMineUserDetailRO centerMineUserDetailRO = CenterMineUserDetailROFactory.getMineUserDetail(socialMineUserDetailRO, mineUser);

        return ResultRO.success(centerMineUserDetailRO);
    }

    public ResultRO<CenterMineUserDetailRO> deleteUserImg(CenterUserImgDeleteQO centerUserImgDeleteQO) {
        SocialUserDO mineUser = CenterUserUtil.getMineUserNotNull();

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
