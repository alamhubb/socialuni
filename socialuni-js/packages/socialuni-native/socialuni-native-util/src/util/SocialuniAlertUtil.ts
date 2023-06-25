let nativeUtil

const modules = import.meta.globEager('../../../*/src/util/AlertUtil.ts')

for (const path in modules) {
    console.log(path)
    const module = modules[path]
    nativeUtil = module.default
}

export default class SocialuniAlertUtil {
    static get nativeUtil(): any {
        return nativeUtil
    }
}
