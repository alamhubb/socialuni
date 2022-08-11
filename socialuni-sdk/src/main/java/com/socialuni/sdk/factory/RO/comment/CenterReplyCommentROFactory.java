package com.socialuni.sdk.factory.RO.comment;

import com.socialuni.sdk.factory.RO.user.CenterUserROFactory;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.RO.community.comment.SocialReplyCommentRO;
import com.socialuni.sdk.model.RO.talk.CenterReplyCommentRO;
import com.socialuni.sdk.model.RO.user.CenterUserRO;
import com.socialuni.sdk.utils.UnionIdDbUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 不需要像帖子一样，每次有回复都刷新，因为不愁看，且你评论后的，有动静你会有通知
 */
@Data
@Component
@Slf4j
public class CenterReplyCommentROFactory {
    //需要user因为，user需要外部传入，区分center和social
    //用户详情
    public static CenterReplyCommentRO getReplyCommentRO(SocialReplyCommentRO commentRO, SocialUserDO mineUser) {
        String id = UnionIdDbUtil.getUidByUnionIdNotNull(commentRO.getId());
        CenterUserRO userRO = CenterUserROFactory.getUserRO(commentRO.getUser(), mineUser);

        CenterReplyCommentRO centerReplyCommentRO = new CenterReplyCommentRO();
        centerReplyCommentRO.setId(id);
        centerReplyCommentRO.setUser(userRO);
        return centerReplyCommentRO;
    }
}