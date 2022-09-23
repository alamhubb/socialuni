package com.socialuni.sdk.logic.service.circle;

import com.socialuni.sdk.config.SocialuniSystemConst;
import com.socialuni.sdk.logic.domain.circle.CircleQueryDomain;
import com.socialuni.sdk.logic.factory.community.SocialCircleROFactory;
import com.socialuni.sdk.feignAPI.community.SocialuniCircleAPI;
import com.socialuni.sdk.dao.DO.circle.SocialuniCircleDO;
import com.socialuni.sdk.dao.DO.user.SocialuniUserDO;
import com.socialuni.sdk.model.QO.circle.SocialuniCircleQueryByTypeQO;
import com.socialuni.sdk.model.QO.community.circle.CircleCreateQO;
import com.socialuni.sdk.model.RO.community.circle.CircleTypeRO;
import com.socialuni.sdk.model.RO.community.circle.SocialCircleRO;
import com.socialuni.sdk.dao.repository.community.SocialCircleRepository;
import com.socialuni.sdk.dao.store.SocialuniCircleRedis;
import com.socialuni.sdk.utils.DevAccountUtils;
import com.socialuni.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.model.ResultRO;
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
    @Resource
    CircleQueryDomain circleQueryDomain;

    public ResultRO<SocialCircleRO> createCircle(CircleCreateQO circleCreateQO, SocialuniUserDO user) {
        SocialuniCircleDO circleDO = new SocialuniCircleDO(circleCreateQO.getCircleName(), circleCreateQO.getCircleDesc(), DevAccountUtils.getDevIdNotNull(), user);
        circleDO = socialCircleRepository.save(circleDO);
        return new ResultRO<>(SocialCircleROFactory.getCircleRO(circleDO));
    }

    public ResultRO<SocialCircleRO> createCircle(CircleCreateQO circleCreateQO) {

        SocialuniCircleDO circleDO = new SocialuniCircleDO(circleCreateQO.getCircleName(), circleCreateQO.getCircleDesc(), DevAccountUtils.getDevIdNotNull(), SocialuniUserUtil.getMineUserNotNull());
        circleDO = socialCircleRepository.save(circleDO);

        ResultRO<SocialCircleRO> resultRO = new ResultRO<>(SocialCircleROFactory.getCircleRO(circleDO));

        //如果应用，则调用中心
        if (SocialuniSystemConst.serverIsChild()) {
            resultRO = socialuniCircleAPI.createCircle(circleCreateQO);
        }
        return resultRO;
    }


    public ResultRO<List<SocialCircleRO>> queryHotCircles() {
        //如果应用，则调用中心
        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniCircleAPI.queryHotCircles();
        }
        return ResultRO.success(socialCircleRedis.getHotCirclesRedis(GenderType.all));
    }

    public ResultRO<List<CircleTypeRO>> queryCircleTypes() {
        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniCircleAPI.queryCircleTypes();
        }
        return ResultRO.success(socialCircleRedis.getAllCircleTypesRedis(GenderType.all));
    }

    public ResultRO<List<CircleTypeRO>> queryHotCircleTypes() {
        if (SocialuniSystemConst.serverIsChild()) {
            return socialuniCircleAPI.queryHotCircleTypes();
        }
        List<CircleTypeRO> list = socialCircleRedis.getHotCircleTypesRedis(GenderType.all);
        return ResultRO.success(list);
    }

    public ResultRO<List<SocialCircleRO>> queryCirclesByCircleType(SocialuniCircleQueryByTypeQO socialuniCircleQueryByTypeQO) {
        List<SocialCircleRO> list = circleQueryDomain.queryCirclesByCircleType(socialuniCircleQueryByTypeQO);
        return ResultRO.success(list);
    }
}
