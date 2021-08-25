package com.socialuni.social.sdk.domain.hug;

import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.entity.model.DO.HugDO;
import com.socialuni.social.entity.model.DO.talk.TalkDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.community.SocialHugAddQO;
import com.socialuni.social.sdk.redis.HugRedis;
import com.socialuni.social.sdk.utils.TalkStore;
import com.socialuni.social.sdk.utils.TalkUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class SocialAddHugDomain {
    @Resource
    private TalkStore talkStore;

    @Resource
    private HugRedis hugRedis;

    public ResultRO<Void> addHug(UserDO mineUser, SocialHugAddQO socialHugAddQO) {
        if (socialHugAddQO.getTalkId() != null) {
            TalkDO talkDO = TalkUtils.get(socialHugAddQO.getTalkId());
            HugDO hugDO = new HugDO(mineUser.getId(), socialHugAddQO.getTalkId(), socialHugAddQO.getCommentId());
            hugRedis.save(hugDO);
            Integer hugNum = talkDO.getHugNum();
            if (hugNum == null) {
                talkDO.setHugNum(1);
            } else {
                talkDO.setHugNum(++hugNum);
            }
            talkDO.setUpdateTime(new Date());
            talkStore.save(talkDO);
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
