package com.socialuni.social.official.controller;

import com.socialuni.social.common.api.constant.SocialuniLoginType;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.api.model.user.SocialuniContentUserRO;
import com.socialuni.social.common.api.model.user.SocialuniUserRO;
import com.socialuni.social.common.api.utils.SocialTokenFacade;
import com.socialuni.social.common.sdk.constant.SocialuniConst;
import com.socialuni.social.common.sdk.constant.VisibleType;
import com.socialuni.social.follow.facotry.SocialuniContentUserROFactory;
import com.socialuni.social.official.logic.TestTokenRepository;
import com.socialuni.social.official.logic.TestUserRepository;
import com.socialuni.social.official.model.TokenDO;
import com.socialuni.social.official.model.TokenSocialuniTokenDO;
import com.socialuni.social.official.model.UserDO;
import com.socialuni.social.official.service.TestUserService;
import com.socialuni.social.sdk.logic.service.SocialuniCommentService;
import com.socialuni.social.sdk.logic.service.talk.SocialuniTalkService;
import com.socialuni.social.community.sdk.model.QO.comment.SocialuniCommentPostQO;
import com.socialuni.social.community.sdk.model.QO.talk.SocialuniTalkPostQO;
import com.socialuni.social.community.sdk.model.RO.talk.SocialuniCommentRO;
import com.socialuni.social.community.sdk.model.RO.talk.SocialuniTalkRO;
import com.socialuni.social.common.api.constant.GenderType;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.tance.dev.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.dao.DO.SocialuniTokenDO;
import com.socialuni.social.user.sdk.logic.domain.SocialuniLoginDomain;
import com.socialuni.social.user.sdk.logic.manage.SocialUserManage;
import com.socialuni.social.user.sdk.model.RO.login.SocialLoginRO;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("socialuni/official")
public class SocialuniOfficialController {
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
    @Resource
    SocialUserManage socialUserManage;
    @Resource
    SocialuniLoginDomain socialuniLoginDomain;


    @GetMapping("login")
    public ResultRO<SocialLoginRO<SocialuniUserRO>> login(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }

        SocialuniUserDo socialuniUserDo = socialUserManage.createUserByNickname(name, SocialuniUnionIdFacede.createUserUnionId());

        SocialLoginRO<SocialuniUserRO> socialLoginRO = socialuniLoginDomain.getSocialLoginROByMineUser(socialuniUserDo, SocialuniLoginType.nickname);

//        TokenSocialuniTokenDO socialuniTokenDO = testUserService.getSocialuniToken(tokenDO.getToken());

        return ResultRO.success(socialLoginRO);
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
