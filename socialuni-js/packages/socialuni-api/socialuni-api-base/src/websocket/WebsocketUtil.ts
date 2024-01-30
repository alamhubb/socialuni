import FastWebsocket, {FastWebsocketConfig} from "fast-socket/src/FastWebsocket";
import {socialuniPluginsModule} from "socialuni/src/store/SocialuniPluginsModule";
import UUIDUtil from "qing-util/src/util/UUIDUtil";
import SocialuniAPIConfig from "../SocialuniAPIConfig";


export default class WebsocketUtil {
    private static fastWebsocket: FastWebsocket = null

    static createWebsocket() {
        const header = {} as any
        let token: string
        for (const socialuniPlugin of socialuniPluginsModule.plugins) {
            if (socialuniPlugin.onWebsocketInterceptors) {
                socialuniPlugin.onWebsocketInterceptors(header)
            }
        }
        if (header.token) {
            token = header.token
        } else {
            token = UUIDUtil.getUUID()
        }
        const websocketUrl = SocialuniAPIConfig.socialuniWebsocketUrl + '/webSocket/message?token=' + token

        const config: FastWebsocketConfig = {
            wsUrl: websocketUrl,
            onOpen: (reload) => {
                for (const plugin of socialuniPluginsModule.plugins) {
                    plugin.onWebsocketConnected?.(reload)
                }
            },
            onMessage: (res) => {
                console.log('接受了消息')
                const data = JSON.parse(res.data);
                for (const plugin of socialuniPluginsModule.plugins) {
                    plugin.onMessage?.(data)
                }
            }
        }

        this.fastWebsocket = FastWebsocket.createClient(config)
    }

    static send(msg: string) {
        this.fastWebsocket.send(msg)
    }
}
