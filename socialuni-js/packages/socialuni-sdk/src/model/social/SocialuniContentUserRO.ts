import { socialUserModule } from "socialuni-sdk/src/store/store"

export default class SocialuniContentUserRO {
  type: string = null
  isMine: boolean = null
  id: string
  nickname: string
  avatar: string
}
