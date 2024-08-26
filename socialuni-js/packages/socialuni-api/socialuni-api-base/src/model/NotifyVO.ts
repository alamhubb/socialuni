import CenterUserDetailRO from './social/CenterUserDetailRO'
import SocialuniChatRO from 'socialuni-api-base/src/model/SocialuniChatRO'

export default class NotifyVO<T> {
    public user: CenterUserDetailRO
    public chat: SocialuniChatRO
    public type: string
    public hintMsg: string
    public data: T
}
