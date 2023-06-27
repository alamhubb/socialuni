import {Vue} from "vue-class-component";
import {SocialuniViewServiceInterface} from "./SocialuniViewServiceInterface";

export default abstract class SocialuniViewService<T> implements SocialuniViewServiceInterface{
    //存储实例，因为初始时还没有$refs
    instance: Vue = null

    initService(instance: Vue) {
        this.instance = instance
    }

    get $refs(): T {
        return this.instance.$refs as T
    }
}
