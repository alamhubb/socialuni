import {ImportModule} from "socialuni/src/interface/ImportModule";
import StorageUtil from "socialuni-native-uni/src/util/StorageUtil";

let nativeUtil: StorageUtil
try {
    if (uni) {
        const res: ImportModule<StorageUtil> = await import('socialuni-native-uni/src/util/StorageUtil')
        nativeUtil = res.default
    }
} catch (e) {

}


export default class SocialuniStorageUtil {
    static get nativeUtil(): StorageUtil {
        return nativeUtil
    }
}
