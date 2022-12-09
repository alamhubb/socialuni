package com.socialuni.social.im.contrller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.utils.StringUtil;
import com.socialuni.social.im.api.SocialuniImUserAPI;
import com.socialuni.social.im.feign.SocialuniOpenImUserFeign;
import com.socialuni.social.im.logic.domain.SocialBindUserOpenImAccountDomain;
import com.socialuni.social.im.model.SocialuniImUserModel;
import com.socialuni.social.im.service.ImUserService;
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
    ImUserService imUserService;

    @Override
    public ResultRO<String> getUserImToken() {

        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();

        return imUserService.getUserImToken(mineUser);
    }
}
