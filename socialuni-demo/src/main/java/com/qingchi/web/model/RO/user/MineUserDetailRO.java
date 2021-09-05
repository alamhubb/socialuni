package com.qingchi.web.model.RO.user;

import com.socialuni.api.model.RO.user.CenterMineUserDetailRO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
@NoArgsConstructor
public class MineUserDetailRO extends UserDetailRO {
    private String phoneNum;
    private String birthday;

    //邀请码，你分享给别人的邀请码
    private String inviteCode;
    //为自己才展示的属性
//    private Integer qcb; 旧版本已废弃
    private String userType;
    private Integer experience;
    private Date vipEndDate;
    private Integer likeCount;
    private Integer shell;
    private String idCardStatus;
    private Boolean genderModified;
    //认证次数，3次以上需要咨询客服
    private Integer authNum;
    //经验等级
    private Integer gradeLevel;
    private Integer wealthLevel;

    public MineUserDetailRO(UserDetailRO userDetailRO, CenterMineUserDetailRO mineUserDetailRO) {
        super(userDetailRO);
        this.birthday = mineUserDetailRO.getBirthday();
        this.phoneNum = mineUserDetailRO.getPhoneNum();
    }
}
