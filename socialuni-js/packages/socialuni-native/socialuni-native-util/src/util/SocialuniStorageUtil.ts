import {ImportModule} from "socialuni/src/interface/ImportModule";
import {socialuniSystemModule} from "socialuni-util/src/store/SocialuniSystemModule";

let nativeUtil: any
let modulePath

if (socialuniSystemModule.isUniApp) {
    modulePath = '../../../socialuni-native-uni/src/util/StorageUtil.ts';
} else {
    modulePath = '../../../socialuni-native-h5/src/util/StorageUtil.ts';
}
const res: ImportModule<any> = await import(/* @vite-ignore */ modulePath)

nativeUtil = res.default

export default class SocialuniStorageUtil {
    static get nativeUtil(): any {
        console.log(nativeUtil)
        return nativeUtil
    }
}
