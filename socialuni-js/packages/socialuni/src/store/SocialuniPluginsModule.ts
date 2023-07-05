import {reactive, UnwrapNestedRefs} from "vue";
import {SocialuniPlugin} from "../interface/SocialuniPlugin";
import {RouteLocationPathRaw, Router, RouteRecord} from "vue-router";
import UUIDUtil from "@socialuni/socialuni-util/src/util/UUIDUtil";

class SocialuniPluginsModule {
    private _router: Router = null
    private _route: RouteRecord = null
    uid: string = UUIDUtil.getUUID()
    private socialuniPlugins: SocialuniPlugin[] = []

    constructor() {
        console.trace('创建了socialplus')
    }

    get route(): RouteLocationPathRaw {
        return this._route;
    }

    setRoute(value: RouteRecord) {
        this._route = value;
    }

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
