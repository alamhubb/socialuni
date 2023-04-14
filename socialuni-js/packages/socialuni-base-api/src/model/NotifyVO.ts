import CenterUserDetailRO from './social/CenterUserDetailRO'
import SocialuniChatRO from 'socialuni-base-api/src/model/SocialuniChatRO'

export default class NotifyVO {
  public user: CenterUserDetailRO
  public chat: SocialuniChatRO
  public type: string
  public content: string
}
