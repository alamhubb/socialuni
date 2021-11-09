import socialHttp from '../plugins/http/socialHttp'
import CircleCreateQO from '@/socialuni/model/community/circle/CircleCreateQO'
import Toast from '@/socialuni/utils/Toast'
import SocialCircleRO from '@/socialuni/model/community/circle/SocialCircleRO'


export default class CircleAPI {
  static createCircleAPI (createQO: CircleCreateQO) {
    return socialHttp.post<SocialCircleRO>('circle/createCircle', createQO).then(res => {
      Toast.toast('创建成功')
      return res
    })
  }

  static queryHotCirclesAPI () {
    return socialHttp.post<SocialCircleRO []>('circle/queryHotCircles')
  }
}
