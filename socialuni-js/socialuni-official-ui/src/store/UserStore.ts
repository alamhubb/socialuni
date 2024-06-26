import User from "@/model/user/User";
import {ref} from "vue";
import SocialuniTokenUtil from "socialuni-user-sdk/src/util/SocialuniTokenUtil.ts";

export default class UserStore {
    private static _user: any = ref(null)

    static get user() {
        return this._user.value
    }

    static setUser(user: User) {
        this._user.value = user
    }

    static clearUser() {
        this._user.value = null as any
        SocialuniTokenUtil.remove()
    }
}
