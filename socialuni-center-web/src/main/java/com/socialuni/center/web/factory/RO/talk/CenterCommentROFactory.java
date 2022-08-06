package com.socialuni.center.web.factory.RO.talk;

import com.socialuni.center.web.model.RO.user.CenterUserRO;
import com.socialuni.center.web.factory.RO.comment.CenterReplyCommentROFactory;
import com.socialuni.center.web.factory.RO.user.CenterUserROFactory;
import com.socialuni.center.web.model.RO.talk.CenterCommentRO;
import com.socialuni.center.web.model.RO.talk.CenterReplyCommentRO;
import com.socialuni.center.web.utils.UnionIdDbUtil;
import com.socialuni.center.web.factory.ListConvertUtil;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.center.web.model.RO.community.comment.SocialCommentRO;
import com.socialuni.center.web.utils.common.BirthdayAgeUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 不需要像帖子一样，每次有回复都刷新，因为不愁看，且你评论后的，有动静你会有通知
 */
@Data
@Component
@Slf4j
public class CenterCommentROFactory {
    //需要user因为，user需要外部传入，区分center和social
    //用户详情
    public static CenterCommentRO getCommentRO(SocialCommentRO socialCommentRO, SocialUserDO mineUser) {
        Integer id = UnionIdDbUtil.createCommentUid(socialCommentRO);

        CenterUserRO userRO = CenterUserROFactory.getUserRO(socialCommentRO.getUser(), mineUser);

        List<CenterCommentRO> childComments = CenterCommentROFactory.getCommentROs(socialCommentRO.getChildComments(), mineUser);

        CenterCommentRO centerCommentRO = new CenterCommentRO();

        centerCommentRO.setId(id);
        centerCommentRO.setUser(userRO);
        centerCommentRO.setChildComments(childComments);
        centerCommentRO.setContentType(socialCommentRO.getContentType());

        centerCommentRO.setNo(socialCommentRO.getNo());
        centerCommentRO.setContent(BirthdayAgeUtil.replaceAgeBetween10to18Str(socialCommentRO.getContent()));
        centerCommentRO.setChildCommentNum(socialCommentRO.getChildCommentNum());
        centerCommentRO.setHugNum(socialCommentRO.getHugNum());
        centerCommentRO.setReportNum(socialCommentRO.getReportNum());

        if (socialCommentRO.getReplyComment() != null) {
            CenterReplyCommentRO replyComment = CenterReplyCommentROFactory.getReplyCommentRO(socialCommentRO.getReplyComment(), mineUser);
            centerCommentRO.setReplyComment(replyComment);
        }

        return centerCommentRO;
    }

    public static List<CenterCommentRO> getCommentROs(List<SocialCommentRO> ROS, SocialUserDO mineUser) {
        return ListConvertUtil.toList(CenterCommentROFactory::getCommentRO, ROS, mineUser);
    }
}