import FastWebsocket, {FastWebsocketConfig} from "fast-socket/src/FastWebsocket";


export default class WebsocketUtil {
    private static fastWebsocket: FastWebsocket = null

    static createWebsocket(config: FastWebsocketConfig) {
        this.fastWebsocket = new FastWebsocket()
    }

    static send(msg: string) {
        this.fastWebsocket.send(msg)
    }
}
