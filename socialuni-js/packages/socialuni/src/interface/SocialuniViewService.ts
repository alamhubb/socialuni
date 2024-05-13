import {SocialuniViewServiceInterface} from "./SocialuniViewServiceInterface";
import {ComponentInternalInstance} from "vue";
import UUIDUtil from "qing-util/src/util/UUIDUtil";
import {Emit, Component, Vue, Watch, toNative} from 'vue-facing-decorator'
import {socialuniPluginsModule} from "../store/SocialuniPluginsModule";

export default abstract class SocialuniViewService<T> implements SocialuniViewServiceInterface {
    //存储实例，因为初始时还没有$refs
    uuid: ComponentInternalInstance = UUIDUtil.getUUID()
    instance: Vue = null
    params: any = null

    //不要使用getInstance了
    //为什么使用instance而不使用this？
    initService(instance: Vue, params: any = {}) {
        this.instance = instance
        this.params = params
    }

    get $refs(): T {
        return this.instance.refs as T
    }

    get $route(): T {
        return socialuniPluginsModule.route
    }

    get $router(): T {
        return socialuniPluginsModule.router
    }
}
