package com.socialuni.social.peiwan.sdk.controller;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.model.SocialuniImgAddQO;
import com.socialuni.social.peiwan.sdk.logic.SocialuniAdminPeiwanService;
import com.socialuni.social.peiwan.sdk.model.QO.SocialuniPeiwanInfoRO;
import com.socialuni.social.peiwan.sdk.model.RO.SocialuniPeiwanAppInitData;
import com.socialuni.social.peiwan.sdk.model.RO.SocialuniPeiwanInfoImgRO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("admin/peiwan")
@RestController
@Slf4j
public class SocialuniAdminPeiwanController {

    @Resource
    SocialuniAdminPeiwanService socialuniAdminPeiwanService;

    @GetMapping("getAppInitData")
    public ResultRO<SocialuniPeiwanAppInitData> getAppInitData() {
        return ResultRO.success(new SocialuniPeiwanAppInitData());
    }

    @PostMapping("addPeiwanInfo")
    public ResultRO<Void> addPeiwanInfo(@RequestBody SocialuniPeiwanInfoRO socialuniPeiwanInfoAddQO) {
        return socialuniAdminPeiwanService.addPeiwanInfo(socialuniPeiwanInfoAddQO);
    }

    @GetMapping("queryPeiwanInfoList")
    public ResultRO<List<SocialuniPeiwanInfoRO>> queryPeiwanInfoList() {
        return socialuniAdminPeiwanService.queryPeiwanInfoList();
    }

    @GetMapping("updatePeiwanAvatar/{userId}")
    public ResultRO<String> updatePeiwanAvatar(@PathVariable("userId") String userId, @RequestParam String src) {
        return socialuniAdminPeiwanService.updatePeiwanAvatar(userId, src);
    }

    @PostMapping("updatePeiwanList")
    public ResultRO<Void> updatePeiwanList(@RequestBody List<SocialuniPeiwanInfoRO> peiwanInfoROS) {
        return socialuniAdminPeiwanService.updatePeiwanList(peiwanInfoROS);
    }


    @GetMapping("deletePeiwanImg/{imgId}")
    public ResultRO<Void> deletePeiwanImg(@PathVariable("imgId") String imgId) {
//        Integer imgId = Integer.valueOf(imgIdStr);
        socialuniAdminPeiwanService.deletePeiwanImg(imgId);
        return ResultRO.success();
    }

    @PostMapping("addPeiwanImgList/{userId}")
    public ResultRO<List<SocialuniPeiwanInfoImgRO>> addPeiwanImgList(@PathVariable("userId") String userId, @RequestBody List<SocialuniImgAddQO> imgAddQOS) {
        return socialuniAdminPeiwanService.addPeiwanImgList(userId, imgAddQOS);
    }
}
