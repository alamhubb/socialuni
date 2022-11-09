package com.socialuni.social.sdk.logic.check;

import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.community.sdk.entity.SocialuniCircleDO;
import com.socialuni.social.community.sdk.entity.SocialuniTagTypeDO;
import com.socialuni.social.community.sdk.repository.SocialuniTagTypeRepository;
import com.socialuni.social.common.sdk.constant.SocialuniConst;
import com.socialuni.social.sdk.dao.store.SocialuniCircleRedis;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SocialuniSchoolCheck {
    @Resource
    SocialuniTagTypeRepository socialuniTagTypeApi;
    @Resource
    SocialuniCircleRedis socialCircleRedis;

    public void checkSchoolName(String schoolName) {
        if (StringUtils.isEmpty(schoolName)) {
            throw new SocialParamsException("学校名称不能为空");
        }
        SocialuniTagTypeDO tagTypeDO = socialuniTagTypeApi.findFirstByName(SocialuniConst.schoolCircleTypeName);
        if (tagTypeDO == null) {
            throw new SocialParamsException("未找到大学圈子分类");
        }
        List<?  extends SocialuniCircleDO> circleDOS = socialCircleRedis.getCirclesByCircleTypeId(tagTypeDO.getId());

        if (circleDOS.stream().noneMatch(item -> item.getName().equals(schoolName))) {
            throw new SocialParamsException("不存在的大学名称，请添加大学名称");
        }
    }
}
