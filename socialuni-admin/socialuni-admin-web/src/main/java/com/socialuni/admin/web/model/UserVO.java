/*
package com.socialuni.admin.web.model;

import com.socialuni.social.model.DO.user.SocialSocialUserDO;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

*/
/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 *//*

@Data
public class UserVO {
    private Integer id;
    private String avatar;
    private String nickname;
    private String gender;
    private Integer age;
    private String city;
    private String birthday;
    private String idCardStatus;
    private List<UserImgVO> imgs;

    public UserVO() {
    }

    public UserVO(SocialSocialUserDO user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.gender = user.getGender();
        this.city = user.getLocation();
        this.age = user.getAge();
        this.birthday = user.getBirthday();
        this.idCardStatus = user.getIdCardStatus();
//        this.imgs = UserImgVO.userImgDOToVOS(user.getImgs());
        this.avatar = user.getAvatar();
    }

    public static List<UserVO> userDOToVOS(List<SocialSocialUserDO> userDOs) {
        return userDOs.stream().map(UserVO::new).collect(Collectors.toList());
    }
}
*/
