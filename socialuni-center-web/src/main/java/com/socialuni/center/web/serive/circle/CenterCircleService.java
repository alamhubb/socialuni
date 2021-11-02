package com.socialuni.center.web.serive.circle;

import com.socialuni.center.sdk.utils.DevAccountUtils;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.entity.model.DO.circle.SocialCircleDO;
import com.socialuni.social.model.model.QO.community.circle.CircleCreateQO;
import com.socialuni.social.sdk.repository.SocialCircleRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CenterCircleService {
    @Resource
    private SocialCircleRepository socialCircleRepository;

    public ResultRO<SocialCircleDO> createCircle(CircleCreateQO circleCreateQO) {

        SocialCircleDO circleDO = new SocialCircleDO(circleCreateQO.getCircleName(), circleCreateQO.getCircleDesc(), DevAccountUtils.getDevId(), CenterUserUtil.getMineUser());
        circleDO = socialCircleRepository.save(circleDO);
        return new ResultRO<>(circleDO);
    }
}
