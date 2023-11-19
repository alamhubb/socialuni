import CommonUtil from "@socialuni/socialuni-util/src/util/CommonUtil";
import NotifyVO from "../model/NotifyVO";
import JsonUtil from "@socialuni/socialuni-util/src/util/JsonUtil";
import UUIDUtil from "@socialuni/socialuni-util/src/util/UUIDUtil";
import SocialuniAPIConfig from "../SocialuniAPIConfig";
import {socialuniSystemModule} from "@socialuni/socialuni-util/src/store/SocialuniSystemModule";


export default class WebsocketWebRtcUtil {
    //失败重连时间
    static failedReconnectTime = 3000
    static pingTime = 10000
    static timer: number = null
    static locking: boolean = false
    static ws: WebSocket = null

    static send(msg: string) {
        this.ws.send(msg)
    }

    static reConnect() {
        console.log('触发了重连未执行')
        if (this.locking) return;
        this.locking = true;
        console.log('触发了重连')
        CommonUtil.delayTime(WebsocketWebRtcUtil.failedReconnectTime).then(() => {
            console.log('触发了延时开启')
            WebsocketWebRtcUtil.websocketConnect(true)
        })
    }

    static websocketConnect(reload: boolean) {
        this.locking = false;
        //上锁，防止无限重连，因为会触发close会触发重连
        this.websocketClose()
        this.ws = undefined;

        console.log('websocket连接')

        const header = {} as any

        let token: string


        if (header.token) {
            token = header.token
        } else {
            token = UUIDUtil.getUUID()
        }

        const websocketUrl = SocialuniAPIConfig.socialuniWebsocketUrl + '/webrtc'


        const onOpen = (() => {
            this.locking = false
            if (reload || WebsocketWebRtcUtil.timer) {
                clearInterval(WebsocketWebRtcUtil.timer)
            }
            if (reload) {
                console.log('重新加载')
            }

            //心跳保活
            WebsocketWebRtcUtil.timer = setInterval(() => {
                if (this.ws.readyState === this.ws.OPEN) {
                    console.log('触发了ping')
                    this.ws.send('ping')
                }
            }, WebsocketWebRtcUtil.pingTime)
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
        const onMessage = ((res: MessageEvent) => {
            const notify: NotifyVO = JsonUtil.toParse(res.data)
            console.log(res)
            console.log(notify)
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

    static websocketClose() {
        if (this.ws && this.ws.readyState === this.ws.OPEN) {
            this.ws?.close(null);
        }
    }
}
