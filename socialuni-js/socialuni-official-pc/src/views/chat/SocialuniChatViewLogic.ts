import {socialuniChatModule} from "socialuni-im/src/store/SocialuniChatModule";
import SocialuniChatRO from "socialuni-api-base/src/model/SocialuniChatRO";
import UserCheckUtil from "socialuni-user/src/util/UserCheckUtil";
import CommonUtil from "socialuni-util/src/util/CommonUtil";
import HintMsg from "socialuni-constant/constant/HintMsg";
import MessageVO from "socialuni-im-api/src/model/RO/MessageVO";
import {socialChatModule} from "socialuni-im/src/store/SocialChatModule";
import ToastUtil from "socialuni-util/src/util/ToastUtil";
import MessageAddVO from "socialuni-im-api/src/model/QO/message/MessageAddVO";
import MessageAPI from "socialuni-im-api/src/api/MessageAPI";
import {useRoute, useRouter} from "vue-router";
import DateUtil from "socialuni-util/src/util/DateUtil";
import {socialuniUserModule} from "socialuni-user/src/store/SocialuniUserModule";
import {MessageStatus} from "socialuni-constant/constant/openIm/OpenImMessageType";
import YScrollbar from "@/components/YScrollbar.vue";
import {watch} from "vue";
import SocialuniLoginService from "@/logic/service/SocialuniLoginService";
import WebsocketUtil from "socialuni-api-base/src/websocket/WebsocketUtil";
import AlertUtil from "socialuni-util/src/util/AlertUtil";
import {socialuniConfigModule} from "socialuni-app/src/store/SocialuniConfigModule";
import mitt from "mitt";
import SocialuniUserEventConst from "socialuni-user/src/constant/SocialuniUserEventConst";

export interface SocialuniChatViewLogicRefs {
    messageBox: YScrollbar
}

export default class SocialuniChatViewLogic {
    private $refs: SocialuniChatViewLogicRefs = null

    init(value: SocialuniChatViewLogicRefs) {
        this.$refs = value;
        watch(() => socialuniChatModule.scrollTop, () => {
            console.log('触发了滚动')
            this.$refs.messageBox.$el.scrollTop = socialuniChatModule.scrollTop
            // this.scrollTop = -1000
        })
    }

    msgContent = ''
    scrollTop: number = 0

    formatTime(time: any) {
        return DateUtil.formatTime(time)
    }


    get chat(): SocialuniChatRO {
        if (socialuniChatModule.chat) {
            console.log(socialuniChatModule.chat.messages)
            console.log(socialuniChatModule.chat.messages.length)

            return socialuniChatModule.chat
        }
        return null
    }

    get chatList() {
        return socialuniChatModule.chats
    }


    setChatId(chat: SocialuniChatRO) {
        socialuniChatModule.setChatId(chat.id)
    }


    async sendMsgClick() {
        console.log(this.$refs.messageBox)
        console.log(this.$refs.messageBox.$el.scrollTop)
        if (!socialuniChatModule.chat) {
            ToastUtil.throwError('缺少会话')
        }
        console.log(123123)
        UserCheckUtil.checkUserBindPhoneNum()
        console.log(456456)
        const msgContent = this.msgContent
        // || (this.chat.needPayOpen ? HintMsg.payOpenDefaultMsg : '')
        console.log(this.msgContent)

        if (msgContent) {
            const newMsg = new MessageVO(this.msgContent, socialuniUserModule.mineUser)
            socialuniChatModule.messages.push(newMsg)
            socialuniChatModule.scrollToMessagePageBottom()
            const index: number = socialuniChatModule.messages.length - 1
            // 点击发送后立即push
            //启用状态可以直接发送
            this.msgContent = ''
            this.chat.updateTime = new Date().getTime()
            try {
                const res = await MessageAPI.sendMsgAPI(this.chat.id, msgContent)
                this.chat.updateTime = res.data.createTime
                socialuniChatModule.messages.splice(index, 1, res.data)
            } catch (e) {
                newMsg.readStatus = MessageStatus.Failed
            }
        } else {
            ToastUtil.throwError('不能发送空白内容')
        }
    }
}
