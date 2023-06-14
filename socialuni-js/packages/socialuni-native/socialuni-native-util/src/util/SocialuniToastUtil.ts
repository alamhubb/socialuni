import ToastUtil from "packages/socialuni-native/socialuni-native-uni/src/util/ToastUtil"
import { ImportModule } from "socialuni/src/interface/ImportModule"

let nativeUtil: ToastUtil
try {
  if (uni) {
    const res: ImportModule<ToastUtil> = await import('socialuni-native-uni/src/util/ToastUtil')
    nativeUtil = res.default
  }
} catch (e) {

}


export default class SocialuniToastUtil {
  static get nativeUtil(): ToastUtil {
    return nativeUtil
  }
}
