package com.socialuni.sdk.logic.manage.talk;

import com.socialuni.sdk.dao.DO.talk.SocialTalkDO;
import com.socialuni.sdk.dao.repository.community.TalkRepository;
import com.socialuni.sdk.dao.store.TalkQueryStore;
import com.socialuni.sdk.utils.TalkRedis;
import com.socialuni.sdk.utils.TalkUtils;
import com.socialuni.sdk.utils.UnionIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
@Slf4j
public class TalkManage {
    public final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private TalkRepository talkRepository;
    @Resource
    private TalkRedis talkRedis;
    @Resource
    private TalkQueryStore talkQueryStore;

    /**
     * 需要对talk进行操作，必传talk不可精简
     *
     * @param talk
     * @return
     */
    public void updateTalkByAddComment(String talkUId) {
        Integer talkId = UnionIdUtil.getUnionIdByUuidNotNull(talkUId);
        SocialTalkDO talk = TalkUtils.getAllowNull(talkId);
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
