package com.socialuni.social.sdk.service.user;/*
package com.socialuni.social.service;

import com.socialuni.web.config.AppConfigStatic;
import com.socialuni.web.constant.ChatType;
import com.socialuni.web.constant.status.ChatStatus;
import com.socialuni.web.model.chat.ChatDO;
import com.socialuni.web.model.chat.ChatUserDO;
import com.socialuni.web.model.user.IdCardDO;
import com.socialuni.web.model.user.UserDetailDO;
import com.socialuni.web.repository.chat.ChatRepository;
import com.socialuni.web.repository.chat.ChatUserRepository;
import com.socialuni.web.repository.shell.VipSaleRepository;
import com.socialuni.web.repository.user.IdCardRepository;
import com.socialuni.web.repository.user.UserDetailRepository;
import com.socialuni.web.repository.user.UserRepository;
import com.socialuni.center.sdk.service.FollowService;
import com.socialuni.web.service.IllegalWordService;
import com.socialuni.web.service.VipService;
import com.socialuni.web.utils.DevAccountUtils;
import com.socialuni.web.utils.UserUtils;
import com.socialuni.base.constants.GenderType;
import com.qingchi.web.model.DO.user.UniUserDO;
import com.socialuni.social.constant.CommonConst;
import com.socialuni.social.api.model.model.ResultRO;
import com.socialuni.social.repository.user.img.UserImgRepository;
import com.socialuni.social.utils.GenderUtil;
import com.socialuni.social.utils.GenerateNicknameUtil;
import com.socialuni.uniapp.model.SocialProviderLoginQO;
import com.socialuni.social.utils.model.SocialUserModelUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.Optional;

*/
/**
 * @author qinkaiyuan
 * @date 2019-06-25 19:59
 *//*

@Service
public class UserCreateService {
    @Resource
    private UserRepository userRepository;
    @Resource
    private UserDetailRepository userDetailRepository;
    @Resource
    private EntityManager entityManager;
    @Resource
    private UserImgRepository socialUserImgRepository;
    @Resource
    private IdCardRepository idCardRepository;
    @Resource
    private VipSaleRepository vipSaleRepository;
    @Resource
    private IllegalWordService illegalWordService;
    @Value("${config.initAvatar}")
    private String initAvatar;
    @Resource
    private FollowService followService;

    @Resource
    private VipService vipService;
    @Resource
    private ChatRepository chatRepository;
    @Resource
    private ChatUserRepository chatUserRepository;
    @Resource
    UserCheckSetDefaultValueService userCheckSetDefaultValueService;

    //用户第一次登陆
    //保存用户信息
    //创建详情信息，保存详情信息

    //一步是校验值，没有值给默认值
    //一步是保存

    public UniUserDO createUser(SocialProviderLoginQO loginRO) {
        UniUserDO socialUserDO = SocialUserModelUtil.ProviderLoginROToDO(loginRO);
        socialUserDO = userCheckSetDefaultValueService.userSetDefaultValue(socialUserDO);

    }

    public UniUserDO createUser(String phoneNum, String platform) {
        UniUserDO user = new UniUserDO();
        user.setPhoneNum(phoneNum);
        user.setPlatform(platform);

        user.setNickname(GenerateNicknameUtil.getGirlName());
        user.setAvatar(initAvatar);
        user.setGender(GenderType.girl);
        user.setBirthday(CommonConst.initBirthday);
//        user.setCity("北京");
        return this.createUser(user);
    }

    public UniUserDO createUser(UniUserDO user) {
        if (StringUtils.isEmpty(user.getNickname())) {
            user.setNickname(GenerateNicknameUtil.getGirlName());
        }
        if (StringUtils.isEmpty(user.getAvatar())) {
            user.setAvatar(initAvatar);
        }

        String appGenderType = DevAccountUtils.getAppGenderType();

        //如果为全部,则设置初始值
        if (appGenderType.equals(GenderType.all)) {
            if (StringUtils.isEmpty(user.getGender())) {
                //默认设置为女生，因为女生不介意当男生，而男生介意当女生，所以男生会主动修改，
                // 且体现女士优先，让女士更方便
                user.setGender(CommonConst.girl);
            } else {
                //做兼容处理，男女转为boy、girl
                String editGender = GenderUtil.getUserEditGenderByOldGender(user.getGender());
                ResultRO<String> checkGender = GenderUtil.checkUserEditGenderType(editGender);
                if (checkGender.hasError()) {
                    user.setGender(CommonConst.girl);
                }
                //做兼容处理，男女转为boy、girl
                user.setGender(editGender);
            }
        } else {
            //如果不为全部，则直接设置用户性别默认为单性，且不可修改
            user.setGender(appGenderType);
        }

        if (StringUtils.isEmpty(user.getBirthday())) {
            user.setBirthday(CommonConst.initBirthday);
        }
        */
/*if (StringUtils.isEmpty(user.getCity())) {
            user.setCity("北京");
        }*//*

       */
/* Date curDate = new Date();
        // 初始都为0
        user.setFaceRatio(0);
        user.setBaseFaceRatio(0);
        user.setLikeCount(0);
        user.setSeeCount(MatchConstants.INIT_FACE_SEE_NUM);
        //新用户注册，颜值为最低，0
        user.setFaceValue(0);
        user.setFaceRank(0);
        user.setAge(AgeUtils.getAgeByBirth(user.getBirthday()));
        user.setIdCardStatus(CommonStatus.init);
        //初始为正常用户
        user.setVipFlag(false);
        user.setYearVipFlag(false);
        user.setQcb(0);
        //经验等级
        user.setGradeLevel(0);
        //经验值
        user.setShell(0);
        //财富等级
        user.setWealthLevel(0);
        user.setStatus(UserStatus.enable);
        user.setViolationCount(0);
        user.setFansNum(0);
        user.setFollowNum(0);
        user.setCreateTime(curDate);
        user.setGenderModified(false);
        user.setUpdateTime(curDate);
        user.setLastOnlineTime(curDate);
        user.setLastShowAdTime(curDate);
        user.setType(UserType.personal);
        user.setVersionNo(1);
        user.setLoveValue(0);
        user.setJusticeValue(0);
        user.setReportNum(0);
        user.setAuthNum(0);
        user.setIsSelfAuth(false);*//*

        userRepository.save(user);
        entityManager.clear();
        Optional<UniUserDO> userDOOptional = userRepository.findById(user.getId());
        if (userDOOptional.isPresent()) {
            user = userDOOptional.get();
        }
        */
/*vipService.addVipOrder(VipGiftType.register, user, user.getRegisterInviteCode(), user.getRegisterInviteUser());
        if (user.getRegisterInviteUser() != null) {
            vipService.addVipOrder(VipGiftType.invite, user, user.getRegisterInviteCode(), user.getRegisterInviteUser());
        }*//*

        //设置个人邀请码
        String userInviteCode = "0000000" + user.getId().toString();
//        user.setInviteCode(userInviteCode.substring(userInviteCode.length() - 8));
        user = userRepository.save(user);
        //注释掉圈子功能
        List<ChatDO> chatDOS = chatRepository.findByTypeAndStatus(ChatType.system_group, ChatStatus.enable);
        for (ChatDO chat : chatDOS) {
            ChatUserDO chatUserDO = new ChatUserDO(chat, user.getId());
            chatUserRepository.save(chatUserDO);
        }

        Optional<UniUserDO> systemUserOptional = userRepository.findById(UserUtils.getSystemId());

        if (systemUserOptional.isPresent()) {
            UniUserDO sysUser = systemUserOptional.get();
            //所有人注册默认关注系统用户
            followService.addFlow(user, sysUser);
        }
        //初始化用户详情
        UserDetailDO userDetailDO = new UserDetailDO();
        userDetailRepository.save(userDetailDO);
        return userRepository.findById(user.getId()).get();
    }

    public UniUserDO addIdCard(UniUserDO user, IdCardDO idCardDO) {
        //首先将之前的所有idcard改为禁用，目前不存在多次认证的情况
        */
/*List<IdCardDO> idCardDOS = idCardRepository.findByUserAndStatus(user, CommonStatus.enable);
        for (IdCardDO cardDO : idCardDOS) {
            cardDO.setStatus(CommonStatus.disable);
        }
        idCardRepository.saveAll(idCardDOS);*//*

        // 新idcard信息
        idCardDO.setUserId(user.getId());
        idCardRepository.save(idCardDO);
        // 用户身份证状态改为审核中状态
//        user.setIdCardStatus(ReportStatus.audit);
        user.setUpdateTime(new Date());
        //提交认证，更新颜值分
        this.updateFaceContent(user);
        userRepository.save(user);
        entityManager.clear();
        return userRepository.getOne(user.getId());
    }


    //更新用户颜值相关内容
    public void updateUserListFaceContent(List<UniUserDO> users) {
        for (UniUserDO user : users) {
            updateFaceContent(user);
        }
        userRepository.saveAll(users);
    }

    //更新用户颜值相关内容
    public void updateFaceContent(UniUserDO user) {
        Integer[] faceValues;
        if (user.getGender().equals(CommonConst.boy)) {
            faceValues = AppConfigStatic.getBoysFaceValueList();
        } else {
            faceValues = AppConfigStatic.getGirlsFaceValueList();
        }
        //用户被查看，喜欢次数+1
//        user.setSeeCount(user.getSeeCount() + 1);
        //根据用户的查看次数和喜欢次数，计算用户的颜值
//        long faceValue = user.getLikeCount() * MatchConstants.FACE_VALUE_BASE_MULTIPLE / user.getSeeCount();
//        user.setFaceValue((int) faceValue);
        //有了用户颜值
//        Integer userFaceValue = user.getFaceValue();
        int faceValueLength = faceValues.length;
        //颜值从小到大排序的
        if (faceValues.length > 0) {
            //默认最后一名
//            int faceRank = binary(faceValues, userFaceValue);
            Long setFaceRatio;
            */
/*if (faceRank >= faceValueLength) {
//                user.setFaceRank(1);
                setFaceRatio = MatchConstants.FACE_VALUE_BASE_MULTIPLE;
            } else {
                user.setFaceRank(faceValueLength - faceRank);
                setFaceRatio = faceRank * MatchConstants.FACE_VALUE_BASE_MULTIPLE / faceValueLength;
            }
            user.setBaseFaceRatio(setFaceRatio.intValue());
            if (user.getVipFlag() != null && user.getVipFlag()) {
                //普通会员加20分
                setFaceRatio += MatchConstants.FACE_VALUE_VIP_APPEND_MULTIPLE;
            }*//*

            //根据这个排名除以总男数得到百分比数
            //认证增加30分
            */
/*if (user.isCertified()) {
                setFaceRatio += MatchConstants.FACE_VALUE_APPEND_MULTIPLE;
            }*//*

            //年会员加30分
            */
/*if (user.getYearVipFlag() != null && user.getYearVipFlag()) {
                setFaceRatio += MatchConstants.FACE_VALUE_YEAR_VIP_APPEND_MULTIPLE;
            } else if (user.getVipFlag() != null && user.getVipFlag()) {
                //普通会员加20分
                setFaceRatio += MatchConstants.FACE_VALUE_VIP_APPEND_MULTIPLE;
            }*//*

            //100个清池币1颜值
            */
/*if (user.getQcb() != null && user.getQcb() > 0) {
                setFaceRatio += user.getQcb() * MatchConstants.QCB_FACE_VALUE_RATIO;
            }*//*

//            user.setFaceRatio(setFaceRatio.intValue());
        }
    }

    //## 标题二分法查找,返回插入点索引
    public static int binary(Integer[] arr, Integer n) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid].equals(n)) {
                return mid;
            } else if (arr[mid] > n) {
                high = mid - 1;
            } else if (arr[mid] < n) {
                low = mid + 1;
            }
        }
        return mid + 1;
    }
}
*/
