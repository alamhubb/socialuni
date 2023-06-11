import ToastUtil from "socialuni-native-uni/src/util/ToastUtil";
import {ImportModule} from "socialuni/src/interface/ImportModule";

let nativeUtil: ToastUtil
try {
    if (uni) {
        const res: ImportModule<ToastUtil> = await import('socialuni-native-uni/src/util/ToastUtil')
        nativeUtil = res.default
    }
} catch (e) {

}


export default class SocialuniToastUtil {
    get nativeUtil(): ToastUtil {
        return nativeUtil
    }
}
