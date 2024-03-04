package com.socialuni.social.user.sdk.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
public class UserDetailVO {
    private String id;
    private String nickname;
    private String gender;
    private Integer age;
    private String location;
    private String city;
    private String avatar;
    private String birthday;
    private String idCardStatus;
    private Boolean genderModified;
    //显示用户颜值
    private Integer faceRatio;
    private Integer likeCount;
    private List<UserImgVO> imgs;

    private Boolean onlineFlag;
    //用户最后在线时间，
    private Date lastOnlineTime;
    //是否为vip，
    private Boolean vipFlag;
    //已开vip多少个月
    private Boolean yearVipFlag;

    //邀请码，你分享给别人的邀请码
    private String inviteCode;
    //邀请人，谁邀请的你
//    private MatchUserVO registerInviteUser;
    //vip到期时间
    private Date vipEndDate;
    //清池币数量
    private Integer qcb;

    private Integer fansNum;
    private Integer followNum;
    private String userType;

    /**
     * 微信账户，用户可以关联微信账户，方便添加好友
     */
    private String wxAccount;
    private String qqAccount;
    private String wbAccount;
    private String contactAccount;
    //他人发起消息需要支付的金额
    private Integer beSponsorMsgShell;
    private Boolean openContact;
    private Boolean showUserContact;
    //    private DistrictVO talkQueryDistrict;
//    private DistrictVO talkAddDistrict;
    private Boolean hasFollowed;
    private Boolean beFollow;
    //暂时不使用这个字段
//    private Integer 使用附近;
    private String followStatus;
    private Integer loveValue;
    private Integer justiceValue;
    private String phoneNum;
    private Integer reportNum;
    private Boolean isMine;
    //认证次数，3次以上需要咨询客服
    private Integer authNum;
    //是否进行了本人照片认证
    private Boolean isSelfAuth;

    private Integer experience;
    private Integer shell;
    //经验等级
    private Integer gradeLevel;
    private Integer wealthLevel;
    //是否显示发送消息需要支付5B标示
    //通过对方是否关注了你，和你是否已经支付过5b
    private Boolean allowSendMsg;
    //通过对方是否关注了你，你是否是对方的好友，如果不是，你是否已经购买过msg
//    private Boolean showBuyMsg;
//    private ChatVO chat;
}
