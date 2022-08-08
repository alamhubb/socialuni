package com.socialuni.center.web.domain.hug;

import com.socialuni.center.web.redis.HugRedis;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.center.web.model.DO.HugDO;
import com.socialuni.center.web.model.DO.talk.SocialTalkDO;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.center.web.model.QO.community.SocialHugAddQO;
import com.socialuni.center.web.utils.TalkRedis;
import com.socialuni.center.web.utils.TalkUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class SocialAddHugDomain {
    @Resource
    private TalkRedis talkRedis;

    @Resource
    private HugRedis hugRedis;

    public ResultRO<Void> addHug(SocialUserDO mineUser, SocialHugAddQO socialHugAddQO) {
        if (socialHugAddQO.getTalkId() != null) {
            SocialTalkDO talkDO = TalkUtils.get(socialHugAddQO.getTalkId());
            HugDO hugDO = new HugDO(mineUser.getUnionId(), socialHugAddQO.getTalkId(), socialHugAddQO.getCommentId());
            hugRedis.save(hugDO);
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
