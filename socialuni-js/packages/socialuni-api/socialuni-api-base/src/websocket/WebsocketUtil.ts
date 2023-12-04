import FastWebsocket, {FastWebsocketConfig} from "fast-socket/src/FastWebsocket";
import {socialuniPluginsModule} from "socialuni/src/store/SocialuniPluginsModule";
import {socialuniTokenModule} from "socialuni-user-sdk/src/store/SocialuniTokenModule";
import UUIDUtil from "socialuni-util/src/util/UUIDUtil";
import SocialuniAPIConfig from "../SocialuniAPIConfig";
import WebsocketWebRtcUtil from "./WebsocketWebRtcUtil";
import FastWebRTC from "fast-webrtc/src/FastWebRTC";


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
