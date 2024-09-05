package com.socialuni.social.tag.logic;

import com.socialuni.social.common.api.model.ResultRO;
import com.socialuni.social.sdk.im.dao.DO.SocialuniChatDO;
import com.socialuni.social.tag.dao.DO.SocialuniCircleDO;
import com.socialuni.social.tag.dao.repository.SocialuniCircleRepository;
import com.socialuni.social.tag.dao.redis.SocialuniCircleRedis;
import com.socialuni.social.tag.api.SocialuniCircleAPI;
import com.socialuni.social.tag.factory.SocialCircleROFactory;
import com.socialuni.social.common.sdk.model.QO.circle.CircleChatCreateQO;
import com.socialuni.social.common.sdk.model.QO.circle.CircleCreateQO;
import com.socialuni.social.common.sdk.model.QO.circle.CircleTalkTabInfoQO;
import com.socialuni.social.common.sdk.model.QO.circle.SocialuniCircleQueryByTypeQO;
import com.socialuni.social.tag.logic.manage.SocialuniCircleChatManage;
import com.socialuni.social.tag.model.SocialuniTalkTabCircleRO;
import com.socialuni.social.tag.model.CircleTypeRO;
import com.socialuni.social.common.sdk.model.RO.SocialCircleRO;
import com.socialuni.social.common.api.constant.GenderType;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.tag.util.SocialuniCircleDOUtil;
import com.socialuni.social.tance.dev.config.SocialuniDevConfig;
import com.socialuni.social.tance.dev.facade.DevAccountFacade;
import com.socialuni.social.tance.dev.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SocialuniUnionIdFacede socialuniUnionIdFacede;

    public ResultRO<SocialCircleRO> createCircle(CircleCreateQO circleCreateQO, SocialuniUserDo user) {
        SocialuniCircleDO circleDO = new SocialuniCircleDO(circleCreateQO.getCircleName(), circleCreateQO.getCircleDesc(), DevAccountFacade.getDevIdNullElseCenterDevId(), user.getUserId());
        circleDO = socialCircleApi.savePut(circleDO);
        return new ResultRO<>(SocialCircleROFactory.getCircleRO(circleDO));
    }

    public ResultRO<SocialCircleRO> createCircle(CircleCreateQO circleCreateQO) {

        SocialuniCircleDO circleDO = new SocialuniCircleDO(circleCreateQO.getCircleName(), circleCreateQO.getCircleDesc(), DevAccountFacade.getDevIdNullElseCenterDevId(), SocialuniUserUtil.getMineUserNotNull().getUserId());
        circleDO = socialCircleApi.savePut(circleDO);

        ResultRO<SocialCircleRO> resultRO = new ResultRO<>(SocialCircleROFactory.getCircleRO(circleDO));

        //如果应用，则调用中心
        if (SocialuniDevConfig.hasCenterServer()) {
            resultRO = socialuniCircleAPI.createCircle(circleCreateQO);
        }
        return resultRO;
    }

    //创建圈子群聊
    public ResultRO<String> createCircleChat(CircleChatCreateQO circleCreateQO) {
        Integer devId = DevAccountFacade.getDevIdNullElseCenterDevId();
        String tabName = circleCreateQO.getCircleName();
        SocialuniCircleDO socialuniCircleDO = SocialuniCircleDOUtil.getCircleEnableNotNull(tabName);
        //获取groupId
        SocialuniChatDO socialuniChatDO = socialuniCircleChatManage.getOrCreateCircleGroupChat(tabName, socialuniCircleDO.getAvatar());

        /*SocialuniCircleChatDO socialuniCircleChatDO1 = socialuniCircleChatRepository.findFirstByDevIdAndCircleName(socialuniCircleChatDO.getDevId(), socialuniCircleChatDO.getCircleName());
        if (socialuniCircleChatDO1 == null) {
            socialuniCircleChatDO1 = socialuniCircleChatRepository.save(socialuniCircleChatDO);
        }*/
        if (socialuniChatDO == null) {
        }
            String chatId = SocialuniUnionIdFacede.getUuidByUnionIdNotNull(socialuniChatDO.getUnionId());
            return ResultRO.success(chatId);
    }

    @Resource
    SocialuniCircleChatManage socialuniCircleChatManage;

    public ResultRO<SocialuniTalkTabCircleRO> queryCircleTalkTabInfo(CircleTalkTabInfoQO circleTalkTabInfoQO) {
        String tabName = circleTalkTabInfoQO.getCircleName();
        SocialuniCircleDO socialuniCircleDO = SocialuniCircleDOUtil.getCircleEnableNotNull(tabName);
        SocialuniTalkTabCircleRO homeTabCircleRO = new SocialuniTalkTabCircleRO(socialuniCircleDO);

        //获取groupId
        SocialuniChatDO socialuniChatDO = socialuniCircleChatManage.getOrCreateCircleGroupChat(tabName, socialuniCircleDO.getAvatar());

        if (socialuniChatDO != null) {
            String chatId = SocialuniUnionIdFacede.getUuidByUnionIdNotNull(socialuniChatDO.getUnionId());
            homeTabCircleRO.setGroupChatId(chatId);
            homeTabCircleRO.setChatId(chatId);
        }
        return ResultRO.success(homeTabCircleRO);
    }


    public ResultRO<List<SocialCircleRO>> queryHotCircles() {
        //如果应用，则调用中心
        if (SocialuniDevConfig.hasCenterServer()) {
            return socialuniCircleAPI.queryHotCircles();
        }
        return ResultRO.success(socialCircleRedis.getHotCirclesRedis(GenderType.all));
    }

    public ResultRO<List<CircleTypeRO>> queryCircleTypes() {
        if (SocialuniDevConfig.hasCenterServer()) {
            return socialuniCircleAPI.queryCircleTypes();
        }
        return ResultRO.success(socialCircleRedis.getAllCircleTypesRedis(GenderType.all));
    }

    public ResultRO<List<CircleTypeRO>> queryHotCircleTypes() {
        if (SocialuniDevConfig.hasCenterServer()) {
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
