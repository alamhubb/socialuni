import request from '../plugins/http/request'
import HomeSwiperVO from '../model/HomeSwiperVO'

//清池专用api
export default class QingchiAPI {
  static queryHomeSwipersAPI () {
    return request.post<HomeSwiperVO[]>('app/queryHomeSwipers')
  }
}
