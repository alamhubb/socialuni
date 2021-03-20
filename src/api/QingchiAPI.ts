import http from '@/plugins/http'
import HomeSwiperVO from '@/model/HomeSwiperVO'

//清池专用api
export default class QingchiAPI {
  static queryHomeSwipersAPI () {
    return http.get<HomeSwiperVO[]>('qingchi/queryHomeSwipers')
  }
}
