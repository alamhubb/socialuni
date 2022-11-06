import MessageAddVO from "socialuni-api/src/model/message/MessageAddVO";
import request from "socialuni-api/src/request/request";
import openIm from "../../openIm/openIm";

export default class SocialuniMsg {
    static async sendMsgAPI<T>(chatId: string, content: string) {
        const msgAdd: MessageAddVO = new MessageAddVO(chatId, content)


        console.log("login suc...");
        const textStr = "hello open im";
        console.log('发送消息')
        const {data} = await openIm.createTextMessage(content);
        const params = {
            recvID: "f400ccbf06b642a4abf9c589876566ec",
            groupID: "",
            message: data,
        };
        openIm.sendMessage(params).then(res => {
            console.log('发送消息成功')
        });

        return request.post <T>('message/sendMsg', msgAdd)
    }
}
