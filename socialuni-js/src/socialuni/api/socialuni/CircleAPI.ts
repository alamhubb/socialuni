import request from '../../plugins/http/request'
import CircleCreateQO from '@/socialuni/model/community/circle/CircleCreateQO'
import ToastUtil from '@/socialuni/utils/ToastUtil'
import SocialCircleRO from '@/socialuni/model/community/circle/SocialCircleRO'
import CircleTypeRO from '@/socialuni/model/community/circle/CircleTypeRO'


export default class CircleAPI {
  static createCircleAPI(createQO: CircleCreateQO) {
    return request.post<SocialCircleRO>('socialuni/circle/createCircle', createQO).then(res => {
      ToastUtil.toast('创建成功')
      return res
    })
  }

  static queryHotCirclesAPI() {
    return request.post<SocialCircleRO []>('socialuni/circle/queryHotCircles')
  }

  static queryCircleTypesAPI() {
    return request.post<CircleTypeRO []>('socialuni/circle/queryCircleTypes')
  }
}
