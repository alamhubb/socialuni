package com.socialuni.social.userImg.logic.domain;

import com.socialuni.social.common.api.constant.SocialuniSystemConst;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniUserImgRO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.userImg.api.SocialuniUserImgAPI;
import com.socialuni.social.userImg.dao.DO.SocialuniUserImgDo;
import com.socialuni.social.userImg.factory.UserImgROFactory;
import com.socialuni.social.userImg.model.SocialUserImgDeleteQO;
import com.socialuni.social.userImg.model.SocialuniUserImgDeleteQO;
import com.socialuni.social.userImg.utils.SocialuniUserImgDOUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SocialuniUserImgService {

    @Resource
    SocialAddUserImgDomain socialAddUserImgDomain;
    @Resource
    SocialDeleteUserImgDomain socialDeleteUserImgDomain;
    @Resource
    SocialuniUserImgAPI socialuniUserImgAPI;

    public ResultRO<List<SocialuniUserImgRO>> addUserImg(SocialuniImgAddQO socialUserImgAddQO) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();

        socialAddUserImgDomain.addUserImg(socialUserImgAddQO, mineUser);

        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniUserImgAPI.addUserImg(socialUserImgAddQO);
        }

        return getMineUserImgList();
    }

    public ResultRO<List<SocialuniUserImgRO>> deleteUserImg(SocialuniUserImgDeleteQO centerUserImgDeleteQO) {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();

        Integer userImgId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(centerUserImgDeleteQO.getUserImgId());

        socialDeleteUserImgDomain.deleteUserImg(new SocialUserImgDeleteQO(userImgId), mineUser);
        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniUserImgAPI.deleteUserImg(centerUserImgDeleteQO);
        }

        return getMineUserImgList();
    }

    public ResultRO<List<SocialuniUserImgRO>> getUserImgList(String userIdStr) {

        Integer userId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(userIdStr);

        return getUserImgList(userId);
    }

    public ResultRO<List<SocialuniUserImgRO>> getUserImgList(Integer userId) {
        //用户图片
        List<SocialuniUserImgDo> imgDOS = SocialuniUserImgDOUtil.getUserImgsTop6(userId);
        List<SocialuniUserImgRO> imgVOS = UserImgROFactory.userImgDOToVOS(imgDOS);

        return ResultRO.success(imgVOS);
    }

    public ResultRO<List<SocialuniUserImgRO>> getMineUserImgList() {
        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();

        return getUserImgList(mineUser.getUserId());
    }
}
