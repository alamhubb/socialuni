let nativeUtil

const modules = import.meta.globEager('../../../*/src/util/RouterUtil.ts')

for (const path in modules) {
    const module = modules[path]
    nativeUtil = module.default
}


export default class SocialuniRouterUtil {
    static get nativeUtil(): any {
        return nativeUtil
    }
}
