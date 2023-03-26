package com.socialuni.social.admin.controller;

import com.socialuni.social.admin.factory.SocialuniAdminStickTalkROFactory;
import com.socialuni.social.admin.model.ReportUserVO;
import com.socialuni.social.admin.model.SocialuniAdminStickTalkRO;
import com.socialuni.social.community.sdk.entity.SocialuniTalkDO;
import com.socialuni.social.community.sdk.repository.TalkRepository;
import com.socialuni.social.sdk.dao.utils.content.SocialuniTalkDOUtil;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("socialuni/admin/stickTalk")
public class SocialuniAdminStickTalkManageController {
    @Resource
    TalkRepository talkInterface;

    @GetMapping("querySystemUserTalks")
    public List<SocialuniAdminStickTalkRO> querySystemManageTalks() {
        Integer userId = DevAccountFacade.getDevUserId();

        List<Integer> talkIds = talkInterface.findTop10ByUserIdOrderByGlobalTopDescIdDesc(userId);

        List<SocialuniAdminStickTalkRO> talkROS = talkIds.stream().map(item -> {
            SocialuniAdminStickTalkRO talkvo = SocialuniAdminStickTalkROFactory.getTalkRO(item);
            ReportUserVO userVO = new ReportUserVO(SocialuniUserUtil.getAndCheckUserNotNull(talkvo.getUserId()));
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
                SocialUserPhoneDo SocialUserPhoneDo = socialUserPhoneRedis.findByPhoneNum(phoneNum);
                //如果c端用户不为空，则设置userId
                if (SocialUserPhoneDo != null) {
                    devAccountModel.setUserId(SocialUserPhoneDo.getUserId());
                } else {
                    //如果c端为空则创建
                    // 注册admin的时候，肯定是没有c端用户的，你开发者都没有怎么可能有他下面的用户呢
                    // 所以直接注册c端用户
                    SocialuniUserDo SocialuniUserDo = socialUserPhoneEntity.createUserPhoneEntity(phoneNum);
                    devAccountModel.setUserId(SocialuniUserDo.getUserId());
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
            SocialuniTalkDO talkDO = SocialuniTalkDOUtil.getTalkNotNull(homeSwiperRO.getId());
            if (!talkDO.getGlobalTop().equals(homeSwiperRO.getGlobalTop())) {
                talkDO.setGlobalTop(homeSwiperRO.getGlobalTop());
                SocialuniTalkDOUtil.save(talkDO);
            }
        }
        return homeSwiperROS;
    }
}
