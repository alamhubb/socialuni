package com.socialuni.admin.web.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import com.socialuni.admin.web.factory.ReportContentROFactory;
import com.socialuni.admin.web.factory.SocialuniAdminStickTalkROFactory;
import com.socialuni.admin.web.model.ReportContentVO;
import com.socialuni.admin.web.model.ReportUserVO;
import com.socialuni.admin.web.model.SocialuniAdminHomeSwiperRO;
import com.socialuni.admin.web.model.SocialuniAdminStickTalkRO;
import com.socialuni.social.common.exception.exception.SocialParamsException;
import com.socialuni.social.community.sdk.api.TalkInterface;
import com.socialuni.social.community.sdk.model.SocialuniTalkModel;
import com.socialuni.social.sdk.dao.DO.HomeSwiperDO;
import com.socialuni.social.sdk.dao.repository.HomeSwiperRepository;
import com.socialuni.social.sdk.dao.store.TalkQueryStore;
import com.socialuni.social.sdk.dao.utils.content.SocialuniTalkDORedis;
import com.socialuni.social.sdk.dao.utils.content.SocialuniTalkDOUtil;
import com.socialuni.social.sdk.logic.factory.ListConvertUtil;
import com.socialuni.social.sdk.logic.factory.SocialTalkROFactory;
import com.socialuni.social.sdk.logic.service.talk.SocialuniTalkService;
import com.socialuni.social.sdk.model.RO.talk.SocialuniTalkRO;
import com.socialuni.social.sdk.model.model.SocialuniHomeSwiperModel;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.tance.sdk.enumeration.SocialuniContentType;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.user.sdk.model.SocialuniUserModel;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @PostMapping("updateStickTalks")
    public List<SocialuniAdminStickTalkRO> updateHomeSwipers(@RequestBody List<SocialuniAdminStickTalkRO> homeSwiperROS) {
        for (SocialuniAdminStickTalkRO homeSwiperRO : homeSwiperROS) {
            SocialuniTalkModel talkDO = SocialuniTalkDOUtil.getTalkNotNull(homeSwiperRO.getId());
            if (!talkDO.getGlobalTop().equals(homeSwiperRO.getGlobalTop())){
                talkDO.setGlobalTop(homeSwiperRO.getGlobalTop());
                SocialuniTalkDOUtil.save(talkDO);
            }
        }
        return homeSwiperROS;
    }
}
