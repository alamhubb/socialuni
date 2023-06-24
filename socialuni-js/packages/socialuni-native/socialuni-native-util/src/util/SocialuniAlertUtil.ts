import {ImportModule} from "socialuni/src/interface/ImportModule";
import AlertUtil from "packages/socialuni-native/socialuni-native-uni/src/util/AlertUtil";

let nativeUtil: AlertUtil

async function importPackage() {
    try {
        if (uni) {
            const modulePath = 'socialuni-native-uni/src/util/AlertUtil';
            const res: ImportModule<any> = await import(modulePath)
            nativeUtil = res.default
        }
    } catch (e) {
        const modulePath = 'socialuni-native-h5/src/util/AlertUtil';
        const res: ImportModule<any> = await import(modulePath)
        nativeUtil = res.default
    }
}

importPackage()

export default class SocialuniAlertUtil {
    static get nativeUtil(): AlertUtil {
        return nativeUtil
    }
}
