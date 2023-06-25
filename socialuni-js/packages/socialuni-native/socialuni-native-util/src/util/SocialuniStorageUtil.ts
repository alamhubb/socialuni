let nativeUtil

const modules = import.meta.globEager('../../../*/src/util/StorageUtil.ts')

for (const path in modules) {
    const module = modules[path]
    nativeUtil = module.default
}

export default class SocialuniStorageUtil {
    static get nativeUtil(): any {
        console.log(nativeUtil)
        return nativeUtil
    }
}
