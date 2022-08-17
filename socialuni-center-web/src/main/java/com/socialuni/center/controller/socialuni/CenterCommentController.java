package com.socialuni.center.controller.socialuni;

import com.socialuni.sdk.model.QO.comment.CenterCommentDeleteQO;
import com.socialuni.sdk.model.QO.comment.CenterCommentPostQO;
import com.socialuni.sdk.model.RO.talk.CenterCommentRO;
import com.socialuni.sdk.serive.CenterCommentService;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class CenterCommentController {
    @Resource
    CenterCommentService centerCommentService;

    @PostMapping("postComment")
    public ResultRO<CenterCommentRO> postComment(@RequestBody @Valid CenterCommentPostQO centerCommentPostQO) {
        return centerCommentService.postComment(centerCommentPostQO);
    }

    @PostMapping("deleteComment")
    public ResultRO<Void> deleteComment(@RequestBody @Valid CenterCommentDeleteQO commentDeleteQO) {
        return centerCommentService.deleteComment(commentDeleteQO);
    }
}