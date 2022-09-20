import request from '../../plugins/http/request'
import CircleCreateQO from '@/socialuni/model/community/circle/CircleCreateQO'
import ToastUtil from '@/socialuni/utils/ToastUtil'
import SocialCircleRO from '@/socialuni/model/community/circle/SocialCircleRO'
import CircleTypeRO from '@/socialuni/model/community/circle/CircleTypeRO'
import SocialuniCircleQueryByTypeQO from "@/socialuni/model/QO/circle/SocialuniCircleQueryByTypeQO";


export default class SocialuniCircleAPI {
  static createCircleAPI(createQO: CircleCreateQO) {
    return request.post<SocialCircleRO>('socialuni/circle/createCircle', createQO).then(res => {
      ToastUtil.toast('创建成功')
      return res
    })
  }

  static queryHotCirclesAPI() {
    return request.get<SocialCircleRO []>('socialuni/circle/queryHotCircles')
  }

  static queryHotCircleTypesAPI() {
    return request.get<CircleTypeRO []>('socialuni/circle/queryHotCircleTypes')
  }

  static queryCircleTypesAPI() {
    return request.get<CircleTypeRO []>('socialuni/circle/queryCircleTypes')
  }

  static queryCirclesByTypeAPI(circleTypeName: string) {
    const qo = new SocialuniCircleQueryByTypeQO(circleTypeName)
    return request.post<SocialCircleRO []>('socialuni/circle/queryCirclesByCircleType', qo)
  }
}
