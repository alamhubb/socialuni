import NotifyVO from "socialuni-api-base/src/model/NotifyVO";
import {AxiosError, InternalAxiosRequestConfig} from "axios";


export interface SocialuniPlugin {
    name?: string

    //应用启动
    onLaunch?();

    onShow?();

    //收到websocket消息
    onMessage?(notify: NotifyVO)

    onWebsocketConnected?(reload: boolean)

    onRequestInterceptors?(config: InternalAxiosRequestConfig)

    onResponseErrorInterceptors?(error: AxiosError)

    onWebsocketInterceptors?(header: any)

}


