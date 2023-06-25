import {SocialuniAlertUtilInterface} from "../interface/SocialuniAlertUtilInterface";

let nativeUtil: SocialuniAlertUtilInterface

const modules = import.meta.globEager('../../../*/src/util/AlertUtil.ts')

for (const path in modules) {
    const module = modules[path]
    nativeUtil = module.default
}

export default class SocialuniAlertUtil {
    static get nativeUtil(): SocialuniAlertUtilInterface {
        return nativeUtil
    }
}
