import SocialuniUserRO from "./SocialuniUserRO";
import ImgFileVO from "../ImgFileVO";
import CenterUserDetailRO from "../social/CenterUserDetailRO";

export default class SocialuniMineUserRO extends CenterUserDetailRO {
    //社交币
    socialCoin: number = null

    constructor(mineUser?: SocialuniMineUserRO) {
        if (mineUser) {
            super(mineUser)
            this.phoneNum = mineUser.phoneNum
        }
    }
}
