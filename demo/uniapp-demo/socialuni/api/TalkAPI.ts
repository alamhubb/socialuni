import TalkAddVO from '/socialuni/model/talk/TalkAddVO'
import socialHttp from '/socialuni/plugins/http/socialHttp'
import CommentAddVO from '/socialuni/model/comment/CommentAddVO'
import HugAddVO from '/socialuni/model/HugAddVO'
import TalkQueryVO from '/socialuni/model/talk/TalkQueryVO'
import UserTalkQueryVO from '/socialuni/model/user/UserTalkQueryVO'
import DistrictVO from '/socialuni/model/DistrictVO'
import TalkDeleteVO from '/socialuni/model/talk/TalkDeleteVO'
import CommentDeleteVO from '/socialuni/model/comment/CommentDeleteVO'
import TalkVO from '/socialuni/model/talk/TalkVO'
import DomFile from '/socialuni/model/DomFile'
import ImgAddQO from '/socialuni/model/user/ImgAddQO'

export default class TalkAPI {
  static addTalkAPI (content: string, imgs: DomFile[], district: DistrictVO, tagIds: number[], visibleType: string, visibleGender: string) {
    const data: TalkAddVO = new TalkAddVO(content, imgs.map(item => new ImgAddQO(item)), district, tagIds, visibleType, visibleGender)
    return socialHttp.post('talk/postTalk', data)
  }

  static queryTalksAPI (talkIds: number[], tagIds: number[], tabType: string, gender: string, minAge: number, maxAge: number) {
    return socialHttp.post<TalkVO>('talk/queryTalks', new TalkQueryVO(talkIds, tagIds, tabType, gender, minAge, maxAge))
  }

  static queryUserTalksAPI (userId: string, talkIds: number[]) {
    return socialHttp.post('talk/queryUserTalks', new UserTalkQueryVO(userId, talkIds))
  }

  static queryTalkDetailAPI (talkId: string) {
    return socialHttp.post('talk/queryTalkDetail', { talkId })
  }

  static addCommentAPI (comment: CommentAddVO) {
    return socialHttp.post('comment/postComment', comment)
  }

  static addHugAPI (hug: HugAddVO) {
    return socialHttp.post('hug/addHug', hug)
  }

  static deleteTalkAPI (talkId: number) {
    return socialHttp.post('talk/deleteTalk', new TalkDeleteVO(talkId))
  }

  static deleteCommentAPI (commentId: number) {
    return socialHttp.post('comment/deleteComment', new CommentDeleteVO(commentId))
  }
}
