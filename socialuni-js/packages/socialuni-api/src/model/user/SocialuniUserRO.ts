import {socialUserModule} from "socialuni-sdk/src/store/store";

export default class SocialuniUserRO {
    id: string
    isMine?: boolean
    nickname?: string
    avatar?: string
    age?: number
    gender?: number
    birth?: string

    constructor(user?: SocialuniUserRO) {
        if (user) {
            this.id = user.id
            this.nickname = user.nickname;
            this.avatar = user.avatar;
            this.gender = user.gender;
            this.age = user.age;
            this.birth = user.birth;
            this.isMine = user.id === socialUserModule.userId
        }
    }
}
