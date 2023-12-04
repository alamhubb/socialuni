import CenterUserDetailRO from './social/CenterUserDetailRO'
import SocialuniChatRO from 'socialuni-api-base/src/model/SocialuniChatRO'

export default class NotifyVO<T> {
    public user: CenterUserDetailRO
    public chat: SocialuniChatRO
    public type: string
    public content: string
    public data: T
}
