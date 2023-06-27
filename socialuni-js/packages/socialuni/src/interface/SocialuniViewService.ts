import {Vue} from "vue-class-component";
import {SocialuniViewServiceInterface} from "./SocialuniViewServiceInterface";

export default abstract class SocialuniViewService<T extends Vue> implements SocialuniViewServiceInterface<T> {
    //存储实例，因为初始时还没有$refs
    thisInstance: T = null

    initService(vueInstance: T) {
        this.thisInstance = vueInstance
    }

    get $refs(): any {
        return this.thisInstance.$refs
    }
}
