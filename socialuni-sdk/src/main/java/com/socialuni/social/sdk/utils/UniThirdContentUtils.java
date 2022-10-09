package com.socialuni.social.sdk.utils;

import com.socialuni.social.tance.sdk.repository.SocialuniUnionIdRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class UniThirdContentUtils {
    static SocialuniUnionIdRepository uniContentUnionIdRepository;

    @Resource
    public void setUniContentUnionIdRepository(SocialuniUnionIdRepository uniContentUnionIdRepository) {
        UniThirdContentUtils.uniContentUnionIdRepository = uniContentUnionIdRepository;
    }


    //根据渠道登录信息获取user，支持social比commonUserDomain
    //这个单独出来是因为区分了基础provider和社交，这个单独增加了对社交渠道的支持
    /*public static void saveThirdContentId(ContentAddQO contentAddQO, String contentType, String contentId) {
        DevAccountDO devAccountDO = DevAccountUtils.getDevAccountNotNull();
        Integer devId = devAccountDO.getId();

        if (devAccountDO.getId() == 1) {
            throw new SocialSystemException("默认开发者不该进入校验内容是否重复逻辑");
        }
        UniContentUnionIdDO uniThirdContentDO = uniContentUnionIdRepository.findByDataDevIdAndDataContentUnionId(devId, contentType, contentAddQO.getId());
        if (uniThirdContentDO == null) {
            return;
        }
        uniThirdContentDO = new UniThirdContentIdDO(devId, contentType, contentAddQO.getId(), contentId);

        uniThirdContentRepository.save(uniThirdContentDO);
    }*/
}
