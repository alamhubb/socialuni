package com.socialuni.embed.demo.controller;

import com.socialuni.embed.demo.model.TokenDO;
import com.socialuni.embed.demo.model.TokenSocialuniTokenDO;
import com.socialuni.embed.demo.model.UserDO;
import com.socialuni.embed.demo.service.TestUserService;
import com.socialuni.sdk.constant.SocialuniCommonConst;
import com.socialuni.sdk.constant.VisibleType;
import com.socialuni.sdk.factory.user.base.SocialContentUserROFactory;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.QO.comment.CenterCommentPostQO;
import com.socialuni.sdk.model.QO.community.talk.SocialTalkPostQO;
import com.socialuni.sdk.model.RO.talk.CenterCommentRO;
import com.socialuni.sdk.model.RO.talk.CenterTalkRO;
import com.socialuni.sdk.model.RO.user.base.SocialContentUserRO;
import com.socialuni.sdk.model.RO.user.base.SocialUserRO;
import com.socialuni.sdk.serive.CenterCommentService;
import com.socialuni.sdk.serive.CenterTalkService;
import com.socialuni.sdk.utils.SocialUserUtil;
import com.socialuni.social.web.sdk.model.ResultRO;
import com.socialuni.sdk.constant.socialuni.GenderType;
import com.socialuni.sdk.config.SocialTokenUtil;
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
    CenterCommentService centerCommentService;
    @Resource
    TestTokenRepository testTokenRepository;
    @Resource
    TestUserService testUserService;

    @GetMapping("login")
    public Map<String, Object> login(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        UserDO userDO = testUserRepository.save(new UserDO(name));

        TokenDO tokenDO = new TokenDO(userDO.getId(), SocialTokenUtil.generateTokenByUserId(userDO.getId()));

        tokenDO = testTokenRepository.save(tokenDO);

        TokenSocialuniTokenDO socialuniTokenDO = testUserService.getSocialuniToken(tokenDO.getToken());

        SocialUserDO socialUserDO = SocialUserUtil.getUserByToken(socialuniTokenDO.getSocialuniToken());
        SocialContentUserRO socialContentUserRO = SocialContentUserROFactory.newContentUserRO(socialUserDO, socialUserDO);

        Map<String, Object> map = new HashMap<>();
        map.put("user", socialContentUserRO);
        map.put("token", tokenDO.getToken());
        return map;
    }

    @GetMapping("getMineUser")
    public SocialUserRO getMineUser() {
        SocialUserDO socialUserDO = SocialUserUtil.getMineUserNotNull();
        SocialContentUserRO socialUserRO = SocialContentUserROFactory.newContentUserRO(socialUserDO, socialUserDO);
        return socialUserRO;
    }

    @GetMapping("postTalk")
    public CenterTalkRO postTalk(String content) {
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

    @GetMapping("postComment")
    public CenterCommentRO postComment(String talkId, String content) {
        CenterCommentPostQO socialTalkPostQO = new CenterCommentPostQO();
        socialTalkPostQO.setContent(content);
        socialTalkPostQO.setTalkId(talkId);
        ResultRO<CenterCommentRO> resultRO = centerCommentService.postComment(socialTalkPostQO);
        return resultRO.getData();
    }

    //如果header里面没有，则从reqeust里面拿
    @GetMapping("queryTalks")
    public List<CenterTalkRO> talks() {
        return centerTalkService.queryTalks().getData();
    }
}
