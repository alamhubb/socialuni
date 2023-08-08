import {ComponentInternalInstance} from "vue";

export interface SocialuniViewServiceInterface {
    instance: ComponentInternalInstance
    params: any

    initService(instance: ComponentInternalInstance)
}
