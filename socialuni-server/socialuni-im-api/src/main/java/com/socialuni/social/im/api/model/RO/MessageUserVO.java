package com.socialuni.social.im.api.model.RO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author qinkaiyuan
 * @date 2019-08-14 17:24
 */
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//
@Component
public class MessageUserVO {
    // 前端遍历使用
    private Integer id;
    private String nickname;
    private String avatar;
    private Boolean vipFlag;

    public MessageUserVO() {
    }

    /*public MessageUserVO(Integer userId) {
        this.id = userId;
        Optional<UserDO> optionalUserDO = UserUtils.getUserOpt(userId);
        if (optionalUserDO.isPresent()) {
            UserDO user = optionalUserDO.get();
            //消息的用户
            this.nickname = user.getNickname();
            this.avatar = user.getAvatar();
            this.vipFlag = user.getVipFlag();
        } else {
            logger.error("不应该出现此种状况，根据UserId查不到User");
            ErrorLogUtils.save(new ErrorLogDO(userId, "MessageUserVO构造，userId得不到user", userId));
        }
    }*/
}
