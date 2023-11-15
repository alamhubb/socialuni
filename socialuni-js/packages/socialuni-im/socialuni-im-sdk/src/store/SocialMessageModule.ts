import {nextTick, reactive} from "vue";
import ChatAPI from "@socialuni/socialuni-im-api/src/api/ChatAPI";
import SocialuniChatRO from "@socialuni/socialuni-api-base/src/model/SocialuniChatRO";
import LoadMoreType from "@socialuni/socialuni-constant/constant/LoadMoreType";
import ChatQueryQO from "@socialuni/socialuni-im-api/src/model/QO/chat/ChatQueryQO";
import MessageVO from "@socialuni/socialuni-im-api/src/model/RO/MessageVO";
import MessageAPI from "@socialuni/socialuni-im-api/src/api/MessageAPI";
import CommonUtil from "@socialuni/socialuni-util/src/util/CommonUtil";
import Arrays from "@socialuni/socialuni-util/src/util/Arrays";
import ImPageUtil from "../util/ImPageUtil";
import SocialuniAppUtil from "@socialuni/socialuni-native-util/src/util/SocialuniAppUtil";
import UserCheckUtil from "@socialuni/socialuni-user-sdk/src/util/UserCheckUtil";
import {socialuniUserModule} from "@socialuni/socialuni-user-sdk/src/store/SocialuniUserModule";
import {MessageStatus} from "@socialuni/socialuni-constant/constant/openIm/OpenImMessageType";
import SocialuniImMineUserDetailRO from "@socialuni/socialuni-im-api/src/model/RO/SocialuniImMineUserDetailRO";
import SocialuniImUserAPI from "@socialuni/socialuni-im-api/src/api/SocialuniImUserAPI";
import RouterUtil from "@socialuni/socialuni-native-h5/src/util/RouterUtil";
import MessageViewParams from "../model/MessageViewParams";
import {socialuniChatModule} from "./SocialuniChatModule";
import SocialuniUserEventConst from "@socialuni/socialuni-user-sdk/src/constant/SocialuniUserEventConst";
import CommonEventUtil from "@socialuni/socialuni-native-util/src/util/CommonEventUtil";
import SocialuniImEventKey from "@socialuni/socialuni-im-api/src/constant/SocialuniMusicEventConst";

class SocialuniMsgModule {
    queryTime: Date = null
    lazyLoadNum = 30

    async init(params: MessageViewParams) {
        console.log('chufalechushi')
        console.log(params)
        console.log(params.chatId)
        if (params && params.chatId) {
            // if (params.nickname) {
            //   chat.nickname = params.nickname
            // }
            socialuniChatModule.setChatIdAndQueryMsg(params.chatId)

            this.queryTime = new Date()
            await this.queryMessages(true)

            socialuniChatModule.scrollToMessagePageBottom()

            CommonEventUtil.emit(SocialuniImEventKey.socialuniImPageInit, params)
        }
        // socialuniChatModule.chatId = params.receiveId
        // socialuniChatModule.setCurChatByUserId(params.userId)
        // this.userId = params.userId;
        /*else if (params.groupId) {
          // socialuniChatModule.setCurChatByGroupId(params.groupId)
          // this.groupId = params.groupId;
        }*/
    }

    queryMessages(initQuery: boolean = false) {
        // console.trace('chaxun')
        return MessageAPI.queryMessagesAPI(socialuniChatModule.chat.id, this.queryTime).then((res) => {
            const resMessages: MessageVO[] = res.data
            // this.topId = lastFirstMsgId
            // 如果还有大于等于30个就还可以加载
            if (resMessages && resMessages.length >= this.lazyLoadNum) {
                socialuniChatModule.chat.loadMore = LoadMoreType.more
            } else {
                // 否则没有了
                socialuniChatModule.chat.loadMore = LoadMoreType.noMore
            }
            if (resMessages.length) {
                this.queryTime = resMessages[0].createTime
                if (initQuery) {
                    socialuniChatModule.chat.messages = resMessages
                    socialuniChatModule.readChatAction(socialuniChatModule.chat.messages)
                } else {
                    socialuniChatModule.chat.messages.unshift(...resMessages)
                    socialuniChatModule.readChatAction(socialuniChatModule.chat.messages)
                }
            }
        })
    }

}

export const socialuniMsgModule: SocialuniMsgModule = reactive(new SocialuniMsgModule())
