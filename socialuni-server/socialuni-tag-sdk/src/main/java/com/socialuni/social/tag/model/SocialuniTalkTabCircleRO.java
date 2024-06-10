package com.socialuni.social.tag.model;

import com.socialuni.social.tag.dao.DO.SocialuniCircleDO;
import lombok.Data;

/**
 * @author qinkaiyuan
 * @date 2020-05-23 17:21
 */
@Data
public class SocialuniTalkTabCircleRO {
    public String name;
    public String avatar;
    public String description;
    //群聊id
    public String groupChatId;
    private Integer talkCount;

    public SocialuniTalkTabCircleRO(SocialuniCircleDO circleDO) {
        this.name = circleDO.getName();
        this.avatar = circleDO.getAvatar();
        this.description = circleDO.getDescription();
        this.talkCount = circleDO.getTalkCount();
    }
}
