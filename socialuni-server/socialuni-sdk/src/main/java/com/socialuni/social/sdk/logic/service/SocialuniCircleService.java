package com.socialuni.social.sdk.logic.service;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.common.sdk.constant.SocialuniConst;
import com.socialuni.social.common.sdk.dao.DO.SocialuniCircleDO;
import com.socialuni.social.common.sdk.dao.repository.SocialuniCircleRepository;
import com.socialuni.social.common.sdk.dao.redis.SocialuniCircleRedis;
import com.socialuni.social.common.sdk.feignAPI.community.SocialuniCircleAPI;
import com.socialuni.social.common.sdk.logic.SocialCircleROFactory;
import com.socialuni.social.sdk.logic.domain.CircleQueryDomain;
import com.socialuni.social.common.sdk.model.QO.circle.CircleChatCreateQO;
import com.socialuni.social.common.sdk.model.QO.circle.CircleCreateQO;
import com.socialuni.social.common.sdk.model.QO.circle.CircleTalkTabInfoQO;
import com.socialuni.social.common.sdk.model.QO.circle.SocialuniCircleQueryByTypeQO;
import com.socialuni.social.common.sdk.model.RO.SocialuniTalkTabCircleRO;
import com.socialuni.social.common.sdk.model.RO.circle.CircleTypeRO;
import com.socialuni.social.common.sdk.model.RO.SocialCircleRO;
import com.socialuni.social.common.api.constant.GenderType;
import com.socialuni.social.common.api.constant.SocialuniSystemConst;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.community.sdk.dao.DO.SocialuniCircleChatDO;
import com.socialuni.social.community.sdk.dao.SocialuniCircleDOUtil;
import com.socialuni.social.community.sdk.repository.SocialuniCircleChatRepository;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SocialuniCircleService {
    @Resource
    private SocialuniCircleRepository socialCircleApi;
    @Resource
    private SocialuniCircleRedis socialCircleRedis;

    @Resource
    SocialuniCircleAPI socialuniCircleAPI;
    @Resource
    CircleQueryDomain circleQueryDomain;

    @Resource
    SocialuniCircleChatRepository socialuniCircleChatRepository;

    public ResultRO<SocialCircleRO> createCircle(CircleCreateQO circleCreateQO, SocialuniUserDo user) {
        SocialuniCircleDO circleDO = new SocialuniCircleDO(circleCreateQO.getCircleName(), circleCreateQO.getCircleDesc(), DevAccountFacade.getDevIdNotNull(), user.getUserId());
        circleDO = socialCircleApi.savePut(circleDO);
        return new ResultRO<>(SocialCircleROFactory.getCircleRO(circleDO));
    }

    public ResultRO<SocialCircleRO> createCircle(CircleCreateQO circleCreateQO) {

        SocialuniCircleDO circleDO = new SocialuniCircleDO(circleCreateQO.getCircleName(), circleCreateQO.getCircleDesc(), DevAccountFacade.getDevIdNotNull(), SocialuniUserUtil.getMineUserNotNull().getUserId());
        circleDO = socialCircleApi.savePut(circleDO);

        ResultRO<SocialCircleRO> resultRO = new ResultRO<>(SocialCircleROFactory.getCircleRO(circleDO));

        //如果应用，则调用中心
        if (SocialuniSystemConst.serverIsChild()) {
            resultRO = socialuniCircleAPI.createCircle(circleCreateQO);
        }
        return resultRO;
    }

    //创建圈子群聊
    public ResultRO<String> createCircleChat(CircleChatCreateQO circleCreateQO) {
        Integer devId = DevAccountFacade.getDevIdNotNull();

        SocialuniCircleChatDO socialuniCircleChatDO = new SocialuniCircleChatDO();
        socialuniCircleChatDO.setCircleName(circleCreateQO.getCircleName());
        socialuniCircleChatDO.setGroupChatId(circleCreateQO.getGroupChatId());
        socialuniCircleChatDO.setDevId(SocialuniConst.centerDevId);

        SocialuniCircleChatDO socialuniCircleChatDO1 = socialuniCircleChatRepository.findFirstByDevIdAndCircleName(socialuniCircleChatDO.getDevId(), socialuniCircleChatDO.getCircleName());
        if (socialuniCircleChatDO1 == null) {
            socialuniCircleChatDO1 = socialuniCircleChatRepository.save(socialuniCircleChatDO);
        }

        return ResultRO.success(socialuniCircleChatDO1.getGroupChatId());
    }


    public ResultRO<SocialuniTalkTabCircleRO> queryCircleTalkTabInfo(CircleTalkTabInfoQO circleTalkTabInfoQO) {
        String tabName = circleTalkTabInfoQO.getCircleName();
        SocialuniCircleDO socialuniCircleDO = SocialuniCircleDOUtil.getCircleEnableNotNull(tabName);
            SocialuniTalkTabCircleRO homeTabCircleRO = new SocialuniTalkTabCircleRO(socialuniCircleDO);
            SocialuniCircleChatDO socialuniCircleChatDO = socialuniCircleChatRepository.findFirstByDevIdAndCircleName(SocialuniConst.centerDevId, tabName);
            if (socialuniCircleChatDO != null) {
                homeTabCircleRO.setGroupChatId(socialuniCircleChatDO.getGroupChatId());
            }
        return ResultRO.success(homeTabCircleRO);
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