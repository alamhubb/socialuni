package com.socialuni.social.admin.facede;

import com.socialuni.social.common.api.exception.exception.SocialNotLoginException;
import com.socialuni.social.common.api.exception.exception.SocialParamsException;
import com.socialuni.social.common.api.utils.SocialTokenFacade;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.tance.sdk.facade.DevAccountFacade;
import com.socialuni.social.tance.sdk.model.DevAccountModel;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;

public class AdminDevAccountFacade {

    public static DevAccountModel getAdminDevAccountNotNull() {
        DevAccountModel user = AdminDevAccountFacade.getAdminDevAccountAllowNull();
        if (user == null) {
            throw new SocialNotLoginException();
        }
        return user;
    }

    public static DevAccountModel getAdminDevAccountAllowNull() {
        String token = SocialTokenFacade.getToken();
        return AdminDevAccountFacade.getDevAccountByToken(token);
    }

    public static Integer getAdminDevAccountIdAllowNull() {
        DevAccountModel devAccountModel = getAdminDevAccountAllowNull();
        if (devAccountModel == null) {
            return null;
        }
        return devAccountModel.getId();
    }

    //得到用户信息
    private static DevAccountModel getDevAccountByToken(String token) {
        SocialuniUserDo socialuniUserDo = SocialuniUserUtil.getUserByToken(token);
        if (socialuniUserDo == null) {
            throw new SocialParamsException("token被破解");
        }
        DevAccountModel devAccountModel = DevAccountFacade.getDevAccountByUserId(socialuniUserDo.getUserId());
        return devAccountModel;
    }


    public static boolean isCenter() {
        DevAccountModel devAccountModel = AdminDevAccountFacade.getAdminDevAccountNotNull();
        return devAccountModel.getId() == 1;
    }
}
