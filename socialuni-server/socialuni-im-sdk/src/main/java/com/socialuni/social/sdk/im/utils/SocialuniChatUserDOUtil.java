package com.socialuni.social.sdk.im.utils;

import com.socialuni.social.sdk.im.dao.DO.SocialuniChatUserDO;
import com.socialuni.social.sdk.im.dao.repository.SocialuniChatUserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SocialuniChatUserDOUtil {
    private static SocialuniChatUserRepository chatUserRepository;

    @Resource
    public void setChatUserRepository(SocialuniChatUserRepository chatUserRepository) {
        SocialuniChatUserDOUtil.chatUserRepository = chatUserRepository;
    }

    public static SocialuniChatUserDO findById(Integer id) {
        return chatUserRepository.findFirstById(id);
    }
}
