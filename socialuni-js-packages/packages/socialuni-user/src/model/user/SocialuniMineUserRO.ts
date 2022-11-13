import SocialuniUserRO from "./SocialuniUserRO";

export default class SocialuniMineUserRO extends SocialuniUserRO {
    phoneNum: string

    constructor(mineUser: SocialuniMineUserRO) {
        super(mineUser)
        this.phoneNum = mineUser.phoneNum
    }
}
