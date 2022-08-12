package com.socialuni.sdk.feignAPI;

import com.socialuni.sdk.model.QO.comment.CenterCommentDeleteQO;
import com.socialuni.sdk.model.QO.comment.CenterCommentPostQO;
import com.socialuni.sdk.model.RO.talk.CenterCommentRO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("comment")
@FeignClient(name = "comment", url = "${socialuni.server-url:https://api.socialuni.cn}")
public interface SocialuniCommentAPI {
    @PostMapping("postComment")
    ResultRO<CenterCommentRO> postComment(@RequestBody @Valid CenterCommentPostQO commentVO);

    @PostMapping("deleteComment")
    ResultRO<Void> deleteComment(@RequestBody @Valid CenterCommentDeleteQO commentVO);
}