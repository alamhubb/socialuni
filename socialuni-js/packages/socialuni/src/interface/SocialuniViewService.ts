import {Vue} from "vue-class-component";

export default abstract class SocialuniViewService {
    thisInstance: Vue = null

    initService?(vueInstance: Vue)
}
