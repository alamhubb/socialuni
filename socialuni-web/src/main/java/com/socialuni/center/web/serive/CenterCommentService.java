package com.socialuni.center.web.serive;


import com.socialuni.api.model.QO.comment.CenterCommentDeleteQO;
import com.socialuni.api.model.QO.comment.CenterCommentPostQO;
import com.socialuni.api.model.RO.talk.CenterCommentRO;
import com.socialuni.center.web.domain.comment.CenterCommentDeleteDomain;
import com.socialuni.center.web.domain.comment.CenterCommentPostDomain;
import com.socialuni.social.api.model.model.ResultRO;
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

    public ResultRO<CenterCommentRO> postComment(CenterCommentPostQO socialCommentPostQO) {

        CenterCommentRO socialCommentRO = centerCommentPostDomain.postComment(socialCommentPostQO);

        return new ResultRO<>(socialCommentRO);
    }

    public ResultRO<Void> deleteComment(CenterCommentDeleteQO commentDeleteQO) {
        centerCommentDeleteDomain.deleteComment(commentDeleteQO);
        return new ResultRO<>();
    }
}
