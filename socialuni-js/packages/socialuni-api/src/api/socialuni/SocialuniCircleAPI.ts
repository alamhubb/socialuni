import request from '../../../socialuni-common-api/src/request/request';
import CircleCreateQO from "../../model/community/circle/CircleCreateQO";
import SocialCircleRO from "../../model/community/circle/SocialCircleRO";
import CircleTypeRO from "../../model/community/circle/CircleTypeRO";
import SocialuniCircleQueryByTypeQO from "../../model/QO/circle/SocialuniCircleQueryByTypeQO";
import ToastUtil from "socialuni-sdk/src/utils/ToastUtil";
import CircleCreateChatQO from "../../model/community/circle/CircleCreateChatQO";
import SocialuniTalkTabCircleRO from "../../model/community/circle/SocialuniTalkTabCircleRO";


export default class SocialuniCircleAPI {
  static createCircleAPI(createQO: CircleCreateQO) {
    return request.post<SocialCircleRO>('socialuni/circle/createCircle', createQO).then(res => {
      ToastUtil.toast('创建成功')
      return res
    })
  }

  static createCircleChatAPI(createQO: CircleCreateChatQO) {
    return request.post<string>('socialuni/circle/createCircleChat', createQO)
  }

  static queryCircleTalkTabInfoAPI(createQO: CircleCreateChatQO) {
    return request.post<SocialuniTalkTabCircleRO>('socialuni/circle/queryCircleTalkTabInfo', createQO)
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
