import {socialuniSystemModule} from "@socialuni/socialuni-util/src/store/SocialuniSystemModule";

export default class PlatformModuleLoadUtil {
    static getModule(modules) {
        for (const path in modules) {
            if (socialuniSystemModule.isUniApp) {
                if (path.includes('-uni')) {
                    const module = modules[path]
                    console.log(path)
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
    static getFirstModule(modules) {
        for (const path in modules) {
            console.log(path)
           return modules[path]
        }
    }
}
