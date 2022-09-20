import TalkVO from './TalkVO'
import LoadMoreType from '../../constant/LoadMoreType'
import SocialTalkTabCircleRO from '@/socialuni/model/community/circle/SocialTalkTabCircleRO'

export default class TalkTabVO {
  name: string = null
  circle: SocialTalkTabCircleRO = null
  //有用的，需要根据此判断进入添加talk也面是否自定带入
  type: string = null
  talks: TalkVO[] = []
  firstLoad: boolean = true
  //用来区分是否为app内置tab，读取缓存过滤使用
  appDefaultTab: boolean = false
  scrollTop: number = 0
  loadMore: string = LoadMoreType.more
  queryTime: Date = new Date()


  constructor(name: string = null, type: string = null) {
    this.name = name
    this.type = type
  }
}
