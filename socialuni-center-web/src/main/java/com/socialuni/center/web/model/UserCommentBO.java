package com.socialuni.center.web.model;

//import com.socialuni.center.web.utils.UnionIdDbUtil;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.center.web.model.RO.user.base.SocialUserRO;
import lombok.Data;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
public class UserCommentBO {
    private Integer id;
    private String nickname;
    private String avatar;
    private String gender;
    private Boolean vipFlag;

    public UserCommentBO() {
    }

    public UserCommentBO(SocialUserDO user, SocialUserDO reqUser) {
        if (user != null) {
//            this.id = UnionIdDbUtil.createUserUid(user.getId(),  reqUser);
            this.nickname = user.getNickname();
            this.gender = user.getGender();
            this.avatar = user.getAvatar();
//            this.vipFlag = user.getVipFlag();
        }
    }

    public SocialUserRO toVO() {
        SocialUserRO userCommentVO = new SocialUserRO();
        userCommentVO.setId(this.id);
        userCommentVO.setNickname(this.nickname);
        userCommentVO.setAvatar(this.avatar);
        userCommentVO.setGender(this.gender);
//        userCommentVO.setVipFlag(this.vipFlag);
        return userCommentVO;
    }
}
