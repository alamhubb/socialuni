import socialHttp from '../plugins/http/socialHttp'
import CircleCreateQO from '@/socialuni/model/community/circle/CircleCreateQO'
import ToastUtil from '@/socialuni/utils/ToastUtil'
import SocialCircleRO from '@/socialuni/model/community/circle/SocialCircleRO'
import CircleTypeRO from '@/socialuni/model/community/circle/CircleTypeRO'


export default class CircleAPI {
  static createCircleAPI (createQO: CircleCreateQO) {
    return socialHttp.post<SocialCircleRO>('circle/createCircle', createQO).then(res => {
      ToastUtil.toast('创建成功')
      return res
    })
  }

  static queryHotCirclesAPI () {
    return socialHttp.post<SocialCircleRO []>('circle/queryHotCircles')
  }

  static queryCircleTypesAPI () {
    return socialHttp.post<CircleTypeRO []>('circle/queryCircleTypes')
  }
}
