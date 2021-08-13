package com.socialuni.social.web.controller;

import com.socialuni.social.sdk.service.comment.SocialCommentService;
import com.socialuni.social.model.model.QO.community.comment.SocialCommentDeleteQO;
import com.socialuni.social.model.model.QO.community.comment.SocialCommentPostQO;
import com.socialuni.social.model.model.RO.ResultRO;
import com.socialuni.social.model.model.RO.community.comment.SocialCommentRO;
import com.socialuni.social.sdk.url.community.SocialuniCommentUrl;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SocialCommentController implements SocialuniCommentUrl {

    @Resource
    SocialCommentService socialCommentService;

    @Override
    public ResultRO<SocialCommentRO> postComment(SocialCommentPostQO socialCommentPostQO) {
        return socialCommentService.postComment(socialCommentPostQO);
    }

    @Override
    public ResultRO<Void> deleteComment(SocialCommentDeleteQO commentDeleteQO) {
        return socialCommentService.deleteComment(commentDeleteQO);
    }
}