package com.socialuni.sdk.logic.service.user;

import com.socialuni.sdk.config.SocialuniSystemConst;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserImgDO;
import com.socialuni.sdk.dao.repository.SocialuniUnionIdRepository;
import com.socialuni.sdk.dao.utils.content.SocialuniUserImgDOUtil;
import com.socialuni.sdk.feignAPI.user.SocialuniUserAPI;
import com.socialuni.sdk.logic.domain.user.SocialAddUserImgDomain;
import com.socialuni.sdk.logic.domain.user.SocialDeleteUserImgDomain;
import com.socialuni.sdk.logic.domain.user.SocialEditUserDomain;
import com.socialuni.sdk.logic.entity.UniUserRegistryDomain;
import com.socialuni.sdk.logic.factory.RO.user.SocialuniMineUserDetailROFactory;
import com.socialuni.sdk.logic.factory.RO.user.SocialuniUserDetailROFactory;
import com.socialuni.sdk.logic.factory.UserImgROFactory;
import com.socialuni.sdk.model.QO.SocialuniImgAddQO;
import com.socialuni.sdk.model.QO.user.edit.SocialUserEditQO;
import com.socialuni.sdk.model.QO.user.SocialUserImgDeleteQO;
import com.socialuni.sdk.model.QO.user.SocialuniUserImgDeleteQO;
import com.socialuni.sdk.model.RO.user.SocialuniMineUserDetailRO;
import com.socialuni.sdk.model.RO.user.SocialuniUserDetailRO;
import com.socialuni.sdk.model.RO.user.SocialuniUserImgRO;
import com.socialuni.sdk.utils.SocialuniUnionIdUtil;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.web.sdk.model.ResultRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    SocialuniUnionIdRepository uniContentUnionIdRepository;
    @Resource
    SocialuniUserAPI socialuniUserAPI;

    public ResultRO<SocialuniMineUserDetailRO> getMineUser() {
        SocialuniMineUserDetailRO mineUserDetailRO;
        if (SocialuniSystemConst.serverIsChild()) {
            ResultRO<SocialuniMineUserDetailRO> resultRO = socialuniUserAPI.getMineUser();
            mineUserDetailRO = new SocialuniMineUserDetailRO(resultRO.getData());
        } else {
            mineUserDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail();
        }
        return new ResultRO<>(mineUserDetailRO);
    }

    public ResultRO<SocialuniUserDetailRO> queryUserDetail(String userId) {
        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniUserAPI.queryUserDetail(userId);
        } else {
            SocialuniUserDetailRO userDetailRO;
            SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserAllowNull();
            SocialuniUserDO detailUserDO = SocialuniUserUtil.getUserByUuid(userId);
            if (mineUser != null && detailUserDO.getUnionId().equals(mineUser.getUnionId())) {
                userDetailRO = SocialuniMineUserDetailROFactory.getMineUserDetail(mineUser);
            } else {
                userDetailRO = SocialuniUserDetailROFactory.getUserDetailRO(detailUserDO, mineUser);
            }
            return new ResultRO<>(userDetailRO);
        }
    }


    public ResultRO<SocialuniMineUserDetailRO> editUser(SocialUserEditQO socialUserEditQO) {
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserNotNull();
        SocialuniMineUserDetailRO socialMineUserDetailRO = socialEditUserDomain.editUser(socialUserEditQO, mineUser);

        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniUserAPI.editUser(socialUserEditQO);
        }

        return ResultRO.success(socialMineUserDetailRO);
    }

    public ResultRO<SocialuniMineUserDetailRO> addUserImg(SocialuniImgAddQO socialUserImgAddQO) {
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserNotNull();

        SocialuniMineUserDetailRO socialMineUserDetailRO = socialAddUserImgDomain.addUserImg(socialUserImgAddQO, mineUser);

        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniUserAPI.addUserImg(socialUserImgAddQO);
        }

        return ResultRO.success(socialMineUserDetailRO);
    }

    public ResultRO<SocialuniMineUserDetailRO> addUserAvatarImg(SocialuniImgAddQO socialUserImgAddQO) {
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserNotNull();


        SocialuniMineUserDetailRO socialMineUserDetailRO = socialAddUserImgDomain.addUserAvatarImg(socialUserImgAddQO, mineUser);

        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniUserAPI.addUserAvatarImg(socialUserImgAddQO);
        }

        return ResultRO.success(socialMineUserDetailRO);
    }


    public ResultRO<SocialuniMineUserDetailRO> deleteUserImg(SocialuniUserImgDeleteQO centerUserImgDeleteQO) {
        SocialuniUserDO mineUser = SocialuniUserUtil.getMineUserNotNull();

        Integer userImgId = SocialuniUnionIdUtil.getUnionIdByUuidNotNull(centerUserImgDeleteQO.getUserImgId());

        SocialuniMineUserDetailRO socialMineUserDetailRO = socialDeleteUserImgDomain.deleteUserImg(new SocialUserImgDeleteQO(userImgId), mineUser);
        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniUserAPI.deleteUserImg(centerUserImgDeleteQO);
        }
        return ResultRO.success(socialMineUserDetailRO);
    }

    public ResultRO<List<SocialuniUserImgRO>> getUserImgList(String userId) {
        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniUserAPI.getUserImgList(userId);
        } else {
            Integer userUnionId = SocialuniUnionIdUtil.getUnionIdByUuidNotNull(userId);
            List<SocialuniUserImgDO> imgs50 = SocialuniUserImgDOUtil.getUserImgsTop50(userUnionId);

            List<SocialuniUserImgRO> imgs50Ro = UserImgROFactory.userImgDOToVOS(imgs50);

            return new ResultRO<>(imgs50Ro);
        }
    }
}
