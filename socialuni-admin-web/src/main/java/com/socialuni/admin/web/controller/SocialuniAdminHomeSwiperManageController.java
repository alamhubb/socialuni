package com.socialuni.admin.web.controller;

import com.socialuni.social.sdk.dao.repository.HomeSwiperRepository;
import com.socialuni.social.sdk.model.model.SocialuniHomeSwiperModel;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("socialuni/admin/homeSwiper")
public class SocialuniAdminHomeSwiperManageController {
    @Resource
    HomeSwiperRepository homeSwiperRepository;

    @PostMapping("queryhomeSwipers")
    public List<SocialuniHomeSwiperModel> queryhomeSwipers() {
        //三方应用传的可以直接传用户id作为token
        Integer devId = DevAccountFacade.getDevIdNotNull();

        List<SocialuniHomeSwiperModel> homeSwiperModels = homeSwiperRepository.queryHomeSwipersByDevIdOrderByStatusDescUpdateTimeDescTopLevelDesc(devId);
        return homeSwiperModels;
    }
}
