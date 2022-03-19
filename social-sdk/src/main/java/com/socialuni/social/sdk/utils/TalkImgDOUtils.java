package com.socialuni.social.sdk.utils;

import com.socialuni.social.entity.model.DO.talk.SocialTalkImgDO;
import com.socialuni.social.sdk.repository.community.TalkImgRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Slf4j
public class TalkImgDOUtils {
    private static TalkImgRepository talkImgRepository;

    @Resource
    public void setTalkImgRepository(TalkImgRepository talkImgRepository) {
        TalkImgDOUtils.talkImgRepository = talkImgRepository;
    }


    //根据id列表从缓存中读取talk列表
    public static List<SocialTalkImgDO> findTop3ByTalkId(Integer talkId) {
        return talkImgRepository.findTop3ByContentId(talkId);
    }
}