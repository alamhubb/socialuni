package com.socialuni.social.user.sdk.factory;

import com.socialuni.social.common.sdk.constant.SocialuniCommmonRoleConst;
import com.socialuni.social.common.sdk.constant.SocialuniCommmonRoleType;
import com.socialuni.social.common.sdk.dao.DO.SocialuniAppOperateRecordDO;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.user.sdk.dao.utils.SocialuniUserDOUtil;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;

public class SocialuniAppOperateRecordDOFactory {

    /*public static SocialuniAppOperateRecordDO createChatAppOperateRecordDO(String contentType, Integer contentId, String type, Integer roleId, String roleType, Integer userId, Integer beUserId) {
        SocialuniAppOperateRecordDO socialuniAppOperateRecordDO = new SocialuniAppOperateRecordDO();

        socialuniAppOperateRecordDO.setContentType(contentType);
        socialuniAppOperateRecordDO.setContentId(contentId);
        socialuniAppOperateRecordDO.setType(type);
        socialuniAppOperateRecordDO.setUserId(userId);
        socialuniAppOperateRecordDO.setBeUserId();
        socialuniAppOperateRecordDO.setRoleId();
        socialuniAppOperateRecordDO.setRoleType();
        //操作主题， 群组， 动态等，
        String operateContentType;
        String operateContentId;
        //角色类型，系统管理员，
        String roleType;
        String roleId;
    }*/


    public static SocialuniAppOperateRecordDO createChatAppOperateRecordDOBySys(String contentType, Integer contentId, String type, Integer beUserId, String remark) {
        SocialuniAppOperateRecordDO socialuniAppOperateRecordDO = new SocialuniAppOperateRecordDO();

        socialuniAppOperateRecordDO.setContentType(contentType);
        socialuniAppOperateRecordDO.setContentId(contentId);
        socialuniAppOperateRecordDO.setType(type);
        socialuniAppOperateRecordDO.setRemark(remark);
        socialuniAppOperateRecordDO.setUserId(SocialuniUserUtil.getSystemUserIdNotNull());
        socialuniAppOperateRecordDO.setBeUserId(beUserId);
        socialuniAppOperateRecordDO.setRoleId(SocialuniCommmonRoleConst.admin);
        socialuniAppOperateRecordDO.setRoleType(SocialuniCommmonRoleType.systemManage);

        socialuniAppOperateRecordDO = SocialuniRepositoryFacade.save(socialuniAppOperateRecordDO);

        return socialuniAppOperateRecordDO;

    }
}
