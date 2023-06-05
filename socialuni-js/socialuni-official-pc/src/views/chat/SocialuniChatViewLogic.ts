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
import {useRouter} from "vue-router";
import DateUtil from "socialuni-util/src/util/DateUtil";

export default class SocialuniChatViewLogic {
    msgContent = ''

    formatTime(time: any) {
        return DateUtil.formatTime(time)
    }


    get chat() {
        return socialuniChatModule.chat
    }

    get chatList() {
        return socialuniChatModule.chats
    }


    setChatId(chat: SocialuniChatRO) {
        socialuniChatModule.setChatId(chat.id)
    }


    async sendMsgClick() {
        UserCheckUtil.checkUserBindPhoneNum()
        const msgContent = this.msgContent || (this.chat.needPayOpen ? HintMsg.payOpenDefaultMsg : '')
        console.log(this.msgContent)
        if (msgContent) {
            // 点击发送后立即push
            //启用状态可以直接发送
            this.msgContent = ''
            // console.log(index)
            this.chat.updateTime = new Date().getTime()
            // this.chat.lastContent = msg.content
            // 滚屏到最后面
            // 不能监控变化滚动，有时候是往前面插入
            /*const {data} = await (await socialChatModule.openIm())[msg.action](msg.contentData);
            const params = {
                recvID: this.chat.receiveUserId,
                groupID: this.chat.groupId,
                message: data,
            };*/
            // console.log('-------params-------', params);

            return MessageAPI.sendMsgAPI(this.chat.id, msgContent)
            /*if (this.chat.status === SocialuniCommonStatus.enable) {


            } */
            /*else {
              this.openChatPromise(msgContent).finally(() => {
                this.isOpeningChatDisableBtn = false
              })
            }*/
        } else {
            ToastUtil.toast('不能发送空白内容')
        }
    }
}
