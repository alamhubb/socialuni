package com.socialuni.social.community.sdk.logic.domain.hug;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.community.sdk.dao.DO.SocialuniHugDO;
import com.socialuni.social.community.sdk.dao.DO.SocialuniTalkDO;
import com.socialuni.social.community.sdk.redis.HugRedis;
import com.socialuni.social.community.sdk.dao.SocialuniTalkDORedis;
import com.socialuni.social.community.sdk.dao.SocialuniTalkDOUtil;
import com.socialuni.social.community.sdk.model.QO.community.SocialHugAddQO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class SocialAddHugDomain {
    @Resource
    private SocialuniTalkDORedis talkRedis;

    @Resource
    private HugRedis hugRedis;

    public ResultRO<Void> addHug(SocialuniUserDo mineUser, SocialHugAddQO socialHugAddQO) {
        if (socialHugAddQO.getTalkId() != null) {
            SocialuniTalkDO talkDO = SocialuniTalkDOUtil.getTalkNotNull(socialHugAddQO.getTalkId());
            SocialuniHugDO HugDO = new SocialuniHugDO(mineUser.getUnionId(), socialHugAddQO.getTalkId(), socialHugAddQO.getCommentId());
            hugRedis.save(HugDO);
            Integer hugNum = talkDO.getHugNum();
            if (hugNum == null) {
                talkDO.setHugNum(1);
            } else {
                talkDO.setHugNum(++hugNum);
            }
            talkDO.setUpdateTime(new Date());
            talkRedis.save(talkDO);
        }/* else if (addVO.getCommentId() != null) {
            Optional<CommentDO> commentOptional = commentRepository.findById(addVO.getCommentId());
            if (!commentOptional.isPresent()) {
                return new ResultRO<>("无法抱抱不存在的评论");
            }
            CommentDO comment = commentOptional.get();
            HugDO hugDO = addVO.toDO(comment);
            hugRepository.save(hugDO);
            Integer hugNum = comment.getHugNum();
            if (hugNum == null) {
                comment.setHugNum(1);
            } else {
                comment.setHugNum(++hugNum);
            }
            comment.setUpdateTime(new Date());
            commentRepository.save(comment);
        }*/
        return new ResultRO<>();
    }

    /*private Integer getCommentNo(CommentAddVO addVO) {
        Talk talk = new Talk(addVO.getTalkId());
        Comment commentNo = commentRepository.findFirstByTalkOrderByNoDesc(talk);
        ChildComment childCommentNo = childRep.findFirstByTalkOrderByNoDesc(talk);
        Integer no = 0;
        if (commentNo != null && childCommentNo != null) {
            if (commentNo.getNo() > childCommentNo.getNo()) {
                no = commentNo.getNo();
            } else {
                no = childCommentNo.getNo();
            }
        } else if (commentNo != null) {
            no = commentNo.getNo();
        } else if (childCommentNo != null) {
            no = childCommentNo.getNo();
        }
        return no;
    }*/

/*else {
        ChildComment comment = childRep.save(addVO.toChildComment(user, no));
        entityManager.clear();
        comment = childRep.findById(comment.getId()).get();
        //        messagingTemplate.convertAndSendToUser(user.getName().equals("q") ? "k" : "q", "/queue/notifications", comment);
        String username = user.getName();
        String replyUsername = username.equals("q") ? "k" : "q";
        SocketMsg socketMsg = new SocketMsg(username, replyUsername, comment.getContent());
        messagingTemplate.convertAndSendToUser(replyUsername, "/queue/notifications", socketMsg);
        //获取所有说说
        return comment;
    }*/
}
