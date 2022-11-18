import SocialuniUserRO from "../user/SocialuniUserRO";

export default class SocialUserContentRO extends SocialuniUserRO {
    type: string = null
    isMine: boolean = null
    hasFollowed: boolean = null
    identityAuth: boolean = null
    hasBeFollowed: boolean = null
}
