package com.socialuni.sdk.manage.talk;

import com.socialuni.entity.model.DO.talk.TalkDO;
import com.socialuni.sdk.repository.TalkRepository;
import com.socialuni.sdk.store.TalkQueryStore;
import com.socialuni.sdk.utils.TalkStore;
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
    private TalkStore talkStore;
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

        talk = talkStore.save(talk);
        return talk;
    }
}
