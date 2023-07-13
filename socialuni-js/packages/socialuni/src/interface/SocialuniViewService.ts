import {SocialuniViewServiceInterface} from "./SocialuniViewServiceInterface";
import {Vue} from "vue-class-component";

export default abstract class SocialuniViewService<T> implements SocialuniViewServiceInterface {
    //存储实例，因为初始时还没有$refs
    instance: Vue = null

    initService(instance: Vue) {
        this.instance = instance
    }

    get $refs(): T {
        console.log(this.instance)
        return this.instance.$refs as T
    }
}
