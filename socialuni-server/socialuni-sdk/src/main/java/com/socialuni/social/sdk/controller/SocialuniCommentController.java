package com.socialuni.social.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.sdk.feignAPI.community.SocialuniCommentAPI;
import com.socialuni.social.sdk.logic.service.SocialuniCommentService;
import com.socialuni.social.community.sdk.model.QO.comment.SocialuniCommentDeleteQO;
import com.socialuni.social.community.sdk.model.QO.comment.SocialuniCommentPostQO;
import com.socialuni.social.community.sdk.model.RO.talk.SocialuniCommentRO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("socialuni/comment")
public class SocialuniCommentController implements SocialuniCommentAPI {
    @Resource
    SocialuniCommentService centerCommentService;

    @Override
    public ResultRO<SocialuniCommentRO> postComment(SocialuniCommentPostQO centerCommentPostQO) {
        return centerCommentService.postComment(centerCommentPostQO);
    }

    @Override
    public ResultRO<Void> deleteComment(SocialuniCommentDeleteQO commentDeleteQO) {
        return centerCommentService.deleteComment(commentDeleteQO);
    }
}