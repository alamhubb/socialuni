package com.socialuni.social.sdk.logic.domain.talk;

import com.socialuni.social.sdk.constant.socialuni.ContentStatus;
import com.socialuni.social.community.sdk.model.SocialuniTalkModel;
import com.socialuni.social.user.sdk.model.SocialuniUserModel;
import com.socialuni.social.community.sdk.api.TalkApi;
import com.socialuni.social.sdk.dao.utils.content.SocialuniTalkDORedis;
import com.socialuni.social.sdk.model.QO.community.talk.SocialTalkDeleteQO;
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
    private TalkApi talkApi;
    @Resource
    private SocialuniTalkDORedis talkRedis;

    public void deleteTalk(SocialuniUserModel mineUser, SocialTalkDeleteQO talkDeleteQO) {

        /**
         * 删除动态操作，
         * 如果是系统管理员删除动态，则必须填写原因，删除后发表动态的用户将被封禁
         * 如果是自己删的自己的动态，则不需要填写原因，默认原因是用户自己删除
         */
        SocialuniTalkModel talkDO = talkApi.findOneByUnionId(talkDeleteQO.getTalkId());
        //或者不为自己可见的状态
        if (talkDO == null || !ContentStatus.selfCanSeeContentStatus.contains(talkDO.getStatus())) {
            throw new ParameterMisuseException("无法删除不存在的动态");
        }
        //不是管理员的话就必须是自己删除自己
        //是否是自己删除自己的动态
        if (!talkDO.getUserId().equals(mineUser.getUnionId())) {
            log.warn("有人尝试删除不属于自己的动态,用户名:{},id:{},尝试删除talkId：{}", mineUser.getNickname(), mineUser.getUnionId(), talkDO.getUnionId());
            throw new ParameterMisuseException("系统异常，无法删除不属于自己的动态");
        }
        talkDO.setUpdateTime(new Date());
        talkDO.setStatus(ContentStatus.delete);
        talkDO.setDeleteReason("用户自行删除");
        talkRedis.save(talkDO);
    }
}
