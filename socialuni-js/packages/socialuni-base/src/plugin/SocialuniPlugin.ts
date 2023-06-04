import NotifyVO from "socialuni-api-base/src/model/NotifyVO";

export interface AxiosInterceptorManager<V> {
    use(onFulfilled?: ((value: V) => V | Promise<V>) | null, onRejected?: ((error: any) => any) | null, options?: any): number;

    eject(id: number): void;

    clear(): void;
}


export interface SocialuniPlugin {
    //应用启动
    onLaunch?();

    //收到websocket消息
    onMessage?(notify: NotifyVO)

    onWebsocketConnect?(reload: boolean)

    onRequestInterceptors(): {
        request: AxiosInterceptorManager<any>;
        response: AxiosInterceptorManager<any>;
    }
}


