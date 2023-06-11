import {ImportModule} from "socialuni/src/interface/ImportModule";
import NativeUtil from 'socialuni-native-uni/src/util/NativeUtil'

let nativeUtil: NativeUtil
try {
    if (uni) {
        const res: ImportModule<NativeUtil> = await import('socialuni-native-uni/src/util/NativeUtil')
        nativeUtil = res.default
    }
} catch (e) {

}

export default class SocialuniAppNativeUtil {
    static get nativeUtil(): NativeUtil {
        return nativeUtil
    }
}
