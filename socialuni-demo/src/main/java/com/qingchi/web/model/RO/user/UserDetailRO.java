package com.qingchi.web.model.RO.user;

import com.socialuni.api.model.RO.user.CenterUserDetailRO;
import com.socialuni.social.model.model.RO.message.chat.ChatRO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 */
@Data
@NoArgsConstructor
public class UserDetailRO extends CenterUserDetailRO {

    public UserDetailRO(CenterUserDetailRO userRO) {
        super(userRO);
        this.beFollow = userRO.getHasBeFollowed();
    }

    public UserDetailRO(UserDetailRO userRO) {
        super(userRO);
        this.wxAccount = userRO.getWxAccount();
        this.qqAccount = userRO.getQqAccount();
        this.wbAccount = userRO.getWbAccount();
        this.contactAccount = userRO.getContactAccount();
        this.beSponsorMsgShell = userRO.getBeSponsorMsgShell();
        this.openContact = userRO.getOpenContact();
        this.beFollow = userRO.getBeFollow();
        this.followStatus = userRO.getFollowStatus();
        this.location = userRO.getLocation();
        this.faceRatio = userRO.getFaceRatio();
        this.onlineFlag = userRO.getOnlineFlag();
        this.lastOnlineTime = userRO.getLastOnlineTime();
        this.vipFlag = userRO.getVipFlag();
        this.yearVipFlag = userRO.getYearVipFlag();
        this.showUserContact = userRO.getShowUserContact();
        this.loveValue = userRO.getLoveValue();
        this.justiceValue = userRO.getJusticeValue();
        this.reportNum = userRO.getReportNum();
        this.isSelfAuth = userRO.getIsSelfAuth();
        this.allowSendMsg = userRO.getAllowSendMsg();
        this.chat = userRO.getChat();
    }


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

    //不为自己也可展示的内容
    private Boolean beFollow;
    private String followStatus;
    private String location;
    //显示用户颜值
    private Integer faceRatio;
    private Boolean onlineFlag;
    //用户最后在线时间，
    private Date lastOnlineTime;
    //是否为vip，
    private Boolean vipFlag;
    //已开vip多少个月
    private Boolean yearVipFlag;
    //展示用户联系方式
    private Boolean showUserContact;

    //邀请人，谁邀请的你
//    private MatchUserVO registerInviteUser;
    //vip到期时间

    //清池币数量
    //    private DistrictVO talkQueryDistrict;
//    private DistrictVO talkAddDistrict;

    //暂时不使用这个字段
//    private Integer 使用附近;
    private Integer loveValue;
    private Integer justiceValue;
    private Integer reportNum;

    //是否进行了本人照片认证
    private Boolean isSelfAuth;

    //是否显示发送消息需要支付5B标示
    //通过对方是否关注了你，和你是否已经支付过5b ，旧版本默认不支持发消息，所以默认为false
    private Boolean allowSendMsg;
    //通过对方是否关注了你，你是否是对方的好友，如果不是，你是否已经购买过msg
//    private Boolean showBuyMsg;  旧版本就已弃用
    private ChatRO chat;
}
