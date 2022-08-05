package com.socialuni.center.web.service.circle;

import com.socialuni.center.web.factory.community.SocialCircleROFactory;
import com.socialuni.center.web.store.SocialCircleRedis;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.GenderType;
import com.socialuni.center.web.model.DO.circle.SocialCircleDO;
import com.socialuni.center.web.model.DO.user.SocialUserDO;
import com.socialuni.center.web.model.QO.community.circle.CircleCreateQO;
import com.socialuni.center.web.model.RO.community.circle.CircleTypeRO;
import com.socialuni.center.web.model.RO.community.circle.SocialCircleRO;
import com.socialuni.center.web.repository.community.SocialCircleRepository;
import com.socialuni.center.web.utils.DevAccountUtils;
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
