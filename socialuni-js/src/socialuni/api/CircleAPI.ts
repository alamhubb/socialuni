import socialHttp from '../plugins/http/socialHttp'
import CircleCreateQO from '@/socialuni/model/community/circle/CircleCreateQO'


export default class CircleAPI {
  static createCircleAPI (createQO: CircleCreateQO) {
    return socialHttp.post<CircleCreateQO>('circle/createCircle', createQO)
  }
}
