package com.socialuni.social.sdk.logic.domain.circle;

import com.socialuni.social.sdk.logic.factory.community.SocialCircleROFactory;
import com.socialuni.social.community.sdk.model.SocialuniCircleModel;
import com.socialuni.social.community.sdk.model.SocialuniTagTypeModel;
import com.socialuni.social.sdk.model.QO.circle.SocialuniCircleQueryByTypeQO;
import com.socialuni.social.sdk.model.RO.community.circle.SocialCircleRO;
import com.socialuni.social.community.sdk.api.SocialuniTagTypeInterface;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CircleQueryDomain {
    @Resource
    SocialuniTagTypeInterface tagTypeRepository;
    @Resource
    com.socialuni.social.sdk.dao.store.SocialuniCircleRedis SocialuniCircleRedis;

    public List<SocialCircleRO> queryCirclesByCircleType(SocialuniCircleQueryByTypeQO socialuniCircleQueryByTypeQO) {
        String circleTypeName = socialuniCircleQueryByTypeQO.getCircleTypeName();
        SocialuniTagTypeModel tagTypeDO = tagTypeRepository.findFirstByName(circleTypeName);
        if (tagTypeDO == null) {
            return new ArrayList<>();
        }
        List<?  extends SocialuniCircleModel> list = SocialuniCircleRedis.getCirclesByCircleTypeId(tagTypeDO.getId());
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
