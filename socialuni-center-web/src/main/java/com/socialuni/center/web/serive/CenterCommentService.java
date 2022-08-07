package com.socialuni.center.web.serive;


import com.socialuni.center.web.config.SocialAppConfig;
import com.socialuni.center.web.domain.comment.CenterCommentDeleteDomain;
import com.socialuni.center.web.domain.comment.CenterCommentPostDomain;
import com.socialuni.center.web.feignAPI.SocialuniCommentAPI;
import com.socialuni.center.web.model.QO.comment.CenterCommentDeleteQO;
import com.socialuni.center.web.model.QO.comment.CenterCommentPostQO;
import com.socialuni.center.web.model.RO.community.UniContentIdRO;
import com.socialuni.center.web.model.RO.talk.CenterCommentRO;
import com.socialuni.center.web.utils.UniAPIUtils;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author qinkaiyuan
 * @date 2020-05-16 20:11
 */
@Service
@Slf4j
public class CenterCommentService {
    @Resource
    CenterCommentPostDomain centerCommentPostDomain;
    @Resource
    CenterCommentDeleteDomain centerCommentDeleteDomain;
    @Resource
    SocialuniCommentAPI socialuniCommentAPI;

    public ResultRO<CenterCommentRO> postComment(CenterCommentPostQO centerCommentPostQO) {
        //校验是否触发关键词，如果触发生成举报，修改动态为预审查，只能用户自己可见
        UniContentIdRO uniContentIdRO = UniAPIUtils.callUniAPI(ContentType.comment, centerCommentPostDomain::postComment, socialuniCommentAPI::postComment, centerCommentPostQO);
        CenterCommentRO centerCommentRO = (CenterCommentRO) uniContentIdRO;
        return new ResultRO<>(centerCommentRO);
    }

    public ResultRO<Void> deleteComment(CenterCommentDeleteQO commentDeleteQO) {
        centerCommentDeleteDomain.deleteComment(commentDeleteQO);
        return new ResultRO<>();
    }
}
