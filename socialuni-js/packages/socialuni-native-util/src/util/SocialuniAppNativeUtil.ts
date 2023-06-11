import {ImportModule} from "socialuni/src/interface/ImportModule";
import UniUtil from "./UniUtil";

let nativeUtil: UniUtil
try {
    if (uni) {
        const res: ImportModule<UniUtil> = await import('socialuni-native-uni/src/util/UniUtil')
        nativeUtil = res.default
    }
} catch (e) {

}

export default class SocialuniAppNativeUtil {
    static get nativeUtil(): UniUtil {
        return nativeUtil
    }
}
