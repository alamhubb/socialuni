import {ComponentInternalInstance} from "vue";
import {Vue} from "vue-facing-decorator";

export interface SocialuniViewServiceInterface {
    instance: Vue
    params: any

    initService(instance: Vue)
}
