export default class SocialuniUserRO {
    userId: string
    nickname?: string
    avatar?: string
    age?: number
    gender?: number
    birth?: string

    constructor(user: SocialuniUserRO) {
        this.userId = user.userId
        this.nickname = user.nickname;
        this.avatar = user.avatar;
        this.gender = user.gender;
        this.age = user.age;
        this.birth = user.birth;
    }
}
