package com.socialuni.social.likee.logic.service;

import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.model.user.SocialuniUserIdQO;
import com.socialuni.social.im.api.model.QO.message.MessageAddVO;
import com.socialuni.social.likee.dao.DO.SocialuniUserLikeDO;
import com.socialuni.social.likee.logic.manage.SocialuniUserLikeManageHa;
import com.socialuni.social.sdk.im.logic.service.SocialuniMessageService;
import com.socialuni.social.tance.sdk.facade.SocialuniUnionIdFacede;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Objects;

@Service
public class SocialuniUserLikeService {
    @Resource
    SocialuniUserLikeManageHa socialuniUserLikeManage;

//    @Resource
//    private SocialuniMessageService messageService;

    @Transactional
    public SocialuniUserLikeDO likeUser(SocialuniUserIdQO addVO) {
        //有问题，应该关注完刷新前台用户
        Integer mineUserId = SocialuniUserUtil.getMineUserIdNotNull();

        Integer beUserId = SocialuniUnionIdFacede.getUnionIdByUuidNotNull(addVO.getUserId());
        if (Objects.equals(beUserId, mineUserId)) {
            throw new SocialParamsException("不能喜欢自己哦");
        }
        SocialuniUserLikeDO socialuniUserLikeDO = socialuniUserLikeManage.createOrUpdateLikeStatus(mineUserId, beUserId);

//        sendLikeUserMsg(addVO.getUserId());
        return socialuniUserLikeDO;
    }

//    public void sendLikeUserMsg(String receiveUserId) {
//        MessageAddVO msgAddVO = new MessageAddVO();
//        msgAddVO.setReceiveId(receiveUserId);
//        msgAddVO.setType("喜欢");
//        msgAddVO.setContent("你好，在干嘛呢");
//        //msg支持自定义的展示类型
//        messageService.sendMsg(msgAddVO);
//    }
}
