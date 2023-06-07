import SocialuniAPIConfig from "socialuni-api-base/src/SocialuniAPIConfig";
import {socialuniPluginsModule} from "socialuni/src/store/SocialuniPluginsModule";
import UUIDUtil from "socialuni-app/src/util/UUIDUtil";
import CommonUtil from "socialuni-util/src/util/CommonUtil";
import NotifyVO from "../model/NotifyVO";
import JsonUtil from "socialuni-util/src/util/JsonUtil";


export default class WebsocketUtil {
    //失败重连时间
    static failedReconnectTime = 2000
    static timer: number = null
    static locking: boolean = false
    static ws: WebSocket = null

    static reConnect() {
        console.log('触发了重连')
        if (this.locking) return;
        this.locking = true;
        console.log('触发了重连未执行')
        CommonUtil.delayTime(WebsocketUtil.failedReconnectTime).then(() => {
            WebsocketUtil.websocketConnect(true)
        })
    }

    static websocketConnect(reload: boolean) {
        this.locking = true;
        //上锁，防止无限重连，因为会触发close会触发重连
        this.websocketClose()
        this.ws = undefined;

        console.log('websocket连接')

        for (const plugin of socialuniPluginsModule.plugins) {
            plugin.onWebsocketConnect?.(reload)
        }

        let token: string

        const config = {} as any

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

        console.log(this.ws)

        this.ws.onopen = (() => {
            this.locking = false
            console.log('打开')
            if (reload || WebsocketUtil.timer) {
                clearInterval(WebsocketUtil.timer)
            }
            if (reload) {
                console.log('重新加载')
            }
            //心跳保活
            WebsocketUtil.timer = setInterval(() => {
                this.ws.send('ping')
            }, 30000)
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
        this.ws?.close(null);
    }
}
