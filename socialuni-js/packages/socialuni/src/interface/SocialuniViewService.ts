import {Vue} from "vue-class-component";
import {SocialuniViewServiceInterface} from "./SocialuniViewServiceInterface";

export default abstract class SocialuniViewService<T> implements SocialuniViewServiceInterface<T> {
    //存储实例，因为初始时还没有$refs
    $refs: T = null

    initService($refs: T) {
        this.$refs = $refs
    }

    /*get $refs(): any {
        return this.thisInstance.$refs
    }*/
}
