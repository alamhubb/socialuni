package com.socialuni.center.web.serive.circle;

import com.socialuni.center.sdk.utils.DevAccountUtils;
import com.socialuni.center.web.utils.CenterUserUtil;
import com.socialuni.social.api.model.ResultRO;
import com.socialuni.social.constant.ContentStatus;
import com.socialuni.social.constant.GenderType;
import com.socialuni.social.entity.model.DO.circle.SocialCircleDO;
import com.socialuni.social.model.model.QO.community.circle.CircleCreateQO;
import com.socialuni.social.model.model.RO.community.circle.CircleTypeRO;
import com.socialuni.social.model.model.RO.community.circle.SocialCircleRO;
import com.socialuni.social.model.model.RO.community.tag.TagRO;
import com.socialuni.social.sdk.factory.community.SocialCircleROFactory;
import com.socialuni.social.sdk.factory.community.SocialTagROFactory;
import com.socialuni.social.sdk.repository.community.SocialCircleRepository;
import com.socialuni.social.sdk.store.SocialCircleRedis;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CenterCircleService {
    @Resource
    private SocialCircleRepository socialCircleRepository;
    @Resource
    private SocialCircleRedis SocialCircleRedis;

    public ResultRO<SocialCircleRO> createCircle(CircleCreateQO circleCreateQO) {

        SocialCircleDO circleDO = new SocialCircleDO(circleCreateQO.getCircleName(), circleCreateQO.getCircleDesc(), DevAccountUtils.getDevId(), CenterUserUtil.getMineUser());
        circleDO = socialCircleRepository.save(circleDO);
        return new ResultRO<>(SocialCircleROFactory.getCircleRO(circleDO));
    }


    public ResultRO<List<SocialCircleRO>> queryHotCircles() {
        return ResultRO.success(SocialCircleRedis.getHotCirclesRedis(GenderType.all));
    }

    public ResultRO<List<CircleTypeRO>> queryCircleTypes() {
        return ResultRO.success(SocialCircleRedis.getAllCircleTypesRedis(GenderType.all));
    }


}
