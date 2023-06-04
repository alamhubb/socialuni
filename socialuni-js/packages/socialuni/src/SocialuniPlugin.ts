import NotifyVO from "socialuni-api-base/src/model/NotifyVO";


export interface SocialuniPlugin {
    //应用启动
    onLaunch?();

    //收到websocket消息
    onMessage?(notify: NotifyVO)

    onWebsocketConnect?(reload: boolean)

    onRequestInterceptors(config: InternalAxiosRequestConfig)

    onResponseErrorInterceptors(AxiosResponse)

    onResponseErrorInterceptors(): {
        request: AxiosInterceptorManager<any>;
        response: AxiosInterceptorManager<any>;
    }
}


