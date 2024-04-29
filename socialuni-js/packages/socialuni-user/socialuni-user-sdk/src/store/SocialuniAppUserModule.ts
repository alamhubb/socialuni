import {reactive, UnwrapNestedRefs} from "vue";
import SocialuniMineUserAPI from "socialuni-user-api/src/api/SocialuniMineUserAPI";
import {socialuniUserModule} from "./SocialuniUserModule";
import PhoneAPI from "socialuni-user-api/src/api/PhoneAPI";
import SocialUserIdentityAPI from "socialuni-user-api/src/api/SocialUserIdentityAPI";
import FollowAPI from "socialuni-community-api/src/api/FollowAPI";
import SocialuniUserFansDetailRO from "socialuni-user-api/src/model/SocialuniUserFansDetailRO";
import SocialuniUserExpandDetailRO from "socialuni-user-api/src/model/SocialuniUserExpandDetailRO";
import SocialuniUserExpandAPI from "socialuni-user-api/src/api/SocialuniUserExpandAPI";

class SocialuniAppUserModule {
    private mineUserPhoneNum: string = null

    private mineUserFansDetail: SocialuniUserFansDetailRO = null
    private mineUserExpandDetail: SocialuniUserExpandDetailRO = null

    setMineUserPhoneNum(phoneNum: string) {
        this.mineUserPhoneNum = phoneNum

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
                this.mineUserFansDetail = res.data
            }),
            SocialuniUserExpandAPI.getMineUserExpandDetailAPI().then(res => {
                this.mineUserExpandDetail = res.data
            })
        ])
    }
}

export const socialuniAppUserModule: UnwrapNestedRefs<SocialuniAppUserModule> = reactive(new SocialuniAppUserModule())
