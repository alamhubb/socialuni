package com.socialuni.api.feignAPI;

import com.socialuni.api.model.QO.comment.CenterCommentDeleteQO;
import com.socialuni.api.model.QO.comment.CenterCommentPostQO;
import com.socialuni.api.model.RO.talk.CenterCommentRO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("comment")
@FeignClient(name = "comment", url = "${socialuni.server-url:https://api.socialuni.com}")
public interface SocialuniCommentAPI {
    @PostMapping("postComment")
    ResultRO<CenterCommentRO> postComment(@RequestBody @Valid CenterCommentPostQO commentVO);

    @PostMapping("deleteComment")
    ResultRO<Void> deleteComment(@RequestBody @Valid CenterCommentDeleteQO commentVO);
}