import SocialuniAPIConfig from "socialuni-api-base/src/SocialuniAPIConfig";


export default class WebsocketUtil {
    //失败重连时间
    static failedReconnectTime = 2000
    static timer: number = null
    static locking: boolean = false
    static ws: SocketTask = null

    static reConnect() {
        if (this.locking) return;
        this.locking = true;
        CommonUtil.delayTime(WebsocketUtil.failedReconnectTime).then(() => {
            WebsocketUtil.websocketConnect(true)
        })
    }

    static websocketConnect(reload: boolean) {
        this.locking = true;
        //上锁，防止无限重连，因为会触发close会触发重连
        this.ws?.close(null);
        this.ws = undefined;

        console.log('websocket连接')

        for (const plugin of socialuniPluginsModule.plugins) {
            plugin.onWebsocketConnect?.(reload)
        }

        let token: string

        if (socialuniUserModule.hasToken) {
            token = socialuniUserModule.token
        } else {
            token = UUIDUtil.getUUID()
        }
        console.log(token)
        console.log(SocialuniAPIConfig.socialuniWebsocketUrl + '/webSocket/message?token=' + token)


        this.ws = uni.connectSocket({
            //因为app不支持header中传参
            // url: AppConfig.websocketUrl + 'imserver/' + token,
            url: SocialuniConfig.socialuniWebsocketUrl + '/webSocket/message?token=' + token,
            /* url: CommonUtil.websocketUrl + 'webSocket/message',
            header: {
              token: token
            }, */
            complete: () => {
                console.log('完成')
                this.locking = false
            }
        })

        console.log(this.ws)

        this.ws.onOpen(() => {
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

        this.ws.onError(() => {
            console.log('触发了错误')
            // #ifndef MP
            this.reConnect()
            // #endif
        })

        this.ws.onClose((e) => {
            console.log('触发了关闭:' + e)
            this.reConnect()
        })

        this.ws.onMessage((res: any) => {
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
