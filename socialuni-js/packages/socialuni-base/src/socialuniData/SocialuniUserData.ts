import {reactive} from "vue";
import SocialuniTokenUtil from "../utils/SocialuniTokenUtil";

class SocialuniUserData {
    userToken: string = SocialuniTokenUtil.get() || null

    setToken(token: string) {
        this.userToken = token
    }

    get token() {
        return this.userToken
    }
}

export const socialuniUserData: SocialuniUserData = reactive(new SocialuniUserData())

