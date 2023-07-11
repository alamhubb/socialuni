import {socialuniSystemModule} from "@socialuni/socialuni-util/src/store/SocialuniSystemModule";
import {ImportModule} from "@socialuni/socialuni/src/interface/ImportModule";

export default class PlatformModuleLoadUtil {
    static getModuleDefault(modules) {
        for (const path in modules) {
            if (socialuniSystemModule.isUniApp) {
                if (path.includes('-uni')) {
                    const module = modules[path]
                    return module.default
                }
            } else {
                if (path.includes('-h5')) {
                    const module = modules[path]
                    return module.default
                }
            }
        }
    }

    static async dynamicImport(prefix: string, suffix = '/src/index.ts'):Promise<ImportModule<any>> {

    }

    static getFirstModule(modules) {
        for (const path in modules) {
            return modules[path]
        }
    }
}
