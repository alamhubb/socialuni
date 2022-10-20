package com.socialuni.admin.web.controller;

import com.socialuni.admin.web.service.AdminUserService;
import com.socialuni.social.sdk.api.SocialuniHomeSwiperAPI;
import com.socialuni.social.sdk.model.model.SocialuniHomeSwiperModel;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("socialuni/admin/homeSwiperMange")
public class SocialuniAdminHomeSwiperManageController {
    @Resource
    SocialuniHomeSwiperAPI socialuniHomeSwiperAPI;

    @PostMapping("queryhomeSwipers")
    public List<SocialuniHomeSwiperModel> queryhomeSwipers() {
        //三方应用传的可以直接传用户id作为token
        Integer devId = DevAccountFacade.getDevIdNotNull();

        List<SocialuniHomeSwiperModel> homeSwiperModels = socialuniHomeSwiperAPI.queryHomeSwipersByDevIdOrderByStatusDescUpdateTimeDescTopLevelDesc(devId);
        return homeSwiperModels;
    }
}
