package com.socialuni.social.sdk.service.circle;

import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.GenderType;
import com.socialuni.social.entity.model.DO.circle.SocialCircleDO;
import com.socialuni.social.entity.model.DO.user.UserDO;
import com.socialuni.social.model.model.QO.community.circle.CircleCreateQO;
import com.socialuni.social.model.model.RO.community.circle.CircleTypeRO;
import com.socialuni.social.model.model.RO.community.circle.SocialCircleRO;
import com.socialuni.social.sdk.factory.community.SocialCircleROFactory;
import com.socialuni.social.sdk.repository.community.SocialCircleRepository;
import com.socialuni.social.sdk.store.SocialCircleRedis;
import com.socialuni.social.sdk.utils.DevAccountUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SocialCircleService {
    @Resource
    private SocialCircleRepository socialCircleRepository;
    @Resource
    private SocialCircleRedis socialCircleRedis;

    public ResultRO<SocialCircleRO> createCircle(CircleCreateQO circleCreateQO, UserDO user) {
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
