import {ImportModule} from "socialuni/src/interface/ImportModule";
import NativeUtil from "socialuni-native-uni/src/util/NativeUtil";

let nativeUtil: NativeUtil

async function importPackage() {
    try {
        if (uni) {
            const modulePath = 'socialuni-native-uni/src/util/NativeUtil';
            const res: ImportModule<any> = await import(modulePath)
            nativeUtil = res.default
        }
    } catch (e) {
        const modulePath = 'socialuni-native-h5/src/util/NativeUtil';
        const res: ImportModule<any> = await import(modulePath)
        nativeUtil = res.default
    }
}

importPackage()


export default class SocialuniAppNativeUtil {
    static get nativeUtil(): NativeUtil {
        return nativeUtil
    }
}
