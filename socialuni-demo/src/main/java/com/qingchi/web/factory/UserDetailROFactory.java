package com.qingchi.web.factory;

import com.qingchi.web.dao.UserContactDao;
import com.qingchi.web.model.DO.UserDetailDO;
import com.qingchi.web.model.RO.user.UserDetailRO;
import com.qingchi.web.repsotiory.UserDetailRepository;
import com.qingchi.web.utils.UserUtil;
import com.socialuni.api.feignAPI.SocialuniUserAPI;
import com.socialuni.api.model.RO.user.CenterUserDetailRO;
import com.socialuni.social.entity.model.DO.chat.UserContactDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.sdk.constant.FollowConst;
import com.socialuni.social.sdk.store.SocialUserAccountStore;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserDetailROFactory {
    public static UserDetailRepository userDetailRepository;
    public static UserContactDao userContactDao;
    public static SocialUserAccountStore socialUserAccountStore;
    private static SocialuniUserAPI socialuniUserAPI;


    public static void setSocialuniUserAPI(SocialuniUserAPI socialuniUserAPI) {
        UserDetailROFactory.socialuniUserAPI = socialuniUserAPI;
    }

    @Resource
    public void setUserDetailRepository(UserDetailRepository userDetailRepository) {
        UserDetailROFactory.userDetailRepository = userDetailRepository;
    }

    @Resource
    public void setUserContactDao(UserContactDao userContactDao) {
        UserDetailROFactory.userContactDao = userContactDao;
    }

    @Resource
    public void setSocialUserAccountStore(SocialUserAccountStore socialUserAccountStore) {
        UserDetailROFactory.socialUserAccountStore = socialUserAccountStore;
    }

    public static UserDetailRO getUserDetailRO(CenterUserDetailRO centerUserDetailRO, UserDO mineUser) {
        //user基础信息
        UserDetailRO userDetailVO = new UserDetailRO(centerUserDetailRO);
        Integer detailUserId = UserUtil.getUserIdByUnionId(userDetailVO.getId());
        UserDetailDO userDetailDO = null;
        if (detailUserId != null) {
            userDetailDO = UserUtil.getUserDetail(detailUserId);
        }
        return UserDetailROFactory.getUserDetailRO(centerUserDetailRO, mineUser, userDetailDO);
    }

    public static UserDetailRO getUserDetailRO(CenterUserDetailRO centerUserDetailRO, UserDO mineUser, UserDetailDO userDetailDO) {
        //user基础信息
        UserDetailRO userDetailVO = new UserDetailRO(centerUserDetailRO);
        if (userDetailDO != null) {
            // user详情相关
            userDetailVO.setReportNum(userDetailDO.getReportNum());
            userDetailVO.setOnlineFlag(userDetailDO.getOnlineFlag());
            userDetailVO.setLastOnlineTime(userDetailDO.getLastOnlineTime());
            userDetailVO.setVipFlag(userDetailDO.getVipFlag());
            userDetailVO.setYearVipFlag(userDetailDO.getYearVipFlag());
            userDetailVO.setFaceRatio(userDetailDO.getFaceRatio());
            userDetailVO.setLoveValue(userDetailDO.getLoveValue());
            userDetailVO.setJusticeValue(userDetailDO.getJusticeValue());
            userDetailVO.setIsSelfAuth(userDetailDO.getIsSelfAuth());
            userDetailVO.setBeSponsorMsgShell(userDetailDO.getBeSponsorMsgShell());
            userDetailVO.setOpenContact(userDetailDO.getOpenContact());
            //联系方式相关
            String contactAccount = userDetailDO.getContactAccount();

            boolean isMine = userDetailVO.getIsMine();
            if (isMine) {
                userDetailVO.setContactAccount(contactAccount);
                userDetailVO.setShowUserContact(true);
            } else if (userDetailDO.getOpenContact()) {
                //如果未填写，则显示未填写
                //还有对方是否填写了联系方式
                //是否开启了
                //需要判断用户是否开启了openContact.如果未开启，则showUserContact为false
                //如果为查看别人的详情，则会带着自己的用户信息

                UserContactDO userContactDO = null;
                if (!centerUserDetailRO.getIsMine()) {
                    userContactDO = userContactDao.findByUserIdAndBeUserId(mineUser.getId(), userDetailDO.getId());
                }
                if (userContactDO != null) {
                    //这里需要确认用户是否已获取过对方的联系方式
                    userDetailVO.setContactAccount(contactAccount);
                    userDetailVO.setShowUserContact(true);
                } else {
                    userDetailVO.setContactAccount("***" + StringUtils.substring(contactAccount, -3));
                    //是否展示联系方式
                    userDetailVO.setShowUserContact(false);
                }
            }
            //未登录所有人都显示可关注
            if (mineUser != null && !isMine) {
                //查询出来chatUser，用来判断用户是否购买了。
                //如果被对方关注了，
//            ChatRO chat = socialChatService.seeUserDetailGetOrCreateChat(mineUser, userDetailDO.getUserId());
//            userDetailVO.setChat(chat);
            }
        }
        //关注相关
        //他人需要判断
        //应该移动到前端
        if (userDetailVO.getHasFollowed()) {
            if (userDetailVO.getBeFollow()) {
                userDetailVO.setFollowStatus(FollowConst.eachFollow);
            } else {
                userDetailVO.setFollowStatus(FollowConst.followed);
            }
        } else {
            userDetailVO.setFollowStatus(FollowConst.follow);
        }
        return userDetailVO;
    }

}
