package com.socialuni.social.sdk.im.service;

import cn.hutool.core.util.ReUtil;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.constant.SocialuniConst;
import com.socialuni.social.sdk.im.feign.SocialuniOpenImUserFeign;
import com.socialuni.social.sdk.im.logic.domain.SocialBindUserOpenImAccountDomain;
import com.socialuni.social.sdk.im.model.SocialuniImUserModel;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.constant.GenderTypeNumEnum;
import com.socialuni.social.user.sdk.constant.SocialuniAccountProviderType;
import com.socialuni.social.common.sdk.dao.DO.SocialUserAccountDO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.common.sdk.dao.repository.SocialUserAccountRepository;
import com.socialuni.social.report.sdk.utils.BirthdayAgeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <a href='https://doc.rentsoft.cn:8000/swagger/index.html#/用户相关'>用户相关</a>
 *
 * @author wulinghui
 * @version 1.0
 * @module Socialuni
 * @date 2022/11/4 15:20
 * @since 1.0
 */
@Service
@Slf4j
public class ImUserService {
    @Resource
    private ImHttpComponent imHttpComponent;
    @Resource
    SocialUserAccountRepository socialUserAccountRepository;
    @Resource
    SocialBindUserOpenImAccountDomain socialBindUserOpenImAccountDomain;
    @Resource
    SocialuniOpenImUserFeign socialuniOpenImUserFeign;

    /**
     * 修改用户信息 gender nickname faceURL等
     *
     * @param userModel
     * @param token
     */
    public void updateUserInfo(SocialuniImUserModel userModel, String token) {
        imHttpComponent.post("/auth/user_token", userModel, token);
    }

    public ResultRO<String> getImUserToken(SocialuniUserDo mineUser) {
        SocialuniImUserModel socialuniImUserModel = toImUserModel(mineUser);

        String imToken = null;
        //设置openIm的key
        SocialUserAccountDO socialUserAccountDO = socialUserAccountRepository.findByProviderAndUserId(SocialuniAccountProviderType.openIm, mineUser.getUserId());

        if (socialUserAccountDO == null) {
            imToken = socialuniOpenImUserFeign.userLogin(socialuniImUserModel);
        } else {
            try {
                //如果为登录，则刷新token
                imToken = socialuniOpenImUserFeign.getAndRefreshToken(socialuniImUserModel.getUserID());
            } catch (RuntimeException e) {
                imToken = socialuniOpenImUserFeign.userLogin(socialuniImUserModel);
            }
        }
        socialUserAccountDO = socialBindUserOpenImAccountDomain.bindOrUpdateUserOpenImAccount(mineUser, socialuniImUserModel.getUserID(), imToken);
        return ResultRO.success(socialUserAccountDO.getSessionKey());
    }


    public static SocialuniImUserModel toImUserModel(SocialuniUserDo mineUser) {
        // 注册到Im
        SocialuniImUserModel imUserModel = new SocialuniImUserModel();

        String mineUserUid = SocialuniUnionIdFacede.getUuidByUnionIdNotNull(mineUser.getUserId());

        imUserModel.setUserID(mineUserUid);

        String nickname = mineUser.getNickname();

        boolean containsSpecialChars = ReUtil.contains("[^a-zA-Z0-9\\u4E00-\\u9FA5]", nickname);
        if (containsSpecialChars) {
            nickname = "名称已重置";
        }
        //因为openIm不支持表情昵称
        imUserModel.setNickname(nickname);
        imUserModel.setFaceURL(mineUser.getAvatar());
        imUserModel.setGender(GenderTypeNumEnum.getValueByName(mineUser.getGender()));

        int age = BirthdayAgeUtil.getAgeByBirth(mineUser.getBirthday());
        if (age > 50 || age < 18) {
            //如果值溢出，使用默认值
            imUserModel.setBirth((int) (BirthdayAgeUtil.getBirthDayByBirthString(BirthdayAgeUtil.getYearBirthDateByAge(SocialuniConst.defaultAge)).getTime() / 1000));
        } else {
            //否则使用自带的值
            imUserModel.setBirth((int) (BirthdayAgeUtil.getBirthDayByBirthString(mineUser.getBirthday()).getTime() / 1000));
        }

//        imUserModel.setPhoneNumber(mineUser.getPhoneNum());

        imUserModel.setCreateTime(new Date());
        return imUserModel;
    }

}
