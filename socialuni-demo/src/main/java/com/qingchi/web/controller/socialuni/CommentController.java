package com.qingchi.web.controller.socialuni;

import com.qingchi.web.service.CommentService;
import com.socialuni.api.model.QO.comment.CenterCommentDeleteQO;
import com.socialuni.api.model.QO.comment.CenterCommentPostQO;
import com.socialuni.api.model.RO.talk.CenterCommentRO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("comment")
public class CommentController {
    @Resource
    CommentService commentService;

    @PostMapping("postComment")
    public ResultRO<CenterCommentRO> postComment(@RequestBody @Valid CenterCommentPostQO centerCommentPostQO) {
        return commentService.postComment(centerCommentPostQO);
    }

    @PostMapping("deleteComment")
    public ResultRO<Void> deleteComment(@RequestBody @Valid CenterCommentDeleteQO commentDeleteQO) {
        return commentService.deleteComment(commentDeleteQO);
    }
}