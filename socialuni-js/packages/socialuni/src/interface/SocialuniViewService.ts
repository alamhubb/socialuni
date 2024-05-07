import {SocialuniViewServiceInterface} from "./SocialuniViewServiceInterface";
import {Component, toNativeInternalInstance} from "vue";
import UUIDUtil from "qing-util/src/util/UUIDUtil";
import {Emit, Component, Vue, Watch} from 'vue-facing-decorator'

export default abstract class SocialuniViewService<T> implements SocialuniViewServiceInterface {
    //存储实例，因为初始时还没有$refs
    uuid: ComponentInternalInstance = UUIDUtil.getUUID()
    instance: Vue = null
    params: any = null

    //不要使用getInstance了

    initService(instance: Vue, params: any = {}) {
        this.instance = instance
        this.params = params
    }

    get $refs(): T {
        return this.instance.refs as T
    }
}
