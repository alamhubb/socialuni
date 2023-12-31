package com.socialuni.social.admin.controller;

import com.socialuni.social.admin.model.RO.UserIdentityAuditRO;
import com.socialuni.social.admin.utils.CheckIsAdminUtil;
import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.user.sdk.constant.UserIdentityAuthStatus;
import com.socialuni.social.user.sdk.dao.DO.SocialUserIdentityAuthDO;
import com.socialuni.social.user.sdk.dao.DO.SocialUserIdentityAuthImgDO;
import com.socialuni.social.user.sdk.repository.SocialUserIdentityAuthImgRepository;
import com.socialuni.social.user.sdk.repository.SocialUserIdentityAuthRepository;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("userIdentity")
@Slf4j
public class UserIdentityAuditController {
    @Resource
    SocialUserIdentityAuthRepository socialUserIdentityAuthRepository;
    @Resource
    SocialUserIdentityAuthImgRepository socialUserIdentityAuthImgRepository;

    /**
     * 查询cos图片审核历史详情
     */
    @PostMapping("queryUserIdentityAuthAuditList")
    public ResultRO<List<UserIdentityAuditRO>> queryAuditUserIdentityList() {
        CheckIsAdminUtil.checkAdmin();
        List<SocialUserIdentityAuthDO> list = socialUserIdentityAuthRepository.findTop20ByStatusInOrderByIdDesc(UserIdentityAuthStatus.auditList);
        List<UserIdentityAuditRO> list1 = new ArrayList<>();
        for (SocialUserIdentityAuthDO socialUserIdentityAuthDO : list) {
            SocialUserIdentityAuthImgDO socialUserIdentityAuthImgDO = socialUserIdentityAuthImgRepository.findFirstById(socialUserIdentityAuthDO.getUserIdentityImgId());

            SocialuniUserDo userDO = SocialuniUserUtil.getAndCheckUserNotNull(socialUserIdentityAuthDO.getUserId());

            UserIdentityAuditRO userIdentityAuditRO = new UserIdentityAuditRO(socialUserIdentityAuthDO, socialUserIdentityAuthImgDO, userDO);
            list1.add(userIdentityAuditRO);
        }
        //展示自拍和id照片信息，还有
        return ResultRO.success(list1);
    }

    /**
     * 查询cos图片审核历史详情
     */
    @PostMapping("auditUserIdentityList")
    public ResultRO<List<UserIdentityAuditRO>> auditUserIdentityList(@RequestBody List<UserIdentityAuditRO> audits) {
        CheckIsAdminUtil.checkAdmin();
        for (UserIdentityAuditRO audit : audits) {
            SocialUserIdentityAuthDO socialUserIdentityAuthDO = socialUserIdentityAuthRepository.getOne(audit.getId());
            if (audit.getSuccess()) {
                socialUserIdentityAuthDO.setStatus(UserIdentityAuthStatus.enable);
            } else {
                socialUserIdentityAuthDO.setStatus(UserIdentityAuthStatus.delete);
            }
            socialUserIdentityAuthDO.setUpdateTime(new Date());
            socialUserIdentityAuthRepository.save(socialUserIdentityAuthDO);
        }
        //展示自拍和id照片信息，还有
        return ResultRO.success();
    }
}
