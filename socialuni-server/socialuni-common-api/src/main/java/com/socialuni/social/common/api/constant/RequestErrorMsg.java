package com.socialuni.social.common.api.constant;




import com.socialuni.social.common.api.utils.RequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class RequestErrorMsg {

    public static String getSystemErrorMsg() {
        String errMsg = "系统出错，请联系客服，微信或qq";
        String account = getAccount(errMsg);
        if (account != null) return errMsg + ":" + account;
        return errMsg;
    }

    public static String getAccount(String errMsg) {
        HttpServletRequest request = RequestUtil.getRequest();
        if(request != null){
            Map<String, Object> allConfigsOfMap = (Map<String, Object>) request.getAttribute(SocialSystemConst.CONFIGS_REQUEST_NAME);
            if(allConfigsOfMap == null) return  null;
            Object cacheObj = allConfigsOfMap.get(SocialSystemConst.CONFIGS_KEY_QQ_ACCOUNT);
            return String.valueOf(cacheObj);
        }
        return null;
    }


}
