import ToastUtil from "packages/socialuni-native/socialuni-native-uni/src/util/ToastUtil"
import { ImportModule } from "socialuni/src/interface/ImportModule"

let nativeUtil: any

async function importPackage() {
  try {
    if (uni) {
      const modulePath = 'socialuni-native-uni/src/util/ToastUtil';
      const res: ImportModule<any> = await import(modulePath)
      nativeUtil = res.default
    }
  } catch (e) {
    const modulePath = 'socialuni-native-h5/src/util/ToastUtil';
    const res: ImportModule<any> = await import(modulePath)
    nativeUtil = res.default
  }
}

importPackage()


export default class SocialuniToastUtil {
  static get nativeUtil(): ToastUtil {
    return nativeUtil
  }
}
