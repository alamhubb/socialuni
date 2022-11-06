import CenterUserDetailRO from './social/CenterUserDetailRO'
import SocialuniChatRO from './chat/SocialuniChatRO'

export default class NotifyVO {
  public user: CenterUserDetailRO
  public chat: SocialuniChatRO
  public type: string
  public content: string
}
