import {reactive, UnwrapNestedRefs} from "vue";
import SocialuniMineUserAPI from "socialuni-user-api/src/api/SocialuniMineUserAPI";
import {socialuniUserModule} from "./SocialuniUserModule";
import PhoneAPI from "socialuni-user-api/src/api/PhoneAPI";
import SocialUserIdentityAPI from "socialuni-user-api/src/api/SocialUserIdentityAPI";
import FollowAPI from "socialuni-community-api/src/api/FollowAPI";
import SocialuniUserFansDetailRO from "socialuni-user-api/src/model/SocialuniUserFansDetailRO";
import SocialuniUserExpandDetailRO from "socialuni-user-api/src/model/SocialuniUserExpandDetailRO";
import SocialuniUserExpandAPI from "socialuni-user-api/src/api/SocialuniUserExpandAPI";
import SocialuniCoinAPI from "socialuni-user-api/src/api/SocialuniCoinAPI";

class SocialuniAppUserModule {
    private userPhoneNum: string = null

    private _mineUserFansDetail: SocialuniUserFansDetailRO = null
    private _mineUserExpandDetail: SocialuniUserExpandDetailRO = null
    private mineUserCoinInfo: { coinNum: number } = null

    get mineUserExpandDetail() {
        return this._mineUserExpandDetail
    }

    get mineUserFansDetail() {
        return this._mineUserFansDetail
    }

    get userCoinNum() {
        return this.mineUserCoinInfo.coinNum
    }

    set userCoinNum(value) {
        this.mineUserCoinInfo.coinNum = value
    }

    setMineUserPhoneNum(phoneNum: string) {
        this.userPhoneNum = phoneNum
    }

    get mineUserPhoneNum() {
        return this.userPhoneNum
    }

    get hasPhoneNum() {
        return !!this.mineUserPhoneNum
    }

    async getMineUserAction() {
        await Promise.all([
            SocialuniMineUserAPI.getMineUserInfoAPI().then((res: any) => {
                socialuniUserModule.setUser(res.data)
            }),
            PhoneAPI.getMineUserPhoneNumAPI().then((res: any) => {
                socialuniAppUserModule.setMineUserPhoneNum(res.data)
            }),
            SocialUserIdentityAPI.getMineUserIdentityStatusAPI(),
            FollowAPI.getMineUserFollowDetailAPI().then((res: any) => {
                this._mineUserFansDetail = res.data
            }),
            SocialuniUserExpandAPI.getMineUserExpandDetailAPI().then(res => {
                this._mineUserExpandDetail = res.data
            }),
            SocialuniCoinAPI.getUserCoinInfoAPI().then(res => {
                this.mineUserCoinInfo = res.data
            })
        ])
    }
}

export const socialuniAppUserModule: UnwrapNestedRefs<SocialuniAppUserModule> = reactive(new SocialuniAppUserModule())
