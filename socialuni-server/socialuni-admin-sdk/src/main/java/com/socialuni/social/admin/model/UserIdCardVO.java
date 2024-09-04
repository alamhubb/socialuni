package com.socialuni.social.admin.model;/*
package com.socialuni.admin.web.model;

import com.socialuni.social.model.DO.user.SocialSocialUserDO;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

*/
/**
 * @author qinkaiyuan
 * @date 2019-09-04 23:24
 *//*

@Data
public class UserIdCardVO {
    //用户的id
    private Long userId;
    private String nickname;
    private String gender;
    private List<UserImgVO> imgs;
    private String idCard;
    private Date createDate;

    public UserIdCardVO() {
    }

    public UserIdCardVO(SocialSocialUserDO user) {
        this.userId = user.getId();
        this.nickname = user.getNickname();
        this.gender = user.getGender();
//        this.imgs = UserImgVO.userImgDOToVOS(user.getImgs());
        */
/*if (user.getIdCards().size() > 0) {
            this.idCard = user.getIdCards().get(0).getFrontIdCardImg();
        }*//*

        this.createDate = user.getCreateTime();
    }

    public static List<UserIdCardVO> userDOToVOS(List<SocialSocialUserDO> userDOs) {
        return userDOs.stream().map(UserIdCardVO::new).collect(Collectors.toList());
    }
}
*/
