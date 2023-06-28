import PlatformModuleLoadUtil from "./PlatformModuleLoadUtil";
import {SocialuniToastUtilInterface} from "../interface/SocialuniToastUtilInterface";

let nativeUtil

const modules = import.meta.globEager('../../../*/src/util/ToastUtil.ts')

nativeUtil = PlatformModuleLoadUtil.getModule(modules)

export default class SocialuniToastUtil {
  static get nativeUtil(): SocialuniToastUtilInterface {
    return nativeUtil
  }
}
