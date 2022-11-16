import {socialUserModule} from "socialuni-sdk/src/store/store";

export default class SocialuniUserRO {
    id: string
    isMine?: boolean
    nickname?: string
    avatar?: string
    age?: number
    gender?: string
    birth?: string
    city: string = null
    type: string = null

    constructor(user?: SocialuniUserRO) {
        if (user) {
            this.id = user.id
            this.nickname = user.nickname;
            this.avatar = user.avatar;
            this.gender = user.gender;
            this.age = user.age;
            this.birth = user.birth;
            this.city = user.city;
            this.isMine = user.id === socialUserModule.userId
        }
    }
}
