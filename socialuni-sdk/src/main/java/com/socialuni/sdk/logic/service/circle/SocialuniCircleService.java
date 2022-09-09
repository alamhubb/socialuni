package com.socialuni.sdk.logic.service.circle;

import com.socialuni.sdk.config.SocialAppConfig;
import com.socialuni.sdk.logic.factory.community.SocialCircleROFactory;
import com.socialuni.sdk.feignAPI.SocialuniCircleAPI;
import com.socialuni.sdk.dao.DO.circle.SocialCircleDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.model.QO.community.circle.CircleCreateQO;
import com.socialuni.sdk.model.RO.talk.circle.CircleTypeRO;
import com.socialuni.sdk.model.RO.talk.circle.SocialCircleRO;
import com.socialuni.sdk.dao.repository.community.SocialCircleRepository;
import com.socialuni.sdk.dao.store.SocialuniCircleRedis;
import com.socialuni.sdk.utils.DevAccountUtils;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.web.sdk.model.ResultRO;
import com.socialuni.sdk.constant.socialuni.GenderType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SocialuniCircleService {
    @Resource
    private SocialCircleRepository socialCircleRepository;
    @Resource
    private SocialuniCircleRedis socialCircleRedis;

    @Resource
    SocialuniCircleAPI socialuniCircleAPI;

    public ResultRO<SocialCircleRO> createCircle(CircleCreateQO circleCreateQO, SocialuniUserDO user) {
        SocialCircleDO circleDO = new SocialCircleDO(circleCreateQO.getCircleName(), circleCreateQO.getCircleDesc(), DevAccountUtils.getDevIdNotNull(), user);
        circleDO = socialCircleRepository.save(circleDO);
        return new ResultRO<>(SocialCircleROFactory.getCircleRO(circleDO));
    }

    public ResultRO<SocialCircleRO> createCircle(CircleCreateQO circleCreateQO) {

        SocialCircleDO circleDO = new SocialCircleDO(circleCreateQO.getCircleName(), circleCreateQO.getCircleDesc(), DevAccountUtils.getDevIdNotNull(), SocialuniUserUtil.getMineUserNotNull());
        circleDO = socialCircleRepository.save(circleDO);

        ResultRO<SocialCircleRO> resultRO = new ResultRO<>(SocialCircleROFactory.getCircleRO(circleDO));

        //如果应用，则调用中心
        if (SocialAppConfig.serverIsChild()) {
            resultRO = socialuniCircleAPI.createCircle(circleCreateQO);
        }
        return resultRO;
    }


    public ResultRO<List<SocialCircleRO>> queryHotCircles() {
        //如果应用，则调用中心
        if (SocialAppConfig.serverIsChild()) {
            return socialuniCircleAPI.queryHotCircles();
        }
        return ResultRO.success(socialCircleRedis.getHotCirclesRedis(GenderType.all));
    }

    public ResultRO<List<CircleTypeRO>> queryCircleTypes() {
        if (SocialAppConfig.serverIsChild()) {
            return socialuniCircleAPI.queryCircleTypes();
        }
        return ResultRO.success(socialCircleRedis.getAllCircleTypesRedis(GenderType.all));
    }


}
