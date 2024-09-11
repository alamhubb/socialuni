import SocialuniUserExtendFriendQueryQO from "socialuni-api-base/src/model/user/SocialuniUserExtendFriendQueryQO";
import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO";
import socialuniUserRequest from "socialuni-user-api/src/request/socialuniUserRequest";
import SocialuniUserIdQO from "socialuni-api-base/src/model/QO/user/SocialuniUserIdQO";
import SocialuniUserExtendDetailRO from "../model/SocialuniUserExtendDetailRO";
import SocialuniLikeAllConfigBO from "../model/SocialuniLikeAllConfigBO";
import MessageAddVO from "socialuni-im-api/src/model/QO/message/MessageAddVO";
import socialuniImRequest from "socialuni-im-api/src/api/socialuniImRequest";
import MessageVO from "socialuni-im-api/src/model/RO/MessageVO";
import ChatQueryQO from "socialuni-im-api/src/model/QO/chat/ChatQueryQO";
import SocialuniChatRO from "socialuni-api-base/src/model/SocialuniChatRO";

export default class SocialuniUserLikeAPI {

  static addUserLikeAPI(user: SocialuniUserExtendDetailRO) {
    return socialuniUserRequest.post<CenterUserDetailRO[]>('socialuni/userLike/likeUser', {userId: user.id})
  }

  static getAllConfig() {
    return socialuniUserRequest.get<SocialuniLikeAllConfigBO>('socialuni/userLike/getLikeAllConfig')
  }

  static sendMsgAPI(chatId: string, content: string, type: string = null) {
    const msgAdd: MessageAddVO = new MessageAddVO(chatId, content, type)
    return socialuniImRequest.post <MessageVO>('socialuni/userLike/sendMsg', msgAdd)
  }

  static queryChatAPI(queryQO: ChatQueryQO) {
    return socialuniImRequest.post<SocialuniChatRO>('socialuni/userLike/queryChat', queryQO)
  }

  static queryChatListAPI() {
    return socialuniImRequest.get<SocialuniChatRO[]>('socialuni/userLike/queryChatList')
  }
}
