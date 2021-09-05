package com.qingchi.web.controller.oldVersion;

import com.qingchi.web.service.AppService;
import com.socialuni.api.feignAPI.SocialuniAppAPI;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.HomeSwiperVO;
import com.socialuni.social.model.model.QO.FrontErrorLogVO;
import com.socialuni.social.model.model.RO.app.SocialAppLaunchDataRO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinkaiyuan
 * @date 2019-09-28 11:09
 * 前端初始化内容
 */
@RestController
@RequestMapping("qingchi")
public class OldQingchiController {
    @Resource
    AppService appService;

    @GetMapping("queryHomeSwipers")
    public ResultRO<List<HomeSwiperVO>> queryHomeSwipers() {
        return appService.queryHomeSwipers();
    }
}
