import {ImportModule} from "socialuni/src/interface/ImportModule";
import {socialuniSystemModule} from "socialuni-util/src/store/SocialuniSystemModule";

let nativeUtil: any = {get(){return {}},getObj(){return {}}}
/*let modulePath

if (socialuniSystemModule.isUniApp) {
    modulePath = '../../../socialuni-native-uni/src/util/RouterUtil.ts';
} else {
    modulePath = '../../../socialuni-native-h5/src/util/RouterUtil.ts';
}
const res: ImportModule<any> = await import(/!* @vite-ignore *!/ modulePath)

nativeUtil = res.default*/


export default class SocialuniRouterUtil {
    static get nativeUtil(): any {
        return nativeUtil
    }
}
