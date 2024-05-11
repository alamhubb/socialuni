import CircleCreateQO from "socialuni-api-base/src/model/community/circle/CircleCreateQO";
import SocialCircleRO from "socialuni-api-base/src/model/community/circle/SocialCircleRO";
import CircleCreateChatQO from "socialuni-api-base/src/model/community/circle/CircleCreateChatQO";
import SocialuniTalkTabCircleRO from "socialuni-api-base/src/model/community/circle/SocialuniTalkTabCircleRO";
import CircleTypeRO from "socialuni-api-base/src/model/community/circle/CircleTypeRO";
import SocialuniCircleQueryByTypeQO from "socialuni-api-base/src/model/QO/circle/SocialuniCircleQueryByTypeQO";
import socialuniCommunityRequest from "../request/socialuniCommunityRequest";
import QingAppUtil from "qingjs/src/util/QingAppUtil";

export default class SocialuniCircleAPI {
  static createCircleAPI(createQO: CircleCreateQO) {
    return socialuniCommunityRequest.post<SocialCircleRO>('socialuni/circle/createCircle', createQO).then(res => {
      QingAppUtil.ToastUtil.toast('创建成功')
      return res
    })
  }

  static createCircleChatAPI(createQO: CircleCreateChatQO) {
    return socialuniCommunityRequest.post<string>('socialuni/circle/createCircleChat', createQO)
  }

  static queryCircleTalkTabInfoAPI(createQO: CircleCreateChatQO) {
    return socialuniCommunityRequest.post<SocialuniTalkTabCircleRO>('socialuni/circle/queryCircleTalkTabInfo', createQO)
  }

  static queryHotCirclesAPI() {
    return socialuniCommunityRequest.get<SocialCircleRO []>('socialuni/circle/queryHotCircles')
  }

  static queryHotCircleTypesAPI() {
    return socialuniCommunityRequest.get<CircleTypeRO []>('socialuni/circle/queryHotCircleTypes')
  }

  static queryCircleTypesAPI() {
    return socialuniCommunityRequest.get<CircleTypeRO []>('socialuni/circle/queryCircleTypes')
  }

  static queryCirclesByTypeAPI(circleTypeName: string) {
    const qo = new SocialuniCircleQueryByTypeQO(circleTypeName)
    return socialuniCommunityRequest.post<SocialCircleRO []>('socialuni/circle/queryCirclesByCircleType', qo)
  }
}
