import request from "socialuni-api/src/request/request";
import CircleCreateQO from "../../model/community/circle/CircleCreateQO";
import SocialCircleRO from "../../model/community/circle/SocialCircleRO";
import ToastUtil from "socialuni-sdk/src/utils/ObjectUtil";
import CircleTypeRO from "../../model/community/circle/CircleTypeRO";
import SocialuniCircleQueryByTypeQO from "../../model/QO/circle/SocialuniCircleQueryByTypeQO";


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
