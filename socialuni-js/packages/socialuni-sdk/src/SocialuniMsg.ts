import SocialuniChatRO from "socialuni-api/src/model/SocialuniChatRO";
import {socialChatModule} from "./store/store";

export default class SocialuniMsg {
    static async sendMsgAPI<T>(chat: SocialuniChatRO, content: string) {
        const {data} = await socialChatModule.openIm.createTextMessage(content);
        const params = {
            recvID: chat.receiveUserId,
            groupID: "",
            message: data,
        };
        // const msgAdd: MessageAddVO = new MessageAddVO(chatId, content)
        // return request.post <T>('message/sendMsg', msgAdd)
        return socialChatModule.openIm.sendMessage(params)
    }
}
