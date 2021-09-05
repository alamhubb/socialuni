package com.socialuni.center.web.serive;

import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.center.sdk.utils.DevAccountUtils;
import com.socialuni.entity.model.DevAccountDO;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.model.model.RO.app.SocialCosAuthRO;
import com.socialuni.social.sdk.domain.SocialCosGetAuthDomain;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CenterCosService {
    @Resource
    private SocialCosGetAuthDomain socialCosGetAuthDomain;

    public ResultRO<SocialCosAuthRO> getCosAuth() {
        String userId = CenterUserUtil.getMineThirdUserId();
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccountNotNull();
//        String devAppName = Pinyin.toPinyin(devAccountDO.getAppName(), "").toLowerCase();
//        String uploadImgPath = devAppName + "/user/" + userId + "/";
        String uploadImgPath = "user/" + userId + "/";
        SocialCosAuthRO socialCosAuthRO = socialCosGetAuthDomain.getCosAuthorization(uploadImgPath);
        return new ResultRO<>(socialCosAuthRO);
    }
}
