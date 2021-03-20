import http from '@/plugins/http'

//清池专用api
export default class QingchiAPI {
  static queryHomeSwipersAPI () {
    return http.post('qingchi/queryHomeSwipers')
  }
}
