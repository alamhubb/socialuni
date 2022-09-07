package com.socialuni.sdk.service.circle;

import com.socialuni.sdk.factory.community.SocialCircleROFactory;
import com.socialuni.sdk.store.SocialCircleRedis;
import com.socialuni.sdk.model.DO.circle.SocialCircleDO;
import com.socialuni.sdk.model.DO.user.SocialUserDO;
import com.socialuni.sdk.repository.community.SocialCircleRepository;
import com.socialuni.sdk.utils.DevAccountUtils;
import com.socialuni.social.web.sdk.model.ResultRO;
import com.socialuni.sdk.constant.socialuni.GenderType;
import com.socialuni.sdk.model.QO.community.circle.CircleCreateQO;
import com.socialuni.sdk.model.RO.community.circle.CircleTypeRO;
import com.socialuni.sdk.model.RO.community.circle.SocialCircleRO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SocialCircleService {
    @Resource
    private SocialCircleRepository socialCircleRepository;
    @Resource
    private SocialCircleRedis socialCircleRedis;

    public ResultRO<SocialCircleRO> createCircle(CircleCreateQO circleCreateQO, SocialUserDO user) {
        SocialCircleDO circleDO = new SocialCircleDO(circleCreateQO.getCircleName(), circleCreateQO.getCircleDesc(), DevAccountUtils.getDevIdNotNull(), user);
        circleDO = socialCircleRepository.save(circleDO);
        return new ResultRO<>(SocialCircleROFactory.getCircleRO(circleDO));
    }


    public ResultRO<List<SocialCircleRO>> queryHotCircles() {
        return ResultRO.success(socialCircleRedis.getHotCirclesRedis(GenderType.all));
    }

    public ResultRO<List<CircleTypeRO>> queryCircleTypes() {
        return ResultRO.success(socialCircleRedis.getAllCircleTypesRedis(GenderType.all));
    }
}
