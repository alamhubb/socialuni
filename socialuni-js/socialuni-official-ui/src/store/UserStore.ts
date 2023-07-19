import UserVO from "@/model/base/UserVO";
import User from "@/model/user/User";
import {ref} from "vue";
import TokenUtil from "@/utils/TokenUtil";

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
        TokenUtil.remove()
    }
}
