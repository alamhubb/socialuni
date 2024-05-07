import {Component, toNativeInternalInstance} from "vue";

export interface SocialuniViewServiceInterface {
    instance: ComponentInternalInstance
    params: any

    initService(instance: ComponentInternalInstance)
}
