package com.socialuni.social.sdk.im.contrller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.sdk.im.api.SocialuniImUserAPI;
import com.socialuni.social.sdk.im.service.ImUserService;
import com.socialuni.social.common.sdk.dao.DO.keywords.SocialuniUserDo;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    public ResultRO<String> getImUserToken() {

        SocialuniUserDo mineUser = SocialuniUserUtil.getMineUserNotNull();

        return imUserService.getImUserToken(mineUser);
    }
}
