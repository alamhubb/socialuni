package com.socialuni.sdk.controller;

import com.socialuni.sdk.dao.DO.community.talk.SocialuniTalkDO;
import com.socialuni.sdk.dao.utils.SocialuniUserInfoDOUtil;
import com.socialuni.sdk.utils.SocialuniImgContentUtil;
import com.socialuni.social.web.sdk.model.ResultRO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2018-11-18 20:45
 */

@RestController
@RequestMapping("test")
public class SocialuniTestCosController {
    @Resource
    SocialuniUserInfoDOUtil<SocialuniTalkDO> socialuniTalkDOSocialuniUserInfoDOUtil;

    @GetMapping("test1")
    public ResultRO testCos(String imgKey) {
        List<SocialuniTalkDO> list = socialuniTalkDOSocialuniUserInfoDOUtil.findAll();
        return ResultRO.success(list);
    }
}
