import ToastUtil from "socialuni-util/src/util/ToastUtil";
import socialuniAppRequest from "socialuni-base-api/src/request/socialuniAppRequest";
import CircleCreateQO from "socialuni-base-api/src/model/community/circle/CircleCreateQO";
import SocialCircleRO from "socialuni-base-api/src/model/community/circle/SocialCircleRO";
import CircleCreateChatQO from "socialuni-base-api/src/model/community/circle/CircleCreateChatQO";
import SocialuniTalkTabCircleRO from "socialuni-base-api/src/model/community/circle/SocialuniTalkTabCircleRO";
import CircleTypeRO from "socialuni-base-api/src/model/community/circle/CircleTypeRO";
import SocialuniCircleQueryByTypeQO from "socialuni-base-api/src/model/QO/circle/SocialuniCircleQueryByTypeQO";


export default class SocialuniCircleAPI {
  static createCircleAPI(createQO: CircleCreateQO) {
    return socialuniAppRequest.post<SocialCircleRO>('socialuni/circle/createCircle', createQO).then(res => {
      ToastUtil.toast('创建成功')
      return res
    })
  }

  static createCircleChatAPI(createQO: CircleCreateChatQO) {
    return socialuniAppRequest.post<string>('socialuni/circle/createCircleChat', createQO)
  }

  static queryCircleTalkTabInfoAPI(createQO: CircleCreateChatQO) {
    return socialuniAppRequest.post<SocialuniTalkTabCircleRO>('socialuni/circle/queryCircleTalkTabInfo', createQO)
  }

  static queryHotCirclesAPI() {
    return socialuniAppRequest.get<SocialCircleRO []>('socialuni/circle/queryHotCircles')
  }

  static queryHotCircleTypesAPI() {
    return socialuniAppRequest.get<CircleTypeRO []>('socialuni/circle/queryHotCircleTypes')
  }

  static queryCircleTypesAPI() {
    return socialuniAppRequest.get<CircleTypeRO []>('socialuni/circle/queryCircleTypes')
  }

  static queryCirclesByTypeAPI(circleTypeName: string) {
    const qo = new SocialuniCircleQueryByTypeQO(circleTypeName)
    return socialuniAppRequest.post<SocialCircleRO []>('socialuni/circle/queryCirclesByCircleType', qo)
  }
}
