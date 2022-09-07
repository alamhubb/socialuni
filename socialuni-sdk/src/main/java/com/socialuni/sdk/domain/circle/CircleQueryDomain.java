package com.socialuni.sdk.domain.circle;

import com.socialuni.sdk.constant.socialuni.CommonStatus;
import com.socialuni.sdk.factory.community.SocialCircleROFactory;
import com.socialuni.sdk.model.DO.circle.SocialCircleDO;
import com.socialuni.sdk.model.DO.tag.TagTypeDO;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.model.QO.community.circle.CircleCreateQO;
import com.socialuni.sdk.model.RO.community.circle.SocialCircleRO;
import com.socialuni.sdk.repository.community.SocialCircleRepository;
import com.socialuni.sdk.repository.community.TagTypeRepository;
import com.socialuni.sdk.utils.DevAccountUtils;
import com.socialuni.sdk.utils.SocialUserUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CircleQueryDomain {
    @Resource
    TagTypeRepository tagTypeRepository;
    @Resource
    SocialCircleRepository socialCircleRepository;

    public List<SocialCircleRO> queryCirclesByCircleType(String circleTypeName) {
        TagTypeDO tagTypeDO = tagTypeRepository.findFirstByName(circleTypeName);
        if (tagTypeDO == null) {
            return new ArrayList<>();
        }
        List<SocialCircleDO> list = socialCircleRepository.findByTagTypeIdAndStatusOrderByCountDesc(tagTypeDO.getId(), CommonStatus.enable);

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
