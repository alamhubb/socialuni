import SocialuniAppUtil from "socialuni-native-util/src/util/SocialuniAppUtil";
import UserCheckUtil from "socialuni-user/src/util/UserCheckUtil";
import MessageVO from "socialuni-im-api/src/model/RO/MessageVO";
import {socialuniUserModule} from "socialuni-user/src/store/SocialuniUserModule";
import MessageAPI from "socialuni-im-api/src/api/MessageAPI";
import {MessageStatus} from "socialuni-constant/constant/openIm/OpenImMessageType";
import {socialuniChatModule} from "../store/SocialuniChatModule";

export default class SocialuniMsgViewService {
    msgContent = ''

    async sendMsgClick() {
        console.log(123456)
        if (!socialuniChatModule.chat) {
            SocialuniAppUtil.ToastUtil.throwError('缺少会话')
        }
        console.log(123456)
        UserCheckUtil.checkUserBindPhoneNum()
        console.log(123)
        const msgContent = this.msgContent
        console.log(this.msgContent)
        console.log(msgContent)
        console.log(123456)
        if (msgContent) {
            console.log(123456)
            console.log(123)
            const newMsg = new MessageVO(msgContent, socialuniUserModule.mineUser)
            socialuniChatModule.messages.push(newMsg)
            console.log(123)
            socialuniChatModule.scrollToMessagePageBottom()
            const index: number = socialuniChatModule.messages.length - 1
            // 点击发送后立即push
            console.log(123)
            //启用状态可以直接发送
            this.msgContent = ''
            socialuniChatModule.chat.updateTime = new Date()
            console.log(123)
            try {
                const res = await MessageAPI.sendMsgAPI(socialuniChatModule.chat.id, msgContent)
                socialuniChatModule.chat.updateTime = res.data.createTime
                socialuniChatModule.messages.splice(index, 1, res.data)
            } catch (e) {
                newMsg.readStatus = MessageStatus.Failed
            }
        } else {
            SocialuniAppUtil.ToastUtil.throwError('不能发送空白内容')
        }
        // socialuniChatModule.refreshMessages()

        // PlatformUtils.requestSubscribeChat()
    }
}
