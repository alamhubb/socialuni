package com.socialuni.embed.demo.controller;

import com.socialuni.embed.demo.model.TokenDO;
import com.socialuni.embed.demo.model.TokenSocialuniTokenDO;
import com.socialuni.embed.demo.model.UserDO;
import com.socialuni.embed.demo.service.TestUserService;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniContentUserRO;
import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.common.api.utils.SocialTokenFacade;
import com.socialuni.social.common.sdk.constant.SocialuniConst;
import com.socialuni.social.common.sdk.constant.VisibleType;
import com.socialuni.social.sdk.logic.service.SocialuniCommentService;
import com.socialuni.social.sdk.logic.service.talk.SocialuniTalkService;
import com.socialuni.social.community.sdk.model.QO.comment.SocialuniCommentPostQO;
import com.socialuni.social.community.sdk.model.QO.talk.SocialuniTalkPostQO;
import com.socialuni.social.community.sdk.model.RO.talk.SocialuniCommentRO;
import com.socialuni.social.community.sdk.model.RO.talk.SocialuniTalkRO;
import com.socialuni.social.common.api.constant.GenderType;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
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
    SocialuniTalkService centerTalkService;
    @Resource
    SocialuniCommentService centerCommentService;
    @Resource
    TestTokenRepository testTokenRepository;
    @Resource
    TestUserService testUserService;

    @GetMapping("login")
    public ResultRO<Map<String, Object>> login(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        UserDO userDO = testUserRepository.save(new UserDO(name));

        TokenDO tokenDO = new TokenDO(userDO.getId(), SocialTokenFacade.generateTokenByUserId(userDO.getId()));

        tokenDO = testTokenRepository.save(tokenDO);

        TokenSocialuniTokenDO socialuniTokenDO = testUserService.getSocialuniToken(tokenDO.getToken());

        SocialuniUserDo socialUserDO = SocialuniUserUtil.getUserByToken(socialuniTokenDO.getSocialuniToken());
        SocialuniContentUserRO socialContentUserRO = SocialuniContentUserROFactory.newContentUserRO(socialUserDO, socialUserDO);

        Map<String, Object> map = new HashMap<>();
        map.put("user", socialContentUserRO);
        map.put("token", tokenDO.getToken());
        return ResultRO.success(map);
    }

    @GetMapping("getMineUser")
    public ResultRO<SocialuniUserRO> getMineUser() {
        SocialuniUserDo socialUserDO = SocialuniUserUtil.getMineUserNotNull();
        SocialuniContentUserRO socialUserRO = SocialuniContentUserROFactory.newContentUserRO(socialUserDO, socialUserDO);
        return ResultRO.success(socialUserRO);
    }

    @GetMapping("postTalk")
    public ResultRO<SocialuniTalkRO> postTalk(String content) {
        SocialuniTalkPostQO socialTalkPostQO = new SocialuniTalkPostQO();
        socialTalkPostQO.setContent(content);
        socialTalkPostQO.setVisibleGender(GenderType.all);
        socialTalkPostQO.setImgs(new ArrayList<>());
        socialTalkPostQO.setVisibleType(VisibleType.fullNetwork);

        socialTalkPostQO.setTagNames(new ArrayList<String>() {{
            add(SocialuniConst.devEnvTagName);
        }});
        ResultRO<SocialuniTalkRO> resultRO = centerTalkService.postTalk(socialTalkPostQO);
        return resultRO;
    }

    @GetMapping("postComment")
    public ResultRO<SocialuniCommentRO> postComment(String talkId, String content) {
        SocialuniCommentPostQO socialTalkPostQO = new SocialuniCommentPostQO();
        socialTalkPostQO.setContent(content);
        socialTalkPostQO.setTalkId(talkId);
        ResultRO<SocialuniCommentRO> resultRO = centerCommentService.postComment(socialTalkPostQO);
        return resultRO;
    }

    //如果header里面没有，则从reqeust里面拿
    @GetMapping("queryTalks")
    public ResultRO<List<SocialuniTalkRO>> talks() {
        ResultRO<List<SocialuniTalkRO>> resultRO = centerTalkService.queryTalks();
        return resultRO;
    }

    @GetMapping("statistics")
    public ResultRO<Void> statistics() {
        return ResultRO.success();
    }
}
