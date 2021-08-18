package com.socialuni.social.sdk.redis;

import com.socialuni.social.entity.model.DO.talk.TalkDO;
import com.socialuni.social.sdk.repository.TalkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TalkRedis {
    public final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private TalkRepository talkRepository;


    @Cacheable(cacheNames = "talkById", key = "#p0")
    public TalkDO findById(Integer id) {
        return talkRepository.findOneById(id);
    }
}


