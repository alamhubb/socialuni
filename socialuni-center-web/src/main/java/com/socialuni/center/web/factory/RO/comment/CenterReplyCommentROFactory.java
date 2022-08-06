package com.socialuni.center.web.factory.RO.comment;

import com.socialuni.center.web.factory.RO.user.CenterUserROFactory;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.center.web.model.RO.community.comment.SocialReplyCommentRO;
import com.socialuni.center.web.model.RO.talk.CenterReplyCommentRO;
import com.socialuni.center.web.model.RO.user.CenterUserRO;
import com.socialuni.center.web.utils.UnionIdDbUtil;
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
        Integer id = UnionIdDbUtil.createCommentUid(commentRO);
        CenterUserRO userRO = CenterUserROFactory.getUserRO(commentRO.getUser(), mineUser);

        CenterReplyCommentRO centerReplyCommentRO = new CenterReplyCommentRO();
        centerReplyCommentRO.setId(id);
        centerReplyCommentRO.setUser(userRO);
        return centerReplyCommentRO;
    }
}