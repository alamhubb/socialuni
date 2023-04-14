import SocialuniChatRO from "socialuni-base-api/src/model/SocialuniChatRO";
import {socialChatModule} from "./store/store";

export default class SocialuniMsg {
    static async sendMsgAPI<T>(chat: SocialuniChatRO, content: string) {
        const {data} = await (await socialChatModule.openIm()).createTextMessage(content);
        const params = {
            recvID: chat.receiveUserId,
            groupID: "",
            message: data,
        };
        console.log(params)
        // const msgAdd: MessageAddVO = new MessageAddVO(chatId, content)
        // return request.post <T>('message/sendMsg', msgAdd)
        return await (await socialChatModule.openIm()).sendMessage(params)
    }
}
