package com.socialuni.social.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.im.contrller.SocialuniOpenImUserFeign;
import com.socialuni.social.im.model.SocialuniImUserModel;
import com.socialuni.social.sdk.constant.GenderTypeNumEnum;
import com.socialuni.social.sdk.constant.SocialuniAccountProviderType;
import com.socialuni.social.sdk.dao.repository.user.SocialUserAccountRepository;
import com.socialuni.social.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.sdk.feignAPI.im.SocialuniImUserAPI;
import com.socialuni.social.sdk.logic.domain.user.SocialBindUserOpenImAccountDomain;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.sdk.utils.common.BirthdayAgeUtil;
import com.socialuni.social.user.sdk.entity.SocialUserAccountDO;
import com.socialuni.social.user.sdk.entity.SocialuniUserDo;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 11:09
 * 前端初始化内容
 */
@RestController
public class SocialuniImController implements SocialuniImUserAPI {

    @Resource
    SocialUserAccountRepository socialUserAccountRepository;
    @Resource
    SocialBindUserOpenImAccountDomain socialBindUserOpenImAccountDomain;
    @Resource
    SocialuniOpenImUserFeign socialuniOpenImUserFeign;

    @Override
    public ResultRO<String> getImUserToken() {

        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();

        //设置openIm的key
        SocialUserAccountDO socialUserAccountDO = socialUserAccountRepository.findByProviderAndUserId(SocialuniAccountProviderType.openIm, mineUser.getUserId());

        SocialuniImUserModel socialuniImUserModel = toImUserModel(mineUser);

        String imToken;
        if (socialUserAccountDO == null) {
            imToken = socialuniOpenImUserFeign.userLogin(socialuniImUserModel);
        } else {
            //如果为登录，则刷新token
            imToken = socialuniOpenImUserFeign.getAndRefreshToken(socialuniImUserModel.getUserID());
        }
        socialUserAccountDO = socialBindUserOpenImAccountDomain.bindOrUpdateUserOpenImAccount(mineUser, socialuniImUserModel.getUserID(), imToken);
        return ResultRO.success(socialUserAccountDO.getSessionKey());
    }


    public static SocialuniImUserModel toImUserModel(SocialuniUserDo mineUser) {
        // 注册到Im
        SocialuniImUserModel imUserModel = new SocialuniImUserModel();

        String mineUserUid = SocialuniUnionIdFacede.getUuidByUnionIdNotNull(mineUser.getUserId());

        imUserModel.setUserID(mineUserUid);
        imUserModel.setNickname(mineUser.getNickname());
        imUserModel.setFaceURL(mineUser.getAvatar());
        imUserModel.setGender(GenderTypeNumEnum.getValueByName(mineUser.getGender()));
//        imUserModel.setPhoneNumber(mineUser.getPhoneNum());
        imUserModel.setBirth((int) (BirthdayAgeUtil.getBirthDayByBirthString(mineUser.getBirthday()).getTime() / 1000));
        imUserModel.setCreateTime(new Date());
        return imUserModel;
    }


}
