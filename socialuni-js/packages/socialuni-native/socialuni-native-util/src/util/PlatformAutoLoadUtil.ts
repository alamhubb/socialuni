import {socialuniSystemModule} from "socialuni-util/src/store/SocialuniSystemModule";

export default class PlatformAutoLoadUtil {
    static getNativeUtil(modules) {
        console.log('isuniap:' + socialuniSystemModule.isUniApp)
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
}
