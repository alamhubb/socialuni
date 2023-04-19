import CommonUtil from "socialuni-util/src/util/CommonUtil";
import JsonUtil from "../../../socialuni-base-api/src/util/JsonUtil";
import {socialuniUserModule} from "socialuni/src/store/SocialuniUserModule";
import UUIDUtil from "socialuni-util/src/util/UUIDUtil";
import {socialuniConfigModule} from "socialuni-base-api/src/store/SocialuniConfigModule";
import NotifyVO from "socialuni-base-api/src/model/NotifyVO";
import SocialuniConfig from "socialuni-base-api/src/config/SocialuniConfigModule";
import {socialuniPluginsModule} from "../store/SocialuniPluginsModule";
import {socialChatModule} from "socialuni-im/src/store/SocialChatModule";

export default class WebsocketUtil {

    //失败重连时间
    static failedReconnectTime = 2000
    static timer: number = null
    static locking: boolean = false

    static websocketConnect(reload: boolean) {
        console.log('websocket连接')
        let token: string

        if (socialuniUserModule.hasToken) {
            token = socialuniUserModule.token
        } else {
            token = UUIDUtil.getUUID()
        }
        console.log(token)
        console.log(SocialuniConfig.socialuniWebsocketUrl + '/webSocket/message?token=' + token)


        if (!this.locking) {
            this.locking = true
            uni.connectSocket({
                //因为app不支持header中传参
                // url: AppConfig.websocketUrl + 'imserver/' + token,
                url: SocialuniConfig.socialuniWebsocketUrl + '/webSocket/message?token=' + token,
                /* url: CommonUtil.websocketUrl + 'webSocket/message',
                header: {
                  token: token
                }, */
                complete: () => {
                    this.locking = false
                    console.log('完成')
                }
            })
        }

        uni.onSocketOpen(() => {
            console.log('打开')

            if (reload || WebsocketUtil.timer) {
                clearInterval(WebsocketUtil.timer)
            }

            if (reload) {
                console.log('重新加载')
                socialChatModule.getChatsAction()
            }
            //心跳保活
            WebsocketUtil.timer = setInterval(() => {
                uni.sendSocketMessage({
                    data: 'ping',
                    success: res => {
                        console.log('保活成功')
                    },
                    fail: err => {
                        console.log('连接失败重新连接....:' + err)
                        WebsocketUtil.websocketConnect(true)
                    },
                    complete() {

                    }
                })
            }, 30000)
        })

        uni.onSocketError(() => {
            console.log('触发了错误')
            // #ifndef MP
            CommonUtil.delayTime(WebsocketUtil.failedReconnectTime).then(() => {
                WebsocketUtil.websocketConnect(true)
            })
            // #endif
        })

        uni.onSocketClose((e) => {
            console.log('触发了关闭:' + e)
            CommonUtil.delayTime(WebsocketUtil.failedReconnectTime).then(() => {
                WebsocketUtil.websocketConnect(true)
            })
        })

        uni.onSocketMessage((res: any) => {
            const notify: NotifyVO = JsonUtil.toParse(res.data)
            for (const plugin of socialuniPluginsModule.plugins) {
                plugin.onMessage?.(notify)
            }
        })
    }

    static websocketClose() {
        uni.closeSocket()
    }
}
