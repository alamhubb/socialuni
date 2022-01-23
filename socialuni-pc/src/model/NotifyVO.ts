import CenterUserDetailRO from './social/CenterUserDetailRO'
import ChatVO from './chat/ChatVO'

export default class NotifyVO {
  public user: CenterUserDetailRO
  public chat: ChatVO
  public type: string
  public content: string
}
