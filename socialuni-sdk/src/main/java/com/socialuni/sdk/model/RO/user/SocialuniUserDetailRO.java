package com.socialuni.sdk.model.RO.user;

import lombok.Data;
import lombok.NoArgsConstructor;

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

    public SocialuniUserDetailRO(SocialuniUserDetailRO userRO) {
        super(userRO);
        this.imgs = userRO.getImgs();
        this.schoolName = userRO.getSchoolName();
    }

    public SocialuniUserDetailRO(SocialuniUserFollowDetailRO centerUserFollowDetailRO) {
        super(centerUserFollowDetailRO);
    }
}
