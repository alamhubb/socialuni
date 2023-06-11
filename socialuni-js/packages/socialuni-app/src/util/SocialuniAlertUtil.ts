import {ImportModule} from "socialuni/src/interface/ImportModule";
import AlertUtil from "socialuni-native-uni/src/util/AlertUtil";

let nativeUtil: AlertUtil
try {
    if (uni) {
        const res: ImportModule<AlertUtil> = await import('socialuni-native-uni/src/util/AlertUtil')
        nativeUtil = res.default
    }
} catch (e) {

}

export default class SocialuniAlertUtil {
    static get nativeUtil(): AlertUtil {
        return nativeUtil
    }
}
