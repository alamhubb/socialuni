package com.socialuni.social.sdk.url.community;

import com.socialuni.social.model.model.QO.community.comment.SocialCommentDeleteQO;
import com.socialuni.social.model.model.QO.community.comment.SocialCommentPostQO;
import com.socialuni.social.api.model.model.ResultRO;
import com.socialuni.social.model.model.RO.community.comment.SocialCommentRO;
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