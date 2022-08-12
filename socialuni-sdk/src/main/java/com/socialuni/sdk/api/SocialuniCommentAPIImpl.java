package com.socialuni.sdk.api;

import com.socialuni.sdk.feignAPI.SocialuniCommentAPI;
import com.socialuni.sdk.model.QO.comment.CenterCommentDeleteQO;
import com.socialuni.sdk.model.QO.comment.CenterCommentPostQO;
import com.socialuni.sdk.model.RO.talk.CenterCommentRO;
import com.socialuni.sdk.serive.CenterCommentService;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialuniCommentAPIImpl implements SocialuniCommentAPI {
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