package com.socialuni.center.web.serive.circle;

import com.socialuni.center.web.utils.DevAccountUtils;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.GenderType;
import com.socialuni.social.entity.model.DO.circle.SocialCircleDO;
import com.socialuni.social.model.model.QO.community.circle.CircleCreateQO;
import com.socialuni.social.model.model.RO.community.circle.CircleTypeRO;
import com.socialuni.social.model.model.RO.community.circle.SocialCircleRO;
import com.socialuni.center.web.factory.community.SocialCircleROFactory;
import com.socialuni.center.web.repository.community.SocialCircleRepository;
import com.socialuni.center.web.store.SocialCircleRedis;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CenterCircleService {
    @Resource
    private SocialCircleRepository socialCircleRepository;
    @Resource
    private SocialCircleRedis socialCircleRedis;

    public ResultRO<SocialCircleRO> createCircle(CircleCreateQO circleCreateQO) {

        SocialCircleDO circleDO = new SocialCircleDO(circleCreateQO.getCircleName(), circleCreateQO.getCircleDesc(), DevAccountUtils.getDevIdNotNull(), CenterUserUtil.getMineUserNotNull());
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
