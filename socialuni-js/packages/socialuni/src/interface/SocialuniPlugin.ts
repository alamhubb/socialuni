import NotifyVO from "socialuni-base-api/src/model/NotifyVO";

export interface SocialuniPlugin{
    //应用启动
    onLaunch?();

    //收到websocket消息
    onMessage?(notify: NotifyVO)
}
