import {reactive, UnwrapNestedRefs} from "vue";
import {SocialuniPlugin} from "../interface/SocialuniPlugin";

class SocialuniPluginsModule {
    private socialuniPlugins: SocialuniPlugin[] = []

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
