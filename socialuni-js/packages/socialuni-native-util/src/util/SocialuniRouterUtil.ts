import {ImportModule} from "socialuni/src/interface/ImportModule";
import RouterUtil from "socialuni-native-uni/src/util/RouterUtil";

let nativeUtil: RouterUtil
try {
    if (uni) {
        const res: ImportModule<RouterUtil> = await import('socialuni-native-uni/src/util/RouterUtil')
        nativeUtil = res.default
    }
} catch (e) {

}


export default class SocialuniRouterUtil {
    static get nativeUtil(): RouterUtil {
        return nativeUtil
    }
}
