import {SocialuniViewServiceInterface} from "./SocialuniViewServiceInterface";
import {ComponentInternalInstance} from "@vue/runtime-core";

export default abstract class SocialuniViewService<T> implements SocialuniViewServiceInterface {
    //存储实例，因为初始时还没有$refs
    instance: ComponentInternalInstance = null

    initService(instance: ComponentInternalInstance) {
        this.instance = instance
    }

    get $refs(): T {
        return this.instance.refs as T
    }
}
