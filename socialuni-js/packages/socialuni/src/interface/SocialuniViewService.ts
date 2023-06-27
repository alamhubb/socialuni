import {Vue} from "vue-class-component";
import {SocialuniViewServiceInterface} from "./SocialuniViewServiceInterface";

export default abstract class SocialuniViewService implements SocialuniViewServiceInterface {
    thisInstance: Vue = null

    initService(vueInstance: Vue) {
        this.thisInstance = vueInstance
    }

    get $refs(): any {
        return this.thisInstance.$refs
    }
}
