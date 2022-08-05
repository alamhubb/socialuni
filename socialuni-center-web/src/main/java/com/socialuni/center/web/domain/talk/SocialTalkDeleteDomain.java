package com.socialuni.center.web.domain.talk;

import com.socialuni.social.constant.ContentStatus;
import com.socialuni.social.entity.model.DO.talk.TalkDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.center.web.repository.community.TalkRepository;
import com.socialuni.center.web.utils.TalkRedis;
import com.socialuni.social.model.model.QO.community.talk.SocialTalkDeleteQO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.procedure.ParameterMisuseException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2020-05-16 20:11
 */
@Service
@Slf4j
public class SocialTalkDeleteDomain {
    @Resource
    private TalkRepository talkRepository;
    @Resource
    private TalkRedis talkRedis;

    public void deleteTalk(UserDO mineUser, SocialTalkDeleteQO talkDeleteQO) {

        /**
         * 删除动态操作，
         * 如果是系统管理员删除动态，则必须填写原因，删除后发表动态的用户将被封禁
         * 如果是自己删的自己的动态，则不需要填写原因，默认原因是用户自己删除
         */
        TalkDO talkDO = talkRepository.findOneById(talkDeleteQO.getTalkId());
        //或者不为自己可见的状态
        if (talkDO == null || !ContentStatus.selfCanSeeContentStatus.contains(talkDO.getStatus())) {
            throw new ParameterMisuseException("无法删除不存在的动态");
        }
        //不是管理员的话就必须是自己删除自己
        //是否是自己删除自己的动态
        if (!talkDO.getUserId().equals(mineUser.getId())) {
            log.warn("有人尝试删除不属于自己的动态,用户名:{},id:{},尝试删除talkId：{}", mineUser.getNickname(), mineUser.getId(), talkDO.getId());
            throw new ParameterMisuseException("系统异常，无法删除不属于自己的动态");
        }
        talkDO.setUpdateTime(new Date());
        talkDO.setStatus(ContentStatus.delete);
        talkDO.setDeleteReason("用户自行删除");
        talkRedis.save(talkDO);
    }
}
