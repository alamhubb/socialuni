package com.socialuni.sdk.feignAPI;

import com.socialuni.sdk.model.QO.comment.CenterCommentDeleteQO;
import com.socialuni.sdk.model.QO.comment.CenterCommentPostQO;
import com.socialuni.sdk.model.RO.talk.CenterCommentRO;
import com.socialuni.social.web.sdk.model.ResultRO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("socialuni/comment")
@FeignClient(name = "comment", url = "${socialuni.central-server-url:https://api.socialuni.cn}")
@Tag(name = "社区模块/评论模块")
public interface SocialuniCommentAPI {
    @PostMapping("postComment")
    @Operation(summary = "发表评论")
    ResultRO<CenterCommentRO> postComment(@RequestBody @Valid CenterCommentPostQO centerCommentPostQO);

    @PostMapping("deleteComment")
    @Operation(summary = "删除评论")
    ResultRO<Void> deleteComment(@RequestBody @Valid CenterCommentDeleteQO commentDeleteQO);
}