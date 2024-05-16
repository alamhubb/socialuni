import {reactive} from "vue";
import SocialuniUserExtendDetailRO from "socialuni-expand-api/src/model/SocialuniLikeAllConfigBO";

class SocialuniLikeConfigModule {
    config: SocialuniUserExtendDetailRO = new SocialuniUserExtendDetailRO()

    setConfig(config: SocialuniUserExtendDetailRO) {
        this.config = config
    }
}

export const socialuniLikeConfigModule: SocialuniLikeConfigModule = reactive(new SocialuniLikeConfigModule())
