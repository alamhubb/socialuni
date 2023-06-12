import SocialuniUserRO from "./SocialuniUserRO";
import ImgFileVO from "../ImgFileVO";
import CenterUserDetailRO from "../social/CenterUserDetailRO";

export default class SocialuniMineUserRO extends CenterUserDetailRO {
    phoneNum: string = null
    fansNum: number = null
    followNum: number = null
    birthday: string = null
    //社交币
    socialCoin: number = null

    constructor(mineUser?: SocialuniMineUserRO) {
        if (mineUser) {
            super(mineUser)
            this.phoneNum = mineUser.phoneNum
        }
    }
}
