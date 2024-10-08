package com.socialuni.social.sdk.logic.service;


import com.socialuni.social.common.sdk.feignAPI.community.SocialuniCommentAPI;
import com.socialuni.social.community.sdk.logic.domain.comment.SocialuniCommentDeleteDomain;
import com.socialuni.social.community.sdk.logic.domain.comment.SocialuniCommentPostDomain;
import com.socialuni.social.community.sdk.model.QO.comment.SocialuniCommentDeleteQO;
import com.socialuni.social.community.sdk.model.QO.comment.SocialuniCommentPostQO;
import com.socialuni.social.community.sdk.model.RO.talk.SocialuniCommentRO;
import com.socialuni.social.sdk.utils.UniAPIUtils;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.tance.dev.config.SocialuniDevConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author qinkaiyuan
 * @date 2020-05-16 20:11
 */
@Service
@Slf4j
public class SocialuniCommentService {
    @Resource
    SocialuniCommentPostDomain centerCommentPostDomain;
    @Resource
    SocialuniCommentDeleteDomain centerCommentDeleteDomain;
    @Resource
    SocialuniCommentAPI socialuniCommentAPI;

    public ResultRO<SocialuniCommentRO> postComment(SocialuniCommentPostQO centerCommentPostQO) {
        //校验是否触发关键词，如果触发生成举报，修改动态为预审查，只能用户自己可见
        SocialuniCommentRO centerCommentRO = centerCommentPostDomain.postComment(centerCommentPostQO);
        //如果应用，则调用中心
        if (SocialuniDevConfig.hasCenterServer()) {
//            return null;
            return UniAPIUtils.callUniAPIAndUpdateUid(centerCommentRO, socialuniCommentAPI::postComment, centerCommentPostQO);
        }
        return ResultRO.success(centerCommentRO);
    }

    public ResultRO<Void> deleteComment(SocialuniCommentDeleteQO commentDeleteQO) {
        centerCommentDeleteDomain.deleteComment(commentDeleteQO);
        if (SocialuniDevConfig.hasCenterServer()) {
            return socialuniCommentAPI.deleteComment(commentDeleteQO);
        }
        return new ResultRO<>();
    }
}
