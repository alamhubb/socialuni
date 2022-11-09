package com.socialuni.social.sdk.feignAPI.community;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.sdk.model.QO.comment.SocialuniCommentDeleteQO;
import com.socialuni.social.sdk.model.QO.comment.SocialuniCommentPostQO;
import com.socialuni.social.sdk.model.RO.talk.SocialuniCommentRO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

//@RequestMapping("socialuni/comment")
@FeignClient(name = "comment", url = "${socialuni.central-server-url:https://api.socialuni.cn}", path = "socialuni/comment")
@Tag(name = "社区模块/评论模块")
public interface SocialuniCommentAPI {
    @PostMapping("postComment")
    @Operation(summary = "发表评论")
    ResultRO<SocialuniCommentRO> postComment(@RequestBody @Valid SocialuniCommentPostQO centerCommentPostQO);

    @PostMapping("deleteComment")
    @Operation(summary = "删除评论")
    ResultRO<Void> deleteComment(@RequestBody @Valid SocialuniCommentDeleteQO commentDeleteQO);
}