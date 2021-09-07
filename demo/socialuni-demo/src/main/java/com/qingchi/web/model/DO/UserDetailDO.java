package com.qingchi.web.model.DO;

import com.qingchi.web.constant.MatchConstants;
import com.socialuni.social.constant.ConstStatus;
import com.socialuni.social.entity.model.DO.CommonBaseDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Date;

/**
 * @author qinkaiyuan
 * @date 2019-10-31 13:31
 * 用户详细信息，只有查看用户详情时才需要的信息
 */
//本应用专属，与外界应用无关
@Data
@Entity
@NoArgsConstructor
@Table(name = "user_detail",
        //手机号唯一
        uniqueConstraints = {
                //user唯一，索引列不能为空
                @UniqueConstraint(columnNames = "userId")
        }
)
public class UserDetailDO extends CommonBaseDO {
    @Column(nullable = false)
    private Integer userId;
    /**
     * 微信账户，用户可以关联微信账户，方便添加好友
     */
    private String wxAccount;
    private String qqAccount;
    private String wbAccount;
    private String contactAccount;
    //开放联系方式
    @Column(nullable = false)
    private Boolean openContact;
    //经验值。以后不再需要正义值和爱心值。
    private Integer experience;
    //他人向你发起消息需要支付贝壳数量
    @Column(nullable = false)
    private Integer beSponsorMsgShell;


    private Integer talkQueryDistrictId;
    private Integer talkAddDistrictId;
    //用户当前是否启用了为附近功能 0初始，1使用，2不使用
    //暂时不使用这个字段
//    private Integer useNearby;
    @Column(nullable = false)
    private Date updateTime;

    //性别是否修改过，只能修改一次，怎么提示
    @Column(nullable = false)
    private Boolean genderModified;
    //是否进行了本人照片认证
    //会在头像旁边显示
    @Column(nullable = false)
    private Boolean isSelfAuth;
    //是否为vip，
    @Column(nullable = false)
    private Boolean vipFlag;
    //已开vip多少个月
    @Column(nullable = false)
    private Boolean yearVipFlag;
    //经验等级
    private Integer gradeLevel;
    private Integer wealthLevel;

    /**
     * 用户信息版本号
     */
    private Integer versionNo;

    //设置 ：级联 保存/新建 操作 。新建 学校和学生 的时候，保存新建的学校那么新建的学生也同时被保存
    //查询 图片状态为已审核过和可用的，未认证时，正常图片为可用，认证后正常图片为已审核过
    //, fetch = FetchType.EAGER 因为设置了join所以不需要设置及时加载了，默认就是

    @Column(nullable = false)
    private Integer faceRank;
    @Column(nullable = false)
    private Integer faceRatio;
    @Column(nullable = false)
    private Integer baseFaceRatio;
    @Column(nullable = false)
    private Integer faceValue;
    //拥有的清池币数量
    @Column(nullable = false)
    private Integer qcb;
    //贝壳，单位毛
    @Column(nullable = false)
    private Integer shell;
    //单位等于分
    @Column(nullable = false)
    //爱心值、正义值、与登记相关
    private Integer loveValue;
    @Column(nullable = false)
    private Integer justiceValue;


    //从来不会在前台展示
    /**
     * 被人查看的次数
     */
    @Column(nullable = false)
    private Integer seeCount;
    /**
     * 被人喜欢的次数
     */
    @Column(nullable = false)
    private Integer likeCount;

    /**
     * 被人喜欢的次数
     */
    @Column(nullable = false)
    private boolean openMatch;
    //认证次数，3次以上需要咨询客服
    @Column(nullable = false)
    private Integer authNum;
    //身份证四个状态 初始，待审核，已认证，未通过认证
    @Column(nullable = false)
    private String idCardStatus;

    //用户是否在线，
    @Column(nullable = false)
    private Boolean onlineFlag;

    //用户最后在线时间，
    @Column(nullable = false, columnDefinition = "timestamp default current_timestamp")
    private Date lastOnlineTime;

    //邀请码，你分享给别人的邀请码
//    @Column(nullable = false)
//    private String inviteCode;
    //注册码，别人分享给你的邀请码
//    private String registerInviteCode;
    //邀请你的用户
//    private Integer registerInviteUserId;
    @Column(nullable = false)
    private Integer reportNum;


    /**
     * 被违规的次数，默认0
     */
    @Column(nullable = false)
    private Integer violationCount;

    /**
     * 违规原因
     */
    private String violationReason;

    /**
     * 上次展示广告的时间
     */
    @Column(nullable = false, columnDefinition = "timestamp default current_timestamp")
    private Date lastShowAdTime;

    //vip到期时间
    private Date vipEndDate;

    /**
     * 封禁开始时间
     */
    private Date violationStartTime;

    /**
     * 封禁结束时间
     */
    private Date violationEndTime;

    public UserDetailDO(UserDO userDO) {
        this.userId = userDO.getId();
//        this.setWxAccount("");
//        this.setQqAccount("");
//        this.setWbAccount("");
//        this.setContactAccount("");
        this.setOpenContact(false);

        Date curDate = new Date();
        this.setExperience(0);
        this.setBeSponsorMsgShell(5);
        this.setCreateTime(curDate);
        this.setUpdateTime(curDate);

        this.setGenderModified(false);
        this.setIsSelfAuth(false);
        this.setVipFlag(false);
        this.setYearVipFlag(false);
        this.setGradeLevel(0);

        this.setWealthLevel(0);
        this.setVersionNo(1);
        this.setFaceRank(0);

        this.setFaceRatio(0);
        this.setBaseFaceRatio(0);
        this.setFaceValue(0);
        this.setQcb(0);
        this.setShell(0);
        this.setLoveValue(0);

        this.setJusticeValue(0);
        this.setSeeCount(MatchConstants.INIT_FACE_SEE_NUM);
        this.setLikeCount(0);
        this.setOpenMatch(true);
        this.setAuthNum(0);

        this.setIdCardStatus(ConstStatus.init);
        this.setOnlineFlag(true);
        this.setLastOnlineTime(curDate);

        this.setReportNum(0);
        this.setViolationCount(0);
        this.setLastShowAdTime(curDate);
    }
}
