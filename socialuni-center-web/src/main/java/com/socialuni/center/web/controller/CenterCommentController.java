package com.socialuni.center.web.controller;

import com.socialuni.center.web.feignAPI.SocialuniCommentAPI;
import com.socialuni.center.web.model.QO.comment.CenterCommentDeleteQO;
import com.socialuni.center.web.model.QO.comment.CenterCommentPostQO;
import com.socialuni.center.web.model.RO.talk.CenterCommentRO;
import com.socialuni.center.web.serive.CenterCommentService;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CenterCommentController implements SocialuniCommentAPI {
    @Resource
    CenterCommentService centerCommentService;

    @Override
    public ResultRO<CenterCommentRO> postComment(CenterCommentPostQO centerCommentPostQO) {
        return centerCommentService.postComment(centerCommentPostQO);
    }

    @Override
    public ResultRO<Void> deleteComment(CenterCommentDeleteQO commentDeleteQO) {
        return centerCommentService.deleteComment(commentDeleteQO);
    }
}