import FriendAddQO from "../model/QO/firend/FriendAddQO";
import socialuniImRequest from "./socialuniImRequest";
import SocialuniFriendApplyUserRO from "../model/RO/SocialuniFriendApplyUserRO";
import SocialuniUserIdQO from 'socialuni-api-base/src/model/QO/user/SocialuniUserIdQO'
import SocialuniImMineUserDetailRO from '../model/RO/SocialuniImMineUserDetailRO'
import SocialuniImUserDetailRO from '../model/RO/SocialuniImUserDetailRO'

export default class SocialuniImUserAPI {
    static getImUserDetailAPI(userIdQO: SocialuniUserIdQO) {
        return socialuniImRequest.post<SocialuniImUserDetailRO>('socialuni/imUser/getImUserDetail', userIdQO)
    }

    static getImMineUser() {
        return socialuniImRequest.post<SocialuniImMineUserDetailRO>('socialuni/imUser/getImMineUser')
    }

    static closeStrangerMsg() {
        return socialuniImRequest.post<SocialuniImMineUserDetailRO>('socialuni/imUser/closeStrangerMsg')
    }

    static openStrangerMsg() {
        return socialuniImRequest.post<SocialuniImMineUserDetailRO>('socialuni/imUser/openStrangerMsg')
    }
}
