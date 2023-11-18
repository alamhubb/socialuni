package com.socialuni.social.music.sdk.check;

import lombok.Data;

@Data
public class SocialuniMusicOperateCheckRO {
    Integer chatId;
    Integer mineUserId;
    String roleId;

    public SocialuniMusicOperateCheckRO(Integer chatId, Integer mineUserId, String roleId) {
        this.chatId = chatId;
        this.mineUserId = mineUserId;
        this.roleId = roleId;
    }
}
