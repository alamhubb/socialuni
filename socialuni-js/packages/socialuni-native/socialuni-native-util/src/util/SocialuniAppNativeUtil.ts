let nativeUtil

const modules = import.meta.globEager('../../../*/src/util/NativeUtil.ts')

for (const path in modules) {
    const module = modules[path]
    nativeUtil = module.default
}

export default class SocialuniAppNativeUtil {
    static get nativeUtil(): any {
        return nativeUtil
    }
}
