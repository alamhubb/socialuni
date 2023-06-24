import {ImportModule} from "socialuni/src/interface/ImportModule";

let nativeUtil: any

async function importPackage() {
    try {
        if (uni) {
            const modulePath = 'socialuni-native-uni/src/util/RouterUtil';
            const res: ImportModule<any> = await import(modulePath)
            nativeUtil = res.default
        }
    } catch (e) {
        const modulePath = 'socialuni-native-h5/src/util/RouterUtil';
        const res: ImportModule<any> = await import(modulePath)
        nativeUtil = res.default
    }
}

importPackage()



export default class SocialuniRouterUtil {
    static get nativeUtil(): any {
        return nativeUtil
    }
}
