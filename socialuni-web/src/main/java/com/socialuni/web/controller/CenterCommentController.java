package com.socialuni.web.controller;

import com.socialuni.sdk.feignAPI.SocialuniCommentAPI;
import com.socialuni.sdk.model.QO.comment.CenterCommentDeleteQO;
import com.socialuni.sdk.model.QO.comment.CenterCommentPostQO;
import com.socialuni.sdk.model.RO.talk.CenterCommentRO;
import com.socialuni.sdk.serive.CenterCommentService;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.URI;
import java.util.Map;

@RestController
public class CenterCommentController implements SocialuniCommentAPI {
    @Resource
    CenterCommentService centerCommentService;

    @Override
    public ResultRO<CenterCommentRO> postComment(URI baseUrl, Map<String, Object> headerMap, CenterCommentPostQO centerCommentPostQO) {
        return centerCommentService.postComment(centerCommentPostQO);
    }

    @Override
    public ResultRO<Void> deleteComment(CenterCommentDeleteQO commentDeleteQO) {
        return centerCommentService.deleteComment(commentDeleteQO);
    }
}