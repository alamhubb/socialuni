import SocialuniTokenUtil from "../util/SocialuniTokenUtil";
import {reactive, UnwrapNestedRefs} from "vue";

class SocialuniTokenModule {
    private userToken: string = SocialuniTokenUtil.get() || null

    setToken(token: string) {
        this.userToken = token
        SocialuniTokenUtil.set(token)
    }

    get token() {
        return this.userToken
    }
}

export const socialuniTokenModule: UnwrapNestedRefs<SocialuniTokenModule> = reactive(new SocialuniTokenModule())
