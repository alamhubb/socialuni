import {reactive, UnwrapNestedRefs} from "vue";
import {SocialuniPlugin} from "../interface/SocialuniPlugin";
import {Router} from "vue-router";

class SocialuniPluginsModule {
    private _router: Router = null
    private socialuniPlugins: SocialuniPlugin[] = []


    get router(): Router {
        return this._router;
    }

    setRouter(value: Router) {
        this._router = value;
    }

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
