import JsonUtil from "qing-util/src/util/JsonUtil";
import CommonUtil from "qing-util/src/util/CommonUtil";
import {socialuniSystemModule} from "qing-util/src/store/SocialuniSystemModule";

export interface FastWebsocketConfig {
    wsUrl: string,
    onMessage?: ((event: MessageEvent) => void)
    onOpen?: ((reload: boolean) => void)
    // onError?: ((event: MessageEvent) => void)
    // onClose?: ((event: MessageEvent) => void)
}

export default class FastWebsocket {
    static createClient(config: FastWebsocketConfig) {
        const easyWebsocket = new FastWebsocket()
        easyWebsocket.create(config)
        return easyWebsocket
    }

    //失败重连时间
    private failedReconnectTime = 3000
    private pingTime = 10000
    private timer: number = null
    private locking: boolean = false
    private ws: WebSocket = null
    private config: FastWebsocketConfig = null

    create(config: FastWebsocketConfig) {
        this.config = config
        this.websocketConnect(false)
    }

    send(msg: any) {
        this.ws.send(JsonUtil.toJson(msg))
    }

    private reConnect() {
        console.log('触发了重连未执行')
        if (this.locking) return;
        this.locking = true;
        console.log('触发了重连')
        CommonUtil.delayTime(this.failedReconnectTime).then(() => {
            console.log('触发了延时开启')
            this.websocketConnect(true)
        })
    }

    private websocketConnect(reload: boolean) {
        console.log(222222)
        this.locking = false;
        //上锁，防止无限重连，因为会触发close会触发重连
        this.websocketClose()
        this.ws = undefined;

        console.log('websocket连接')

        const websocketUrl = this.config.wsUrl

        const onOpen = (() => {
            this.locking = false
            if (reload || this.timer) {
                clearInterval(this.timer)
            }
            if (reload) {
                console.log('重新加载')
            }

            this.config.onOpen?.(reload)

            //心跳保活
            this.timer = setInterval(() => {
                if (this.ws.readyState === this.ws.OPEN) {
                    this.ws.send('ping')
                }
            }, this.pingTime)
        })


        const onError = (() => {
            console.log('触发了错误')
            // #ifndef MP
            this.reConnect()
            // #endif
        })
        const onClose = ((e) => {
            console.log('触发了关闭:' + JsonUtil.log(e))
            this.reConnect()
        })

        const onMessage = (async (res: MessageEvent) => {
            this.config.onMessage(res)
        })

        if (socialuniSystemModule.isUniApp) {
            this.ws = uni.connectSocket({
                url: websocketUrl,
                complete: () => {
                },
            });
            //@ts-ignore
            this.ws.onClose(onClose);
            //@ts-ignore
            this.ws.onOpen(onOpen);
            //@ts-ignore
            this.ws.onError(onError);
            //@ts-ignore
            this.ws.onMessage(onMessage);
        } else {
            this.ws = new WebSocket(websocketUrl)

            this.ws.onopen = onOpen

            this.ws.onerror = onError

            this.ws.onclose = onClose

            this.ws.onmessage = onMessage
        }

    }

    websocketClose() {
        if (this.ws && this.ws.readyState === this.ws.OPEN) {
            this.ws?.close(null);
        }
    }
}
