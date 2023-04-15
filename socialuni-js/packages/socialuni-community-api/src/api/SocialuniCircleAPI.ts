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
    return socialuniAppsocialuniCommunityRequest.post<SocialCircleRO>('socialuni/circle/createCircle', createQO).then(res => {
      ToastUtil.toast('创建成功')
      return res
    })
  }

  static createCircleChatAPI(createQO: CircleCreateChatQO) {
    return socialuniAppsocialuniCommunityRequest.post<string>('socialuni/circle/createCircleChat', createQO)
  }

  static queryCircleTalkTabInfoAPI(createQO: CircleCreateChatQO) {
    return socialuniAppsocialuniCommunityRequest.post<SocialuniTalkTabCircleRO>('socialuni/circle/queryCircleTalkTabInfo', createQO)
  }

  static queryHotCirclesAPI() {
    return socialuniAppsocialuniCommunityRequest.get<SocialCircleRO []>('socialuni/circle/queryHotCircles')
  }

  static queryHotCircleTypesAPI() {
    return socialuniAppsocialuniCommunityRequest.get<CircleTypeRO []>('socialuni/circle/queryHotCircleTypes')
  }

  static queryCircleTypesAPI() {
    return socialuniAppsocialuniCommunityRequest.get<CircleTypeRO []>('socialuni/circle/queryCircleTypes')
  }

  static queryCirclesByTypeAPI(circleTypeName: string) {
    const qo = new SocialuniCircleQueryByTypeQO(circleTypeName)
    return socialuniAppsocialuniCommunityRequest.post<SocialCircleRO []>('socialuni/circle/queryCirclesByCircleType', qo)
  }
}
