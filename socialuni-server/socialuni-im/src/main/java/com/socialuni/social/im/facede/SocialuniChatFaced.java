package com.socialuni.social.im.facede;

import cn.hutool.core.util.NumberUtil;
import com.socialuni.social.common.sdk.dao.facede.SocialuniRepositoryFacade;
import com.socialuni.social.im.dao.DO.SocialuniChatDO;

public class SocialuniChatFaced {

    //根据 用户Id或者 群聊Id获取chat
    public static SocialuniChatDO getChatByComId(String receiveIdUid){
        //传入chatId

        //如果有用户则是chatUserId
        //否则是chatId


        SocialuniChatDO chat = null;
        /*if (NumberUtil.isNumber(receiveIdUid)) {


            chat = SocialuniRepositoryFacade.findById(Integer.valueOf(receiveIdUid), SocialuniChatDO.class);
        }else {
            chat = SocialuniRepositoryFacade.findById(chatUserDO.getChatId(), SocialuniChatDO.class);
        }*/
        return null;
    }
}
