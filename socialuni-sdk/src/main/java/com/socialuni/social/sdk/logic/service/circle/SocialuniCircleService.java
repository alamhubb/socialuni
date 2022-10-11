package com.socialuni.social.sdk.logic.service.circle;

import com.socialuni.social.tance.sdk.enumeration.SocialuniSystemConst;
import com.socialuni.social.sdk.logic.domain.circle.CircleQueryDomain;
import com.socialuni.social.sdk.logic.factory.community.SocialCircleROFactory;
import com.socialuni.social.sdk.feignAPI.community.SocialuniCircleAPI;
import com.socialuni.social.community.sdk.model.SocialuniCircleModel;
import com.socialuni.social.user.sdk.model.SocialuniUserModel;
import com.socialuni.social.sdk.model.QO.circle.SocialuniCircleQueryByTypeQO;
import com.socialuni.social.sdk.model.QO.community.circle.CircleCreateQO;
import com.socialuni.social.sdk.model.RO.community.circle.CircleTypeRO;
import com.socialuni.social.sdk.model.RO.community.circle.SocialCircleRO;
import com.socialuni.social.community.sdk.api.SocialCircleApi;
import com.socialuni.social.sdk.dao.store.SocialuniCircleRedis;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.sdk.utils.SocialuniUserUtil;
import com.socialuni.social.common.model.ResultRO;
import com.socialuni.social.tance.sdk.enumeration.GenderType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SocialuniCircleService {
    @Resource
    private SocialCircleApi socialCircleApi;
    @Resource
    private SocialuniCircleRedis socialCircleRedis;

    @Resource
    SocialuniCircleAPI socialuniCircleAPI;
    @Resource
    CircleQueryDomain circleQueryDomain;

    public ResultRO<SocialCircleRO> createCircle(CircleCreateQO circleCreateQO, SocialuniUserModel user) {
        SocialuniCircleModel circleDO = new SocialuniCircleModel(circleCreateQO.getCircleName(), circleCreateQO.getCircleDesc(), DevAccountFacade.getDevIdNotNull(), user);
        circleDO = socialCircleApi.savePut(circleDO);
        return new ResultRO<>(SocialCircleROFactory.getCircleRO(circleDO));
    }

    public ResultRO<SocialCircleRO> createCircle(CircleCreateQO circleCreateQO) {

        SocialuniCircleModel circleDO = new SocialuniCircleModel(circleCreateQO.getCircleName(), circleCreateQO.getCircleDesc(), DevAccountFacade.getDevIdNotNull(), SocialuniUserUtil.getMineUserNotNull());
        circleDO = socialCircleApi.savePut(circleDO);

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
