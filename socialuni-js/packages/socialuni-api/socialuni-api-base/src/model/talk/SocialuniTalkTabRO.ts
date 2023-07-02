import TalkVO from './TalkVO'
import SocialuniTalkTabCircleRO from "../community/circle/SocialuniTalkTabCircleRO";
import LoadMoreType from "@socialuni/socialuni-constant/constant/LoadMoreType";

export default class SocialuniTalkTabRO {
  name: string = null
  circle: SocialuniTalkTabCircleRO = null
  //有用的，需要根据此判断进入添加talk也面是否自定带入
  type: string = null
  talks: TalkVO[] = []
  firstLoad: boolean = true
  //用来区分是否为app内置tab，读取缓存过滤使用
  appDefaultTab: boolean = false
  scrollTop: number = -1
  pageScrollTop: number = -1
  loadMore: string = LoadMoreType.more
  queryTime: Date = new Date()

  constructor(name: string = null, type: string = null) {
    this.name = name
    this.type = type
  }
}
