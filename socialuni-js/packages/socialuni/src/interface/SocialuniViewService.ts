import {SocialuniViewServiceInterface} from "./SocialuniViewServiceInterface";
import {ComponentInternalInstance} from "vue";
import UUIDUtil from "qing-util/src/util/UUIDUtil";

export default abstract class SocialuniViewService<T> implements SocialuniViewServiceInterface {
    //存储实例，因为初始时还没有$refs
    uuid: ComponentInternalInstance = UUIDUtil.getUUID()
    instance: ComponentInternalInstance = null
    params: any = null

    initService(instance: ComponentInternalInstance, params: any = {}) {
        this.instance = instance
        this.params = params
    }

    get $refs(): T {
        return this.instance.refs as T
    }
}
