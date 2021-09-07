import socialHttp from '/socialuni/plugins/http/socialHttp'
import HomeSwiperVO from '/socialuni/model/HomeSwiperVO'

//清池专用api
export default class QingchiAPI {
  static queryHomeSwipersAPI () {
    return socialHttp.post<HomeSwiperVO[]>('app/queryHomeSwipers')
  }
}
