package com.socialuni.social.sdk.model;

import com.socialuni.social.user.sdk.model.SocialuniUserDO;
import lombok.Data;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
public class NotifyUserVO {
    private String id;
    private String nickname;
    private String avatar;
    private Boolean vipFlag;
    public Boolean hasRead;

    public NotifyUserVO() {
    }

    //与其他业务不同，推送，所以是给接收方看的，为接收方id
    public NotifyUserVO(SocialuniUserDO sendUser, Integer receiveUserId) {
//        Integer devId = DevAccountUtils.getDevId();
//        this.id = UnionIdDbUtil.createUserUid(sendUser.getId(),  receiveUserId);
        this.nickname = sendUser.getNickname();
        this.avatar = sendUser.getAvatar();
//        this.vipFlag = sendUser.getVipFlag();
        this.hasRead = false;
    }
}
