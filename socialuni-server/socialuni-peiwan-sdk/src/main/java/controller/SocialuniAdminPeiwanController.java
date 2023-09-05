package controller;

import com.socialuni.social.common.api.model.ResultRO;
import logic.SocialuniAdminPeiwanService;
import lombok.extern.slf4j.Slf4j;
import model.QO.SocialuniPeiwanInfoRO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("admin/peiwan")
@RestController
@Slf4j
public class SocialuniAdminPeiwanController {

    @Resource
    SocialuniAdminPeiwanService socialuniAdminPeiwanService;

    @PostMapping("addPeiwanInfo")
    public ResultRO<Void> addPeiwanInfo(@RequestBody SocialuniPeiwanInfoRO socialuniPeiwanInfoAddQO) {
        return socialuniAdminPeiwanService.addPeiwanInfo(socialuniPeiwanInfoAddQO);
    }

    @GetMapping("getPeiwanInfoList")
    public ResultRO<List<SocialuniPeiwanInfoRO>> getPeiwanInfoList() {
        return socialuniAdminPeiwanService.getPeiwanInfoList();
    }
}
