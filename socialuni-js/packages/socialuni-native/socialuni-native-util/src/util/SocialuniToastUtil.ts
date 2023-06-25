let nativeUtil

const modules = import.meta.globEager('../../../*/src/util/ToastUtil.ts')

for (const path in modules) {
  const module = modules[path]
  nativeUtil = module.default
}

export default class SocialuniToastUtil {
  static get nativeUtil(): any {
    return nativeUtil
  }
}
