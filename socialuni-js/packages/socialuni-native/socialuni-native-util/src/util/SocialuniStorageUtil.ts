import {ImportModule} from "socialuni/src/interface/ImportModule";
import {socialuniSystemModule} from "socialuni-util/src/store/SocialuniSystemModule";
import StorageUtil from "socialuni-native-h5/src/util/StorageUtil";

let nativeUtil: StorageUtil
let modulePath
if (socialuniSystemModule.isUniApp) {
    modulePath = 'socialuni-native-uni/src/util/StorageUtil';
} else {
    modulePath = 'socialuni-native-h5/src/util/StorageUtil';
}
const res: ImportModule<StorageUtil> = await import(modulePath)

nativeUtil = res.default
/*
async function importPackage() {
    let modulePath
    if (socialuniSystemModule.isUniApp) {
        modulePath = 'socialuni-native-uni/src/util/StorageUtil';
    } else {
        modulePath = 'socialuni-native-h5/src/util/StorageUtil.ts';
    }
    console.log(modulePath)
    const res: ImportModule<any> = await import(modulePath)
    console.log(res)
    nativeUtil = res.default
    console.log(res.default)
}

await importPackage()*/


export default class SocialuniStorageUtil {
    static get nativeUtil(): any {
        console.log(nativeUtil)
        return nativeUtil
    }
}
