package com.socialuni.social.im.logic.manage;

import com.socialuni.social.im.dao.DO.SocialuniChatDO;

import java.util.Date;

public class SocialuniChatDOFactory {

    public SocialuniChatDO createChat(String chatType){

        SocialuniChatDO chatDO = new SocialuniChatDO();
        chatDO.setType(chatType);

        return chatDO;


    }
}
