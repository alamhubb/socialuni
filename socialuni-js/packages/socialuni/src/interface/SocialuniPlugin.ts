import NotifyVO from "socialuni-api-base/src/model/NotifyVO";
import {AxiosResponse, InternalAxiosRequestConfig} from "axios";


export interface SocialuniPlugin {
    name?: string

    //应用启动
    onLaunch?();

    //收到websocket消息
    onMessage?(notify: NotifyVO)

    onWebsocketConnect?(reload: boolean)

    onRequestInterceptors?(config: InternalAxiosRequestConfig)

    onResponseErrorInterceptors?(error: AxiosResponse)

    onWebsocketInterceptors?(config: any)

}


