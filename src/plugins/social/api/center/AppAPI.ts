import HomeSwiperVO from '@/model/HomeSwiperVO'
import socialHttp from '@/plugins/social/http'

//清池专用api
export default class AppAPI {
  static queryHomeSwipersAPI () {
    return socialHttp.get<HomeSwiperVO[]>('app/queryHomeSwipers')
  }
}
