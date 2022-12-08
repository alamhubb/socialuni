package com.socialuni.social.im.contrller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.utils.StringUtil;
import com.socialuni.social.im.api.SocialuniImUserAPI;
import com.socialuni.social.im.feign.SocialuniOpenImUserFeign;
import com.socialuni.social.im.logic.domain.SocialBindUserOpenImAccountDomain;
import com.socialuni.social.im.model.SocialuniImUserModel;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.constant.GenderTypeNumEnum;
import com.socialuni.social.user.sdk.constant.SocialuniAccountProviderType;
import com.socialuni.social.user.sdk.model.DO.SocialUserAccountDO;
import com.socialuni.social.user.sdk.model.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.repository.SocialUserAccountRepository;
import com.socialuni.social.user.sdk.utils.BirthdayAgeUtil;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 11:09
 * 前端初始化内容
 */
@RestController
@Slf4j
public class SocialuniImController implements SocialuniImUserAPI {

    @Resource
    SocialUserAccountRepository socialUserAccountRepository;
    @Resource
    SocialBindUserOpenImAccountDomain socialBindUserOpenImAccountDomain;
    @Resource
    SocialuniOpenImUserFeign socialuniOpenImUserFeign;

    @Override
    public ResultRO<String> getUserImToken() {

        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();

        return this.getUserImToken(mineUser);
    }

    private ResultRO<String> getUserImToken(SocialuniUserDo mineUser) {
        SocialuniImUserModel socialuniImUserModel = toImUserModel(mineUser);

        String imToken = null;
        try {
            //存在脏数据，所以特殊处理
            imToken = socialuniOpenImUserFeign.getAndRefreshToken(socialuniImUserModel.getUserID());
        } catch (RuntimeException e) {
            log.info("正常逻辑未注册");
        }

        //设置openIm的key
        SocialUserAccountDO socialUserAccountDO = socialUserAccountRepository.findByProviderAndUserId(SocialuniAccountProviderType.openIm, mineUser.getUserId());

        if (socialUserAccountDO == null) {
            if (StringUtils.isEmpty(imToken)) {
                imToken = socialuniOpenImUserFeign.userLogin(socialuniImUserModel);
            }
        } else {
            try {
                //如果为登录，则刷新token
                imToken = socialuniOpenImUserFeign.getAndRefreshToken(socialuniImUserModel.getUserID());
            } catch (RuntimeException e) {
                imToken = socialuniOpenImUserFeign.userLogin(socialuniImUserModel);
                ResultRO<String> resultRO = this.getUserImToken(mineUser);
                imToken = resultRO.getData();
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
        imUserModel.setNickname(mineUser.getNickname());
        imUserModel.setFaceURL(mineUser.getAvatar());
        imUserModel.setGender(GenderTypeNumEnum.getValueByName(mineUser.getGender()));
//        imUserModel.setPhoneNumber(mineUser.getPhoneNum());
        imUserModel.setBirth((int) (BirthdayAgeUtil.getBirthDayByBirthString(mineUser.getBirthday()).getTime() / 1000));
        imUserModel.setCreateTime(new Date());
        return imUserModel;
    }


}
