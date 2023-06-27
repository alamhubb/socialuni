import {Vue} from "vue-class-component";

export interface SocialuniViewServiceInterface<T extends Vue> {
    initService(vueInstance: T)
}
