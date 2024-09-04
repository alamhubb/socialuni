package com.socialuni.social.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import com.socialuni.social.admin.model.SocialuniAdminHomeSwiperRO;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.community.sdk.dao.DO.SocialuniHomeSwiperDO;
import com.socialuni.social.community.sdk.repository.HomeSwiperRepository;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("socialuni/admin/homeSwiper")
public class SocialuniAdminHomeSwiperManageController {
    @Resource
    HomeSwiperRepository homeSwiperRepository;

    @GetMapping("queryhomeSwipers")
    public ResultRO<List<SocialuniAdminHomeSwiperRO>> queryhomeSwipers() {
        //三方应用传的可以直接传用户id作为token
        Integer devId = DevAccountFacade.getDevIdNullElseCenterDevId();

        List<SocialuniHomeSwiperDO> homeSwiperModels = homeSwiperRepository.queryHomeSwipersByDevIdOrderByStatusDescUpdateTimeDescTopLevelDesc(devId);

        List<SocialuniAdminHomeSwiperRO> results = homeSwiperModels.stream().map(SocialuniAdminHomeSwiperRO::new).collect(Collectors.toList());
        return ResultRO.success(results);
    }

    @PostMapping("updateHomeSwipers")
    public ResultRO<List<SocialuniAdminHomeSwiperRO>> updateHomeSwipers(@RequestBody List<SocialuniAdminHomeSwiperRO> homeSwiperROS) {
        List<SocialuniHomeSwiperDO> SocialuniHomeSwiperDOs = new ArrayList<>();
        for (SocialuniAdminHomeSwiperRO homeSwiperRO : homeSwiperROS) {
            if (ObjUtil.isEmpty(homeSwiperRO.getId())) {
                SocialuniHomeSwiperDOs.add(homeSwiperRepository.save(BeanUtil.toBean(homeSwiperRO.toModel(), SocialuniHomeSwiperDO.class)));
            } else {
                Integer devId = DevAccountFacade.getDevIdNullElseCenterDevId();
                SocialuniHomeSwiperDO SocialuniHomeSwiperDODb = homeSwiperRepository.findByIdAndDevId(homeSwiperRO.getId(), devId)
                        .orElseThrow(() -> new SocialParamsException("不存在的轮播图配置"));
                SocialuniHomeSwiperDOs.add(homeSwiperRepository.save(BeanUtil.toBean(homeSwiperRO.toModel(SocialuniHomeSwiperDODb), SocialuniHomeSwiperDO.class)));
            }
        }

        List<SocialuniAdminHomeSwiperRO> results = SocialuniHomeSwiperDOs.stream().map(SocialuniAdminHomeSwiperRO::new).collect(Collectors.toList());
        return ResultRO.success(results);
    }
}
