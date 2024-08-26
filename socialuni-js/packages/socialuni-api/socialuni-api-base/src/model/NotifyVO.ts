import CenterUserDetailRO from './social/CenterUserDetailRO'
import SocialuniChatRO from 'socialuni-api-base/src/model/SocialuniChatRO'

export default class NotifyVO<T> {
    public user: CenterUserDetailRO
    public chat: SocialuniChatRO
    public type: string
    public domainType: string
    public contentType: string
    public hintMsg: string
    public createTime: Date
    public data: T
}
