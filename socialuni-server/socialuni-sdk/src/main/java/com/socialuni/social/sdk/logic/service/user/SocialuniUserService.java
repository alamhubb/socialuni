package com.socialuni.social.sdk.logic.service.user;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.*;
import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.sdk.dao.utils.content.SocialuniUserImgDOUtil;
import com.socialuni.social.sdk.feignAPI.user.SocialuniUserAPI;
import com.socialuni.social.sdk.logic.domain.user.SocialAddUserImgDomain;
import com.socialuni.social.sdk.logic.domain.user.SocialDeleteUserImgDomain;
import com.socialuni.social.sdk.logic.entity.UniUserRegistryDomain;
import com.socialuni.social.sdk.logic.factory.RO.user.SocialuniContentUserROFactory;
import com.socialuni.social.sdk.logic.factory.RO.user.SocialuniMineUserDetailROFactory;
import com.socialuni.social.sdk.logic.factory.RO.user.SocialuniUserDetailROFactory;
import com.socialuni.social.sdk.logic.factory.UserImgROFactory;
import com.socialuni.social.tance.sdk.api.SocialuniUnionIdInterface;
import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.constant.SocialuniUserStatus;
import com.socialuni.social.user.sdk.logic.domain.SocialEditUserDomain;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserImgDo;
import com.socialuni.social.user.sdk.model.QO.SocialUserEditQO;
import com.socialuni.social.user.sdk.model.QO.SocialUserImgDeleteQO;
import com.socialuni.social.user.sdk.model.QO.SocialuniUserImgDeleteQO;
import com.socialuni.social.user.sdk.model.factory.SocialuniUserROFactory;
import com.socialuni.social.user.sdk.repository.SocialuniUserRepository;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
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
    SocialuniUnionIdInterface uniContentUnionIdRepository;
    @Resource
    SocialuniUserAPI socialuniUserAPI;
    @Resource
    SocialuniUserRepository socialuniUserRepository;


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

    //查询最近在线的用户
    public ResultRO<List<SocialuniContentUserRO>> queryRecentlyUsers() {
        List<SocialuniUserDo> userDos = socialuniUserRepository.findTop10ByStatusOrderByIdDesc(SocialuniUserStatus.enable);

        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();
        List<SocialuniContentUserRO> userROS = SocialuniContentUserROFactory.toList(userDos, mineUser);
        return ResultRO.success(userROS);
    }
}
