package com.socialuni.center.web.utils;

import com.socialuni.center.web.model.DO.UniThirdContentDO;
import com.socialuni.center.web.repository.UniThirdContentRepository;
import com.socialuni.center.web.repository.UniUserAccountRepository;
import com.socialuni.social.entity.model.DO.dev.DevAccountDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.exception.SocialSystemException;
import com.socialuni.social.model.model.QO.ContentAddQO;
import com.socialuni.social.model.model.QO.user.SocialProviderLoginQO;
import com.socialuni.social.sdk.manage.SocialUserFansDetailManage;
import com.socialuni.social.sdk.manage.SocialUserManage;
import com.socialuni.social.sdk.manage.phone.SocialUserPhoneManage;
import com.socialuni.social.sdk.utils.DevAccountUtils;
import com.socialuni.social.sdk.utils.SocialUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Component
@Slf4j
public class UniThirdContentUtils {
    static UniThirdContentRepository uniThirdContentRepository;

    @Resource
    public void setUniThirdContentRepository(UniThirdContentRepository uniThirdContentRepository) {
        UniThirdContentUtils.uniThirdContentRepository = uniThirdContentRepository;
    }

    //根据渠道登录信息获取user，支持social比commonUserDomain
    //这个单独出来是因为区分了基础provider和社交，这个单独增加了对社交渠道的支持
    public static void saveThirdContentId(ContentAddQO contentAddQO, String contentType, String contentId) {
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccountNotNull();
        Integer devId = devAccountDO.getId();

        if (devAccountDO.getId() == 1) {
            throw new SocialSystemException("默认开发者不该进入校验内容是否重复逻辑");
        }
        UniThirdContentDO uniThirdContentDO = uniThirdContentRepository.findByDevIdAndContentTypeAndThirdId(devId, contentType, contentAddQO.getId());
        if (uniThirdContentDO == null) {
            return;
        }
        uniThirdContentDO = new UniThirdContentDO(devId, contentType, contentAddQO.getId(), contentId);

        uniThirdContentRepository.save(uniThirdContentDO);
    }
}
