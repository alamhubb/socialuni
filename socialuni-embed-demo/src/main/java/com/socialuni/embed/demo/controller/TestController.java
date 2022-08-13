package com.socialuni.embed.demo.controller;

import com.socialuni.embed.demo.model.TokenDO;
import com.socialuni.embed.demo.model.UserDO;
import com.socialuni.sdk.constant.SocialuniCommonConst;
import com.socialuni.sdk.constant.VisibleType;
import com.socialuni.sdk.factory.user.base.SocialUserROFactory;
import com.socialuni.sdk.model.QO.community.talk.SocialTalkPostQO;
import com.socialuni.sdk.model.RO.talk.CenterTalkRO;
import com.socialuni.sdk.model.RO.user.base.SocialUserRO;
import com.socialuni.sdk.serive.CenterTalkService;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.GenderType;
import com.socialuni.social.web.sdk.utils.SocialTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {
    @GetMapping("hello")
    public String test() {
        return "hello world";
    }

    @Resource
    TestUserRepository testUserRepository;
    @Resource
    CenterTalkService centerTalkService;
    @Resource
    TestTokenRepository testTokenRepository;

    @GetMapping("login")
    public Map<String, Object> login(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        UserDO userDO = testUserRepository.save(new UserDO(name));

        TokenDO tokenDO = new TokenDO(userDO.getId(), SocialTokenUtil.generateTokenByUserId(userDO.getId()));

        tokenDO = testTokenRepository.save(tokenDO);

        Map<String, Object> map = new HashMap<>();
        map.put("user", userDO);
        map.put("token", tokenDO.getToken());
        return map;
    }

    @GetMapping("getMineUser")
    public SocialUserRO getMineUser() {
        SocialUserRO socialUserRO = SocialUserROFactory.getUserRO(SocialUserUtil.getMineUserNotNull());
        return socialUserRO;
    }

    @GetMapping("postTalk")
    public CenterTalkRO getMineUser(String content) {
        SocialTalkPostQO socialTalkPostQO = new SocialTalkPostQO();
        socialTalkPostQO.setContent(content);
        socialTalkPostQO.setVisibleGender(GenderType.all);
        socialTalkPostQO.setImgs(new ArrayList<>());
        socialTalkPostQO.setVisibleType(VisibleType.fullNetwork);

        socialTalkPostQO.setTagNames(new ArrayList<String>() {{
            add(SocialuniCommonConst.devEnvTagName);
        }});
        ResultRO<CenterTalkRO> resultRO = centerTalkService.postTalk(socialTalkPostQO);
        return resultRO.getData();
    }

    //如果header里面没有，则从reqeust里面拿
    @GetMapping("queryTalks")
    public List<CenterTalkRO> talks() {
        return centerTalkService.queryTalks().getData();
    }
}
