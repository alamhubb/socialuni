package com.socialuni.web.controller.socialuni;

import com.socialuni.sdk.api.SocialuniCommentAPIImpl;
import com.socialuni.sdk.feignAPI.SocialuniCommentAPI;
import com.socialuni.sdk.model.QO.comment.CenterCommentDeleteQO;
import com.socialuni.sdk.model.QO.comment.CenterCommentPostQO;
import com.socialuni.sdk.model.RO.talk.CenterCommentRO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CenterCommentController implements SocialuniCommentAPI {
    @Resource
    SocialuniCommentAPIImpl centerCommentAPIImpl;

    @Override
    public ResultRO<CenterCommentRO> postComment(CenterCommentPostQO centerCommentPostQO) {
        return centerCommentAPIImpl.postComment(centerCommentPostQO);
    }

    @Override
    public ResultRO<Void> deleteComment(CenterCommentDeleteQO commentDeleteQO) {
        return centerCommentAPIImpl.deleteComment(commentDeleteQO);
    }
}