import {reactive, UnwrapNestedRefs} from "vue";
import {SocialuniPlugin} from "../interface/SocialuniPlugin";
import HttpRequestConfig from '../request/HttpRequestConfig'
import SocialuniHttpRequestConfig from '../request/SocialuniHttpRequestConfig'

class SocialuniPluginsModule {
    private socialuniPlugins: SocialuniPlugin[] = []
    requestConfig: HttpRequestConfig = new SocialuniHttpRequestConfig()

    init() {
        this.socialuniPlugins = []
    }

    addPlugin(...socialuniPlugins: SocialuniPlugin[]) {
        this.socialuniPlugins.push(...socialuniPlugins)
    }

    get plugins() {
        return this.socialuniPlugins
    }
}

export const socialuniPluginsModule: UnwrapNestedRefs<SocialuniPluginsModule> = reactive(new SocialuniPluginsModule())
