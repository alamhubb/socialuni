package com.socialuni.sdk.controller;

import com.socialuni.sdk.feignAPI.community.SocialuniCommentAPI;
import com.socialuni.sdk.logic.service.SocialuniCommentService;
import com.socialuni.sdk.model.QO.comment.SocialuniCommentDeleteQO;
import com.socialuni.sdk.model.QO.comment.SocialuniCommentPostQO;
import com.socialuni.sdk.model.RO.talk.SocialuniCommentRO;
import com.socialuni.social.common.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
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