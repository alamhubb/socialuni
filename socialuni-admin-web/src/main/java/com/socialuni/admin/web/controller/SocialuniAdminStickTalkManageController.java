package com.socialuni.admin.web.controller;

import com.socialuni.admin.web.factory.SocialuniAdminStickTalkROFactory;
import com.socialuni.admin.web.model.ReportUserVO;
import com.socialuni.admin.web.model.SocialuniAdminStickTalkRO;
import com.socialuni.social.common.model.ResultRO;
import com.socialuni.social.common.utils.RequestUtil;
import com.socialuni.social.common.utils.UUIDUtil;
import com.socialuni.social.community.sdk.api.TalkInterface;
import com.socialuni.social.community.sdk.model.SocialuniTalkModel;
import com.socialuni.social.sdk.dao.redis.SocialUserPhoneRedis;
import com.socialuni.social.sdk.dao.repository.HomeSwiperRepository;
import com.socialuni.social.sdk.dao.store.TalkQueryStore;
import com.socialuni.social.sdk.dao.utils.content.SocialuniTalkDOUtil;
import com.socialuni.social.sdk.logic.entity.user.SocialUserPhoneEntity;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.tance.sdk.api.DevAccountInterface;
import com.socialuni.social.tance.sdk.api.DevAccountRedisInterface;
import com.socialuni.social.tance.sdk.enumeration.SocialFeignHeaderName;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.tance.sdk.model.DevAccountModel;
import com.socialuni.social.user.sdk.model.SocialUserPhoneModel;
import com.socialuni.social.user.sdk.model.SocialuniUserModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("socialuni/admin/stickTalk")
public class SocialuniAdminStickTalkManageController {
    @Resource
    HomeSwiperRepository homeSwiperRepository;
    @Resource
    TalkInterface talkInterface;
    @Resource
    TalkQueryStore talkQueryStore;
    @Resource
    private SocialUserPhoneRedis socialUserPhoneRedis;
    @Resource
    private SocialUserPhoneEntity socialUserPhoneEntity;
    @Resource
    private DevAccountInterface devAccountInterface;
    @Resource
    private DevAccountRedisInterface devAccountRedisInterface;

    @GetMapping("querySystemUserTalks")
    public List<SocialuniAdminStickTalkRO> querySystemManageTalks() {
        Integer userId = DevAccountFacade.getDevUserId();

        List<Integer> talkIds = talkInterface.findTop10ByUserIdOrderByGlobalTopDescIdDesc(userId);

        List<SocialuniAdminStickTalkRO> talkROS = talkIds.stream().map(item -> {
            SocialuniAdminStickTalkRO talkvo = SocialuniAdminStickTalkROFactory.getTalkRO(item);
            ReportUserVO userVO = new ReportUserVO(SocialuniUserUtil.getUserNotNull(talkvo.getUserId()));
            talkvo.setUser(userVO);
            return talkvo;
        }).collect(Collectors.toList());

        return talkROS;
    }


    /*@GetMapping("updateAllDevPhoneNumUser")
    public ResultRO<Void> updateAllDevPhoneNumUser() {
        List<DevAccountModel> devAccountModels = (List<DevAccountModel>) devAccountInterface.findAll();

        for (DevAccountModel devAccountModel : devAccountModels) {
            Integer userId = devAccountModel.getUserId();
            if (userId == null) {
                RequestUtil.setAttribute(SocialFeignHeaderName.socialuniSecretKey, devAccountModel.getSecretKey());
                String phoneNum = devAccountModel.getPhoneNum();
                //获取c端用户
                SocialUserPhoneModel socialUserPhoneModel = socialUserPhoneRedis.findByPhoneNum(phoneNum);
                //如果c端用户不为空，则设置userId
                if (socialUserPhoneModel != null) {
                    devAccountModel.setUserId(socialUserPhoneModel.getUserId());
                } else {
                    //如果c端为空则创建
                    // 注册admin的时候，肯定是没有c端用户的，你开发者都没有怎么可能有他下面的用户呢
                    // 所以直接注册c端用户
                    SocialuniUserModel socialuniUserModel = socialUserPhoneEntity.createUserPhoneEntity(phoneNum);
                    devAccountModel.setUserId(socialuniUserModel.getUserId());
                }
            }
            if (StringUtils.isEmpty(devAccountModel.getSocialuniId())) {
                devAccountModel.setSocialuniId(UUIDUtil.getUUID());
            }
            devAccountModel = devAccountRedisInterface.saveDevAccount(devAccountModel);
        }
        return null;
    }*/

    @PostMapping("updateStickTalks")
    public List<SocialuniAdminStickTalkRO> updateHomeSwipers(@RequestBody List<SocialuniAdminStickTalkRO> homeSwiperROS) {
        for (SocialuniAdminStickTalkRO homeSwiperRO : homeSwiperROS) {
            SocialuniTalkModel talkDO = SocialuniTalkDOUtil.getTalkNotNull(homeSwiperRO.getId());
            if (!talkDO.getGlobalTop().equals(homeSwiperRO.getGlobalTop())) {
                talkDO.setGlobalTop(homeSwiperRO.getGlobalTop());
                SocialuniTalkDOUtil.save(talkDO);
            }
        }
        return homeSwiperROS;
    }
}
