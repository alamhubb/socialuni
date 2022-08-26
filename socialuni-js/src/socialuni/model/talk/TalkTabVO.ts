import TalkVO from './TalkVO'
import LoadMoreType from '../../constant/LoadMoreType'
import SocialTalkTabCircleRO from "@/socialuni/model/community/circle/SocialTalkTabCircleRO";

export default class TalkTabVO {
  name: string = null
  circle: SocialTalkTabCircleRO = null
  type: string = null
  talks: TalkVO[] = []
  firstLoad: boolean = true
  loadMore: string = LoadMoreType.more


  constructor(name: string = null, type: string = null) {
    this.name = name
    this.type = type
  }
}
