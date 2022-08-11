package com.socialuni.center.web.controller;

import com.socialuni.center.web.domain.comment.CenterCommentDeleteDomain;
import com.socialuni.center.web.domain.comment.CenterCommentPostDomain;
import com.socialuni.center.web.feignAPI.SocialuniCommentAPI;
import com.socialuni.center.web.model.QO.comment.CenterCommentDeleteQO;
import com.socialuni.center.web.model.QO.comment.CenterCommentPostQO;
import com.socialuni.center.web.model.RO.community.UniContentIdRO;
import com.socialuni.center.web.model.RO.talk.CenterCommentRO;
import com.socialuni.center.web.serive.CenterCommentService;
import com.socialuni.center.web.utils.UniAPIUtils;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.ContentType;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.URI;
import java.util.Map;

@RestController
public class CenterCommentController implements SocialuniCommentAPI {
    @Resource
    CenterCommentService centerCommentService;
    @Resource
    CenterCommentPostDomain centerCommentPostDomain;
    @Resource
    CenterCommentDeleteDomain centerCommentDeleteDomain;
    @Resource
    SocialuniCommentAPI socialuniCommentAPI;

    @Override
    public ResultRO<CenterCommentRO> postComment(URI baseUrl, Map<String, Object> headerMap, CenterCommentPostQO centerCommentPostQO) {
        return UniAPIUtils.callUniAPI(true, centerCommentService::postComment, socialuniCommentAPI::postComment, centerCommentPostQO);;
    }

    @Override
    public ResultRO<Void> deleteComment(CenterCommentDeleteQO commentDeleteQO) {
        return centerCommentService.deleteComment(commentDeleteQO);
    }
}