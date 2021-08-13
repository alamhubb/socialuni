/*
package com.socialuni.center.web.model.BO;

import com.socialuni.center.web.constant.shell.ExpenseType;
import com.socialuni.center.web.model.DO.shell.UserContactDO;
import com.socialuni.center.web.model.DO.user.UserDetailDO;
import com.socialuni.center.web.model.RO.UserOAuthRO;
import com.socialuni.center.web.model.RO.match.MatchUserVO;
import com.socialuni.center.web.repository.shell.UserContactRepository;
import com.socialuni.center.web.repository.user.UserDetailRepository;
import com.socialuni.center.web.serive.chat.ChatService;
import com.socialuni.center.sdk.utils.CenterUserUtil;
import com.socialuni.center.sdk.utils.DevAccountUtils;
import com.socialuni.center.sdk.utils.UnionIdDbUtil;
import com.socialuni.constant.CommonStatus;
import com.socialuni.sdk.constant.FollowConst;
import com.socialuni.sdk.constant.status.ContentStatus;
import com.socialuni.sdk.model.ChatVO;
import com.socialuni.entity.model.DO.user.UserDO;
import com.socialuni.entity.model.DO.user.UserImgDO;
import com.socialuni.sdk.model.RO.DistrictVO;
import com.socialuni.sdk.model.RO.UserDetailVO;
import com.socialuni.sdk.model.RO.UserImgVO;
import com.socialuni.sdk.repository.ChatUserRepository;
import com.socialuni.sdk.repository.FollowRepository;
import com.socialuni.sdk.repository.UserImgRepository;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

*/
/**
 * @author qinkaiyuan 查询结果可以没有set和空构造，前台传值可以没有get
 * @date 2019-08-13 23:34
 *//*

@Data
@Component
public class UserDetailBO {
    private static UserDetailRepository userDetailRepository;


    private static UserContactRepository userContactRepository;
    private static ChatUserRepository chatUserRepository;
    private static UserImgRepository userImgRepository;

    @Resource
    public void setUserDetailRepository(UserDetailRepository userDetailRepository) {
        UserDetailBO.userDetailRepository = userDetailRepository;
    }

    @Resource
    public void setUserContactRepository(UserContactRepository userContactRepository) {
        UserDetailBO.userContactRepository = userContactRepository;
    }

    @Resource
    public void setUserImgRepository(UserImgRepository userImgRepository) {
        UserDetailBO.userImgRepository = userImgRepository;
    }

    private static FollowRepository followRepository;
    private static ChatService chatService;

    @Resource
    public void setChatService(ChatService chatService) {
        UserDetailBO.chatService = chatService;
    }

    @Resource
    public void setFollowRepository(FollowRepository followRepository) {
        UserDetailBO.followRepository = followRepository;
    }

    @Resource
    public void setChatUserRepository(ChatUserRepository chatUserRepository) {
        UserDetailBO.chatUserRepository = chatUserRepository;
    }

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
    private MatchUserVO registerInviteUser;
    //vip到期时间
    private Date vipEndDate;
    //清池币数量
    private Integer qcb;

    private Integer fansNum;
    private Integer followNum;
    private String userType;

    */
/**
     * 微信账户，用户可以关联微信账户，方便添加好友
     *//*

    private String wxAccount;
    private String qqAccount;
    private String wbAccount;
    private String contactAccount;
    //他人发起消息需要支付的金额
    private Integer beSponsorMsgShell;
    private Boolean openContact;
    private Boolean showUserContact;
    private DistrictVO talkQueryDistrict;
    private DistrictVO talkAddDistrict;
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
    private ChatVO chat;

    public UserDetailBO() {
    }

    public UserOAuthRO toSocialVO() {
        UserOAuthRO user = new UserOAuthRO();
        user.setUnionId(this.getId());
        user.setNickname(this.getNickname());
        user.setGender(this.getGender());
//        user.setCity(this.getLocation());
        user.setAvatar(this.getAvatar());
//        user.setToken(token);
        return user;
    }

    public UserDetailVO toVO() {

        UserDetailVO user = new UserDetailVO();

        user.setId(this.getId());
        user.setNickname(this.getNickname());
        user.setGender(this.getGender());
        user.setAge(this.getAge());
        user.setCity(this.getLocation());
        user.setLocation(this.getLocation());
        user.setAvatar(this.getAvatar());
        user.setBirthday(this.getBirthday());
        user.setIdCardStatus(this.getIdCardStatus());
        user.setGenderModified(this.getGenderModified());
        user.setFaceRatio(this.getFaceRatio());
        user.setLikeCount(this.getLikeCount());
        user.setImgs(this.getImgs());
        user.setOnlineFlag(this.getOnlineFlag());
        user.setLastOnlineTime(this.getLastOnlineTime());
        user.setVipFlag(this.getVipFlag());
        user.setYearVipFlag(this.getYearVipFlag());
        user.setInviteCode(this.getInviteCode());
        user.setVipEndDate(this.getVipEndDate());
        user.setQcb(this.getQcb());
        user.setFansNum(this.getFansNum());
        user.setFollowNum(this.getFollowNum());
        user.setUserType(this.getUserType());
        user.setWxAccount(this.getWxAccount());
        user.setQqAccount(this.getQqAccount());
        user.setWbAccount(this.getWbAccount());
        user.setContactAccount(this.getContactAccount());
        user.setBeSponsorMsgShell(this.getBeSponsorMsgShell());
        user.setOpenContact(this.getOpenContact());
        user.setShowUserContact(this.getShowUserContact());
        user.setHasFollowed(this.getHasFollowed());
        user.setBeFollow(this.getBeFollow());
        user.setFollowStatus(this.getFollowStatus());
        user.setLoveValue(this.getLoveValue());
        user.setJusticeValue(this.getJusticeValue());
        user.setPhoneNum(this.getPhoneNum());
        user.setReportNum(this.getReportNum());
        user.setIsMine(this.getIsMine());
        user.setAuthNum(this.getAuthNum());
        user.setIsSelfAuth(this.getIsSelfAuth());
        user.setExperience(this.getExperience());
        user.setShell(this.getShell());
        user.setGradeLevel(this.getGradeLevel());
        user.setWealthLevel(this.getWealthLevel());
        user.setAllowSendMsg(this.getAllowSendMsg());

        return user;

    }

    public UserDetailBO(UserDO user) {
        this(user, false);
    }

    public UserDetailBO(UserDO beSeeUser, Boolean isSeeMineDetail) {
        this(beSeeUser, isSeeMineDetail, DevAccountUtils.getDevId());
    }

    public UserDetailBO(UserDO beSeeUser, Boolean isSeeMineDetail, Integer devId) {
        UserDO sessionUser;
        //是否为自己查看自己的详情
        if (isSeeMineDetail) {
            sessionUser = beSeeUser;
        } else {
            sessionUser = CenterUserUtil.getMineUser();
        }
        this.id = UnionIdDbUtil.createUserUid(beSeeUser.getId(), sessionUser, devId);
        this.isMine = isSeeMineDetail;
        //toDO 查询mine的时候显示手机号，出生年月，别人详情时后台就控制不返回此类信息
        this.nickname = StringUtils.substring(beSeeUser.getNickname(), 0, 6);
        this.gender = beSeeUser.getGender();
//        this.location = beSeeUser.getCity();
        //前台使用了city
//        this.city = beSeeUser.getCity();
        this.age = beSeeUser.getAge();
        //满分10W /1千，得到百分之颜值分
//        this.faceRatio = (int) Math.ceil((double) beSeeUser.getFaceRatio() / MatchConstants.FACE_RATIO_BASE_MULTIPLE);
        //todo 这里可以修改为用户存着LIst《userImdId》然后每次不需要连表查询，只根据id查找就行
        List<UserImgDO> imgDOS = userImgRepository.findTop3ByUserIdAndStatusInOrderByCreateTimeDesc(beSeeUser.getId(), ContentStatus.otherCanSeeContentStatus);
        this.imgs = UserImgBO.userImgDOToVOS(imgDOS, sessionUser);
        this.avatar = beSeeUser.getAvatar();
        this.userType = beSeeUser.getType();

//        this.inviteCode = user.getInviteCode();
//        this.yearVipFlag = user.getYearVipFlag();
//        this.registerInviteUser = new MatchUserVO(user.getRegisterInviteUser());
//        this.qcb = user.getQcb();
        Optional<UserDetailDO> optionalUserDetailDO = userDetailRepository.findFirstOneByUserId(beSeeUser.getId());
        optionalUserDetailDO.ifPresent(userDetailDO -> {
            //this.talkQueryDistrict = new DistrictVO(userDetailDO.getTalkQueryDistrict());
            this.wxAccount = userDetailDO.getWxAccount();
            this.qqAccount = userDetailDO.getQqAccount();
            this.wbAccount = userDetailDO.getWbAccount();
            this.openContact = userDetailDO.getOpenContact();
            this.onlineFlag = userDetailDO.getOnlineFlag();
            this.lastOnlineTime = userDetailDO.getLastOnlineTime();
            this.vipFlag = userDetailDO.getVipFlag();
//            this.fansNum = userDetailDO.getFansNum();
            this.loveValue = userDetailDO.getLoveValue();
            this.justiceValue = userDetailDO.getJusticeValue();
            this.isSelfAuth = userDetailDO.getIsSelfAuth();

            this.beSponsorMsgShell = userDetailDO.getBeSponsorMsgShell();
            this.allowSendMsg = false;

            String contactAccount = userDetailDO.getContactAccount();
            if (isSeeMineDetail) {
                this.contactAccount = contactAccount;
                this.experience = userDetailDO.getExperience();
            } else {
                //如果未填写，则显示未填写
                //还有对方是否填写了联系方式
                if (StringUtils.isEmpty(contactAccount)) {
                    this.contactAccount = "";
                } else {
                    //是否开启了
                    if (this.openContact) {
                        //需要判断用户是否开启了openContact.如果未开启，则showUserContact为false
                        this.contactAccount = "***" + StringUtils.substring(contactAccount, -3);
                        this.showUserContact = false;
                        //如果为查看别人的详情，则会带着自己的用户信息
                        if (sessionUser != null) {
                            Optional<UserContactDO> userContactDOOptional = userContactRepository.findFirstByUserIdAndBeUserIdAndStatusAndType(
                                    sessionUser.getId(), beSeeUser.getId(), CommonStatus.enable, ExpenseType.contact);
                            if (userContactDOOptional.isPresent()) {
                                //这里需要确认用户是否已获取过对方的联系方式
                                this.contactAccount = contactAccount;
                                this.showUserContact = true;
                            }
                        }
                    }
                }
            }
        });
        //如果为自己
        if (this.isMine) {
            //为自己才显示的内容
            //为自己不可关注
            this.hasFollowed = false;
            this.beFollow = false;
//            String realPhoneNum = beSeeUser.getPhoneNum();
            String realPhoneNum = "";
//            this.vipEndDate = beSeeUser.getVipEndDate();
//            this.likeCount = beSeeUser.getLikeCount();
            this.birthday = beSeeUser.getBirthday();
//            this.idCardStatus = beSeeUser.getIdCardStatus();
//            this.followNum = beSeeUser.getFollowNum();
            if (StringUtils.isNotEmpty(realPhoneNum)) {
                this.phoneNum = realPhoneNum.substring(0, 3) + "*****" + realPhoneNum.substring(8);
            }
//            this.shell = beSeeUser.getShell();

//            this.genderModified = beSeeUser.getGenderModified();
//            this.authNum = beSeeUser.getAuthNum();
//            this.gradeLevel = beSeeUser.getGradeLevel();
//            this.wealthLevel = beSeeUser.getWealthLevel();
        } else {
            */
/*if (sessionUser != null && !sessionUser.getId().equals(beSeeUser.getId())) {
                Integer followCount = followRepository.countByUserIdAndBeUserIdAndStatus(sessionUser.getId(), beSeeUser.getId(), BaseStatus.enable);
                this.hasFollowed = followCount > 0;
                //查询对方是否关注了自己
                Integer beFollowCount = followRepository.countByUserIdAndBeUserIdAndStatus(beSeeUser.getId(), sessionUser.getId(), BaseStatus.enable);
                this.beFollow = beFollowCount > 0;
                //查询出来chatUser，用来判断用户是否购买了。
//                this.showBuyMsg = true;
                //如果被对方关注了，
                this.chat = chatService.seeUserDetailGetOrCreateChat(sessionUser, beSeeUser.getId());
            } else {
                //未登录所有人都显示可关注
                this.hasFollowed = false;
                this.beFollow = false;
            }*//*

            //他人需要判断
        }
        if (this.hasFollowed) {
            if (this.beFollow) {
                this.followStatus = FollowConst.eachFollow;
            } else {
                this.followStatus = FollowConst.followed;
            }
        } else {
            this.followStatus = FollowConst.follow;
        }
    }

    public static List<UserDetailVO> userDOToVOS(List<UserDO> userDOs) {
        return userDOs.stream().map(item -> new UserDetailBO(item, false).toVO()).collect(Collectors.toList());
    }
}
*/
