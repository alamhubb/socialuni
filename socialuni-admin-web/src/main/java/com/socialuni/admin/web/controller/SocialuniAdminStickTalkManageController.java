package com.socialuni.admin.web.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import com.socialuni.admin.web.model.SocialuniAdminHomeSwiperRO;
import com.socialuni.social.common.exception.exception.SocialParamsException;
import com.socialuni.social.sdk.dao.DO.HomeSwiperDO;
import com.socialuni.social.sdk.dao.repository.HomeSwiperRepository;
import com.socialuni.social.sdk.model.model.SocialuniHomeSwiperModel;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
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

    @GetMapping("querySystemManageTalks")
    public List<SocialuniAdminHomeSwiperRO> querySystemManageTalks() {
        //三方应用传的可以直接传用户id作为token
        Integer devId = DevAccountFacade.getDevIdNotNull();

        List<SocialuniHomeSwiperModel> homeSwiperModels = homeSwiperRepository.queryHomeSwipersByDevIdOrderByStatusDescUpdateTimeDescTopLevelDesc(devId);

        List<SocialuniAdminHomeSwiperRO> results = homeSwiperModels.stream().map(SocialuniAdminHomeSwiperRO::new).collect(Collectors.toList());
        return results;
    }

    @PostMapping("updateHomeSwipers")
    public List<SocialuniAdminHomeSwiperRO> updateHomeSwipers(@RequestBody List<SocialuniAdminHomeSwiperRO> homeSwiperROS) {
        List<SocialuniHomeSwiperModel> socialuniHomeSwiperModels = new ArrayList<>();
        for (SocialuniAdminHomeSwiperRO homeSwiperRO : homeSwiperROS) {
            if (ObjUtil.isEmpty(homeSwiperRO.getId())) {
                socialuniHomeSwiperModels.add(homeSwiperRepository.save(BeanUtil.toBean(homeSwiperRO.toModel(), HomeSwiperDO.class)));
            } else {
                Integer devId = DevAccountFacade.getDevIdNotNull();
                SocialuniHomeSwiperModel socialuniHomeSwiperModelDb = homeSwiperRepository.findByIdAndDevId(homeSwiperRO.getId(), devId)
                        .orElseThrow(() -> new SocialParamsException("不存在的轮播图配置"));
                socialuniHomeSwiperModels.add(homeSwiperRepository.save(BeanUtil.toBean(homeSwiperRO.toModel(socialuniHomeSwiperModelDb), HomeSwiperDO.class)));
            }
        }

        List<SocialuniAdminHomeSwiperRO> results = socialuniHomeSwiperModels.stream().map(SocialuniAdminHomeSwiperRO::new).collect(Collectors.toList());
        return results;
    }
}
