package com.socialuni.social.admin.facede;

import com.socialuni.social.common.api.exception.exception.SocialNotLoginException;
import com.qingchi.qing.common.exception.base.exception.SocialParamsException;
import com.socialuni.social.common.api.utils.SocialTokenFacade;
import com.socialuni.social.common.sdk.dao.DO.SocialuniUserDo;
import com.socialuni.social.tance.dev.dao.DO.DevAccountDo;
import com.socialuni.social.tance.dev.facade.DevAccountFacade;
import com.socialuni.social.user.sdk.utils.SocialuniUserUtil;

public class AdminDevAccountFacade {

    public static DevAccountDo getAdminDevAccountNotNull() {
        DevAccountDo user = AdminDevAccountFacade.getAdminDevAccountAllowNull();
        if (user == null) {
            throw new SocialNotLoginException();
        }
        return user;
    }

    public static DevAccountDo getAdminDevAccountAllowNull() {
        String token = SocialTokenFacade.getToken();
        return AdminDevAccountFacade.getDevAccountByToken(token);
    }

    public static Integer getAdminDevAccountIdAllowNull() {
        DevAccountDo devAccountDo = getAdminDevAccountAllowNull();
        if (devAccountDo == null) {
            return null;
        }
        return devAccountDo.getId();
    }

    //得到用户信息
    private static DevAccountDo getDevAccountByToken(String token) {
        if (token == null) {
            return null;
        }
        SocialuniUserDo socialuniUserDo = SocialuniUserUtil.getUserByToken(token);
        if (socialuniUserDo == null) {
            throw new SocialParamsException("token被破解");
        }
        DevAccountDo devAccountDo = DevAccountFacade.getDevAccountByUserId(socialuniUserDo.getUserId());
        return devAccountDo;
    }


    public static boolean isCenter() {
        DevAccountDo devAccountDo = AdminDevAccountFacade.getAdminDevAccountNotNull();
        return devAccountDo.getId() == 1;
    }
}
