import {socialuniChatModule} from "socialuni-im/src/store/SocialuniChatModule";
import SocialuniChatRO from "socialuni-api-base/src/model/SocialuniChatRO";
import UserCheckUtil from "socialuni-user/src/util/UserCheckUtil";
import MessageVO from "socialuni-im-api/src/model/RO/MessageVO";
import MessageAPI from "socialuni-im-api/src/api/MessageAPI";
import DateUtil from "socialuni-util/src/util/DateUtil";
import {socialuniUserModule} from "socialuni-user/src/store/SocialuniUserModule";
import {MessageStatus} from "socialuni-constant/constant/openIm/OpenImMessageType";
import {watch} from "vue";
import SocialuniAppUtil from "socialuni-native-util/src/util/SocialuniAppUtil";
import SocialuniViewService from "socialuni/src/interface/SocialuniViewService";

export interface SocialuniChatViewServiceRefs {
    messageBox:HTMLDivElement
}

export default class SocialuniChatViewService extends SocialuniViewService{
    private $refs: SocialuniChatViewServiceRefs = null

    init(value: SocialuniChatViewServiceRefs) {
        this.$refs = value;
        watch(() => socialuniChatModule.scrollTop, () => {
            console.log('触发了滚动')
            console.log(this.$refs.messageBox)
            this.$refs.messageBox.scrollTop = socialuniChatModule.scrollTop
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
        if (!socialuniChatModule.chat) {
            SocialuniAppUtil.ToastUtil.throwError('缺少会话')
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
            SocialuniAppUtil.ToastUtil.throwError('不能发送空白内容')
        }
    }
}
