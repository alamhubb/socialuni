package com.socialuni.social.community.sdk.logic.domain.circle;

import com.socialuni.social.common.sdk.dao.DO.SocialuniCircleDO;
import com.socialuni.social.common.sdk.dao.DO.SocialuniTagTypeDO;
import com.socialuni.social.common.sdk.dao.repository.SocialuniTagTypeRepository;
import com.socialuni.social.common.sdk.logic.SocialCircleROFactory;
import com.socialuni.social.community.sdk.model.QO.circle.SocialuniCircleQueryByTypeQO;
import com.socialuni.social.common.sdk.model.RO.SocialCircleRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CircleQueryDomain {
    @Resource
    SocialuniTagTypeRepository tagTypeRepository;
    @Resource
    com.socialuni.social.common.sdk.dao.redis.SocialuniCircleRedis SocialuniCircleRedis;

    public List<SocialCircleRO> queryCirclesByCircleType(SocialuniCircleQueryByTypeQO socialuniCircleQueryByTypeQO) {
        String circleTypeName = socialuniCircleQueryByTypeQO.getCircleTypeName();
        SocialuniTagTypeDO tagTypeDO = tagTypeRepository.findFirstByName(circleTypeName);
        if (tagTypeDO == null) {
            return new ArrayList<>();
        }
        List<?  extends SocialuniCircleDO> list = SocialuniCircleRedis.getCirclesByCircleTypeId(tagTypeDO.getId());
        return SocialCircleROFactory.circleDOToROS(list);
    }

    /*@Async
    public void createCircle(String schollName, String cityName) {
        SocialCircleDO circleDO = new SocialCircleDO(schollName, schollName, 1, null);
        circleDO.setCityName(cityName);
        circleDO.setTagTypeId(36);
        socialCircleRepository.save(circleDO);
    }*/
}
