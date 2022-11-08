import { OpenImMsgRO } from "../openIm/OpenImMsgRO"
import { socialUserModule } from "socialuni-user/src/store/store"

export default class SocialuniContentUserRO {
  type: string = null
  isMine: boolean = null
  id: string
  nickname: string
  avatar: string

  constructor(msg: OpenImMsgRO) {
    this.id = msg.sendID
    this.isMine = msg.sendID === socialUserModule.userId
    this.nickname = msg.senderNickname
    this.avatar = msg.senderFaceUrl
  }
}
