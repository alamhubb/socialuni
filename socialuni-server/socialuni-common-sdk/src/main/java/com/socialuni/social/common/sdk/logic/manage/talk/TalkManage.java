package com.socialuni.social.common.sdk.logic.manage.talk;

import com.socialuni.social.community.sdk.dao.DO.SocialuniTalkDO;
import com.socialuni.social.common.sdk.dao.utils.content.SocialuniTalkDORedis;
import com.socialuni.social.common.sdk.dao.utils.content.SocialuniTalkDOUtil;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
@Slf4j
public class TalkManage {
    @Resource
    private SocialuniTalkDORedis talkRedis;

    /**
     * 需要对talk进行操作，必传talk不可精简
     *
     * @param talk
     * @return
     */
    public void updateTalkByAddComment(String talkUuid) {
        Integer talkId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(talkUuid);
        SocialuniTalkDO talk = SocialuniTalkDOUtil.getTalkNotNull(talkId);
        if (talk == null) {
            return;
        }
        Integer commentNum = talk.getCommentNum();
        if (commentNum == null) {
            talk.setCommentNum(1);
        } else {
            talk.setCommentNum(++commentNum);
        }
        //更新talk更新时间
        talk.setUpdateTime(new Date());
        talkRedis.save(talk);
    }
}
