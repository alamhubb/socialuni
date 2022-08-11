package com.socialuni.sdk.serive;


import com.socialuni.sdk.domain.comment.CenterCommentDeleteDomain;
import com.socialuni.sdk.domain.comment.CenterCommentPostDomain;
import com.socialuni.sdk.feignAPI.SocialuniCommentAPI;
import com.socialuni.sdk.model.QO.comment.CenterCommentDeleteQO;
import com.socialuni.sdk.model.QO.comment.CenterCommentPostQO;
import com.socialuni.sdk.model.RO.talk.CenterCommentRO;
import com.socialuni.social.api.model.ResultRO;
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
        CenterCommentRO centerCommentRO = centerCommentPostDomain.postComment(centerCommentPostQO);
        return ResultRO.success(centerCommentRO);
    }

    public ResultRO<Void> deleteComment(CenterCommentDeleteQO commentDeleteQO) {
        centerCommentDeleteDomain.deleteComment(commentDeleteQO);
        return new ResultRO<>();
    }
}
