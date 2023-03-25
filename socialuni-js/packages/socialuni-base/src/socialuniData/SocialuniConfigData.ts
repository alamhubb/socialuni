import {reactive} from "vue";

class SocialuniConfigData {
    readonly socialuniUrl = (import.meta.env.VITE_APP_SOCIALUNI_URL || 'https://api.socialuni.cn') + '/'
    readonly socialuniImUrl = (import.meta.env.VITE_APP_SOCIALUNI_IM_URL || this.socialuniUrl)
    readonly socialuniSecretKey = import.meta.env.VITE_APP_SOCIALUNI_SECRET_KEY || null
}

export const socialuniConfigData: SocialuniConfigData = reactive(new SocialuniConfigData())
