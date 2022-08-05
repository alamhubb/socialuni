package com.socialuni.center.web.url.community;

import com.socialuni.center.web.model.QO.community.comment.SocialCommentDeleteQO;
import com.socialuni.center.web.model.QO.community.comment.SocialCommentPostQO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.center.web.model.RO.community.comment.SocialCommentRO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("comment")
public interface SocialuniCommentUrl {
    @PostMapping("postComment")
    ResultRO<SocialCommentRO> postComment(@RequestBody @Valid SocialCommentPostQO commentVO);

    @PostMapping("deleteComment")
    ResultRO<Void> deleteComment(@RequestBody @Valid SocialCommentDeleteQO commentVO);
}