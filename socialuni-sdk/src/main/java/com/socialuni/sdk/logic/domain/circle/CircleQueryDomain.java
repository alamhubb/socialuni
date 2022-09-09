package com.socialuni.sdk.logic.domain.circle;

import com.socialuni.sdk.constant.socialuni.CommonStatus;
import com.socialuni.sdk.logic.factory.community.SocialCircleROFactory;
import com.socialuni.sdk.dao.DO.circle.SocialuniCircleDO;
import com.socialuni.sdk.dao.DO.tag.SocialuniTagTypeDO;
import com.socialuni.sdk.model.QO.circle.SocialuniCircleQueryByTypeQO;
import com.socialuni.sdk.model.RO.talk.circle.SocialCircleRO;
import com.socialuni.sdk.dao.repository.community.SocialCircleRepository;
import com.socialuni.sdk.dao.repository.community.SocialuniTagTypeRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CircleQueryDomain {
    @Resource
    SocialuniTagTypeRepository tagTypeRepository;
    @Resource
    SocialCircleRepository socialCircleRepository;

    public List<SocialCircleRO> queryCirclesByCircleType(SocialuniCircleQueryByTypeQO socialuniCircleQueryByTypeQO) {
        String circleTypeName = socialuniCircleQueryByTypeQO.getCircleTypeName();
        SocialuniTagTypeDO tagTypeDO = tagTypeRepository.findFirstByName(circleTypeName);
        if (tagTypeDO == null) {
            return new ArrayList<>();
        }
        List<SocialuniCircleDO> list = socialCircleRepository.findByTagTypeIdAndStatusOrderByCountDesc(tagTypeDO.getId(), CommonStatus.enable);

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
