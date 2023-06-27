import PlatformAutoLoadUtil from "./PlatformAutoLoadUtil";

let nativeUtil

const modules = import.meta.globEager('../../../*/src/util/ToastUtil.ts')

nativeUtil = PlatformAutoLoadUtil.getNativeUtil(modules)

export default class SocialuniToastUtil {
  static get nativeUtil(): any {
    return nativeUtil
  }
}
