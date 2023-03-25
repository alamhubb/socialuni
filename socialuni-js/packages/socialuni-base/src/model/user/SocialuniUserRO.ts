import {socialUserModule} from "socialuni-sdk/src/store/store";
import SocialUserContentRO from "../social/SocialuniContentRO";

export default class SocialuniUserRO extends SocialUserContentRO{
    id: string = null
    isMine?: boolean = false
    nickname?: string = null
    avatar?: string = null
    age?: number = null
    gender?: string = null
    city: string = null
    type: string = null

    constructor(user?: SocialuniUserRO) {
        super(user)
        if (user) {
            this.id = user.id
            this.nickname = user.nickname;
            this.avatar = user.avatar;
            this.gender = user.gender;
            this.age = user.age;
            this.city = user.city;
            this.isMine = user.isMine
        }
    }
}
