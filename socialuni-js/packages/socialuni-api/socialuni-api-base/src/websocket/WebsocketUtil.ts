import {socialuniPluginsModule} from "@socialuni/socialuni/src/store/SocialuniPluginsModule";
import CommonUtil from "@socialuni/socialuni-util/src/util/CommonUtil";
import NotifyVO from "../model/NotifyVO";
import JsonUtil from "@socialuni/socialuni-util/src/util/JsonUtil";
import UUIDUtil from "@socialuni/socialuni-util/src/util/UUIDUtil";
import SocialuniAPIConfig from "../SocialuniAPIConfig";


export default class WebsocketUtil {
    //失败重连时间
    static failedReconnectTime = 3000
    static pingTime = 10000
    static timer: number = null
    static locking: boolean = false
    static ws: WebSocket = null

    static reConnect() {
        console.log('触发了重连未执行')
        if (this.locking) return;
        this.locking = true;
        console.log('触发了重连')
        CommonUtil.delayTime(WebsocketUtil.failedReconnectTime).then(() => {
            console.log('触发了延时开启')
            WebsocketUtil.websocketConnect(true)
        })
    }

    static websocketConnect(reload: boolean) {
        this.locking = false;
        //上锁，防止无限重连，因为会触发close会触发重连
        this.websocketClose()
        this.ws = undefined;

        console.log('websocket连接')

        const config = {} as any

        let token: string

        for (const socialuniPlugin of socialuniPluginsModule.plugins) {
            if (socialuniPlugin.onWebsocketInterceptors) {
                socialuniPlugin.onWebsocketInterceptors(config)
            }
        }

        if (config.token) {
            token = config.token
        } else {
            token = UUIDUtil.getUUID()
        }

        const websocketUrl = SocialuniAPIConfig.socialuniWebsocketUrl + '/webSocket/message?token=' + token

        this.ws = new WebSocket(websocketUrl)


        this.ws.onopen = (() => {
            this.locking = false
            if (reload || WebsocketUtil.timer) {
                clearInterval(WebsocketUtil.timer)
            }
            if (reload) {
                console.log('重新加载')
            }
            for (const plugin of socialuniPluginsModule.plugins) {
                plugin.onWebsocketConnected?.(reload)
            }
            //心跳保活
            WebsocketUtil.timer = setInterval(() => {
                if (this.ws.readyState === this.ws.OPEN) {
                    this.ws.send('ping')
                }
            }, WebsocketUtil.pingTime)
        })

        this.ws.onerror = (() => {
            console.log('触发了错误')
            this.reConnect()
        })

        this.ws.onclose = ((e) => {
            console.log('触发了关闭:' + e)
            this.reConnect()
        })

        this.ws.onmessage = ((res: MessageEvent) => {
            const notify: NotifyVO = JsonUtil.toParse(res.data)
            console.log(notify)
            for (const plugin of socialuniPluginsModule.plugins) {
                plugin.onMessage?.(notify)
            }
        })
    }

    static websocketClose() {
        if (this.ws && this.ws.readyState === this.ws.OPEN) {
            this.ws?.close(null);
        }
    }
}
