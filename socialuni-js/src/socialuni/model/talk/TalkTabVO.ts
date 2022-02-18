import TalkVO from './TalkVO'
import LoadMoreType from '../../const/LoadMoreType'

export default class TalkTabVO {
  name: string = null
  type: string = null
  //圈子id
  circleId: number = null
  talks: TalkVO[] = []
  firstLoad: boolean = true
  loadMore: string = LoadMoreType.more


  constructor (name: string = null, type: string = null) {
    this.name = name
    this.type = type
  }
}
