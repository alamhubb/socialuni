package com.socialuni.social.common.api.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
@NoArgsConstructor
public class SocialuniUserDetailRO extends SocialuniUserFollowDetailRO {
    private List<SocialuniUserImgRO> imgs;
    private String schoolName;
    private Date lastOnlineTime;
    private String contactInfo;
    //为他人详情是，则意义为是否已经拥有查看用户联系方式的权限
    private Boolean openContactInfo;

    public SocialuniUserDetailRO(SocialuniUserDetailRO userRO) {
        super(userRO);
        this.imgs = userRO.getImgs();
        this.schoolName = userRO.getSchoolName();
        this.contactInfo = userRO.getContactInfo();
        this.openContactInfo = userRO.getOpenContactInfo();
    }

    public SocialuniUserDetailRO(SocialuniUserFollowDetailRO centerUserFollowDetailRO) {
        super(centerUserFollowDetailRO);
    }
}
