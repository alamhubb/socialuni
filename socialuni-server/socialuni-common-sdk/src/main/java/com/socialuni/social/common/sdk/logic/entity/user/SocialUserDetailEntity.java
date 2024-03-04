package com.socialuni.social.common.sdk.logic.entity.user;

import org.springframework.stereotype.Repository;

@Repository
public class SocialUserDetailEntity {
//    @Resource
//    SocialUserDetailRepository socialUserDetailRepository;

    /*public SocialUserDetailDO create(UniUserDO user) {
        *//*SocialUserCreateDTO user = new SocialUserCreateDTO();
        user.setNickname(userDetailVO.getNickname());
        user.setAvatar(userDetailVO.getAvatar());
        user.setGender(userDetailVO.getGender());
        user.setBirthday(userDetailVO.getBirthday());
        user.setCity(userDetailVO.getCity());

        UniUserDO userDO = new UniUserDO(user);
        user.create(userDO);
        userDO = socialUserRepository.save(userDO);

        return userDO;*//*

        SocialUserDetailDO userDetail = new SocialUserDetailDO();
        Date curDate = new Date();

        userDetail.setUserId(user.getId());

        userDetail.setWxAccount("");
        userDetail.setQqAccount("");
        userDetail.setWbAccount("");
        userDetail.setContactAccount("");
        userDetail.setOpenContact(true);

        userDetail.setExperience(0);
        userDetail.setBeSponsorMsgShell(5);
        userDetail.setCreateTime(curDate);
        userDetail.setUpdateTime(curDate);

        userDetail.setGenderModified(false);
        userDetail.setIsSelfAuth(false);
        userDetail.setVipFlag(false);
        userDetail.setYearVipFlag(false);
        userDetail.setGradeLevel(0);

        userDetail.setWealthLevel(0);
        userDetail.setVersionNo(1);
        userDetail.setFansNum(0);
        userDetail.setFollowNum(0);
        userDetail.setFaceRank(0);

        userDetail.setFaceRatio(0);
        userDetail.setBaseFaceRatio(0);
        userDetail.setFaceValue(0);
        userDetail.setQcb(0);
        userDetail.setShell(0);
        userDetail.setLoveValue(0);

        userDetail.setJusticeValue(0);
        userDetail.setSeeCount(MatchConstants.INIT_FACE_SEE_NUM);
        userDetail.setLikeCount(0);
        userDetail.setOpenMatch(true);
        userDetail.setAuthNum(0);

        userDetail.setIdCardStatus(CommonStatus.init);
        userDetail.setOnlineFlag(true);
        userDetail.setLastOnlineTime(curDate);
        userDetail.setInviteCode("00000000001");

        userDetail.setReportNum(0);
        userDetail.setViolationCount(0);
        userDetail.setLastShowAdTime(curDate);

//        userDetail.setPlatform(PlatformType.mp);
//        userDetail.setProvider(ProviderType.social);


        return socialUserDetailRepository.save(userDetail);
*/

        /*Optional<UserDO> userDOOptional = userRepository.findById(user.getId());
        if (userDOOptional.isPresent()) {
            user = userDOOptional.get();
        }*/
        /*vipService.addVipOrder(VipGiftType.register, user, user.getRegisterInviteCode(), user.getRegisterInviteUser());
        if (user.getRegisterInviteUser() != null) {
            vipService.addVipOrder(VipGiftType.invite, user, user.getRegisterInviteCode(), user.getRegisterInviteUser());
        }*/
        //设置个人邀请码
//        String userInviteCode = "0000000" + u.getId().toString();
//        user.setInviteCode(userInviteCode.substring(userInviteCode.length() - 8));

        //注释掉圈子功能
        /*List<ChatDO> chatDOS = chatRepository.findByTypeAndStatus(ChatType.system_group, Status.enable);
        for (ChatDO chat : chatDOS) {
            ChatUserDO chatUserDO = new ChatUserDO(chat, user.getId());
            chatUserRepository.save(chatUserDO);
        }*/

        /*Optional<UserDO> systemUserOptional = userRepository.findById(UserUtils.getSystemId());

        if (systemUserOptional.isPresent()) {
            UserDO sysUser = systemUserOptional.get();
            //所有人注册默认关注系统用户
            followService.addFlow(user, sysUser);
        }*/
        //初始化用户详情



    /*public UniUserDO create(SocialUserDetailVO userVO) {
            Userdo user = new SocialUserDetailVO();
            user.setId(this.getId());
            user.setNickname(this.getNickname());
            user.setGender(this.getGender());
            user.setLocation(this.getLocation());
            user.setCity(this.getLocation());
            user.setAge(this.getAge());
            user.setFaceRatio(this.getFaceRatio());
            user.setOnlineFlag(this.getOnlineFlag());
            user.setLastOnlineTime(this.getLastOnlineTime());
            user.setVipFlag(this.getVipFlag());
            user.setAvatar(this.getAvatar());
            user.setFansNum(this.getFansNum());
            user.setLoveValue(this.getLoveValue());
            user.setUserType(this.getUserType());
            user.setIsSelfAuth(this.getIsSelfAuth());
            return user;
        return null;
    }*/
}
