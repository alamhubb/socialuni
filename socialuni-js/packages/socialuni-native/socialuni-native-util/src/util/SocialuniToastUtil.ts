import { ImportModule } from "socialuni/src/interface/ImportModule"
import {socialuniSystemModule} from "socialuni-util/src/store/SocialuniSystemModule";

let nativeUtil: any = {get(){return {}},getObj(){return {}}}
/*let modulePath

if (socialuniSystemModule.isUniApp) {
  modulePath = '../../../socialuni-native-uni/src/util/ToastUtil.ts';
} else {
  modulePath = '../../../socialuni-native-h5/src/util/ToastUtil.ts';
}
const res: ImportModule<any> = await import(/!* @vite-ignore *!/ modulePath)

nativeUtil = res.default*/


export default class SocialuniToastUtil {
  static get nativeUtil(): any {
    return nativeUtil
  }
}
