package com.socialuni.social.sdk.manage.talk;

import com.socialuni.social.entity.model.DO.talk.TalkDO;
import com.socialuni.social.sdk.repository.community.TalkRepository;
import com.socialuni.social.sdk.store.TalkQueryStore;
import com.socialuni.social.sdk.utils.TalkRedis;
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
    public TalkDO updateTalkByAddComment(TalkDO talk) {
        Integer commentNum = talk.getCommentNum();
        if (commentNum == null) {
            talk.setCommentNum(1);
        } else {
            talk.setCommentNum(++commentNum);
        }
        //更新talk更新时间
        talk.setUpdateTime(new Date());

        talk = talkRedis.save(talk);
        return talk;
    }
}
