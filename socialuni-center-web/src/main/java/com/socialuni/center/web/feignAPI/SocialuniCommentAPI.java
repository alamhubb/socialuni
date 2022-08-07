package com.socialuni.center.web.feignAPI;

import com.socialuni.center.web.model.QO.comment.CenterCommentDeleteQO;
import com.socialuni.center.web.model.QO.comment.CenterCommentPostQO;
import com.socialuni.center.web.model.RO.talk.CenterCommentRO;
import com.socialuni.social.api.model.ResultRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.net.URI;
import java.util.Map;

@RequestMapping("comment")
@FeignClient(name = "comment", url = "${socialuni.server-url:https://api.socialuni.cn}")
public interface SocialuniCommentAPI {
    @PostMapping("postComment")
    ResultRO<CenterCommentRO> postComment(URI baseUrl, @RequestHeader Map<String, Object> headerMap, @RequestBody @Valid CenterCommentPostQO commentVO);

    @PostMapping("deleteComment")
    ResultRO<Void> deleteComment(@RequestBody @Valid CenterCommentDeleteQO commentVO);
}