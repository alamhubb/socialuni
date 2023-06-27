import PlatformModuleLoadUtil from "./PlatformModuleLoadUtil";

let nativeUtil

const modules = import.meta.globEager('../../../*/src/util/ToastUtil.ts')

nativeUtil = PlatformModuleLoadUtil.getModule(modules)

export default class SocialuniToastUtil {
  static get nativeUtil(): any {
    return nativeUtil
  }
}
