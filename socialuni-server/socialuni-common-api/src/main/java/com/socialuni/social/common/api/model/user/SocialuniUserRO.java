package com.socialuni.social.common.api.model.user;

import com.socialuni.social.common.api.model.SocialuniContentIdRO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
@NoArgsConstructor
public class SocialuniUserRO extends SocialuniContentIdRO {
    private String nickname;
    //是否为当前用户
    private Boolean isMine;
    private String avatar;
    private String gender;
    private String type;
    //必须为string，返回给app后是uuid无法变为int
    private Integer age;
    private String city;

    public SocialuniUserRO(SocialuniUserRO userRO) {
        super(userRO);
        this.isMine = userRO.getIsMine();
        this.age = userRO.getAge();
        this.city = userRO.getCity();
        this.nickname = userRO.getNickname();
        this.avatar = userRO.getAvatar();
        this.gender = userRO.getGender();
        this.type = userRO.getType();
    }
}
