package com.socialuni.social.sdk.im.dao.redis;

import com.socialuni.social.common.sdk.constant.RedisKeysUtil;
import com.socialuni.social.common.sdk.utils.RedisUtil;
import com.socialuni.social.sdk.im.constant.ChatUserRedisKey;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.sdk.im.dao.repository.SocialuniChatUserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SocialuniChatUserRedis {
    @Resource
    RedisUtil redisUtil;
    @Resource
    SocialuniChatUserRepository chatUserRepository;

    public List<SocialuniChatUserDO> saveAllPut(List<SocialuniChatUserDO> socialuniChatUserDOs) {
        Set<String> redisKeys = new HashSet<>();
        for (SocialuniChatUserDO socialuniChatUserDO : socialuniChatUserDOs) {
            redisKeys.addAll(redisUtil.keys(RedisKeysUtil.getRedisKeys(ChatUserRedisKey.findByUserIdAndStatusOrderByUpdateTimeDesc, socialuniChatUserDO.getUserId()+"")));
            redisKeys.addAll(redisUtil.keys(RedisKeysUtil.getRedisKeys(ChatUserRedisKey.findFirstChatUserById, socialuniChatUserDO.getId()+"")));
        }
        //清除缓存
        redisUtil.del(redisKeys);
        return chatUserRepository.saveAllPut(socialuniChatUserDOs);
    }

}
