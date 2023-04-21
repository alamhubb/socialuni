import {reactive, UnwrapNestedRefs} from "vue";
import {SocialuniPlugin} from "../interface/SocialuniPlugin";
import {HttpRequestConfigInterface} from "../request/HttpRequestConfigInterface";
import SocialuniHttpRequestConfig from "../request/SocialuniHttpRequestConfig";

class SocialuniPluginsModule {
    private socialuniPlugins: SocialuniPlugin[] = []
    requestConfig: HttpRequestConfigInterface = new SocialuniHttpRequestConfig()

    init(requestConfig: HttpRequestConfigInterface = new SocialuniHttpRequestConfig()) {
        this.requestConfig = requestConfig
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
