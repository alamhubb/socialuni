import {SocialuniViewServiceInterface} from "./SocialuniViewServiceInterface";
import {ComponentInternalInstance} from "vue";

export default abstract class SocialuniViewService<T> implements SocialuniViewServiceInterface {
    //存储实例，因为初始时还没有$refs
    instance: ComponentInternalInstance = null
    params: any = null

    initService(instance: ComponentInternalInstance, params: any = {}) {
        this.instance = instance
        this.params = params
    }

    get $refs(): T {
        console.log(this.instance)
        return this.instance.refs as T
    }
}
