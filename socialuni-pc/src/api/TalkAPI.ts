import TalkAddVO from '../model/talk/TalkAddVO'
import request from '../plugins/http/request'
import CommentAddVO from '../model/comment/CommentAddVO'
import HugAddVO from '../model/HugAddVO'
import UserTalkQueryVO from '../model/user/UserTalkQueryVO'
import DistrictVO from '../model/DistrictVO'
import TalkDeleteVO from '../model/talk/TalkDeleteVO'
import CommentDeleteVO from '../model/comment/CommentDeleteVO'
import TalkVO from '../model/talk/TalkVO'
import DomFile from '../model/DomFile'
import ImgAddQO from '../model/user/ImgAddQO'

export default class TalkAPI {
  static addTalkAPI (content: string, imgs: DomFile[], district: DistrictVO, tagIds: number[], visibleType: string, visibleGender: string) {
    const data: TalkAddVO = new TalkAddVO(content, imgs.map(item => new ImgAddQO(item)), district, tagIds, visibleType, visibleGender)
    return request.post('talk/postTalk', data)
  }

 /* static queryTalksAPI (talkIds: number[], tagIds: number[], tabType: string, gender: string, minAge: number, maxAge: number, queryDate: Date) {
    return request.post<TalkVO>('talk/queryTalks', new TalkQueryVO(talkIds, tagIds, tabType, gender, minAge, maxAge, queryDate))
  }*/
  static queryTalksAPI () {
    return request.post<TalkVO[]>('talk/queryTalks')
  }

  static queryUserTalksAPI (userId: string, talkIds: number[]) {
    return request.post('talk/queryUserTalks', new UserTalkQueryVO(userId, talkIds))
  }

  static queryTalkInfoAPI (talkId: string) {
    return request.post('talk/queryTalkInfo', { talkId })
  }

  static queryTalkDetailAPI (talkId: string) {
    return request.post('talk/queryTalkDetail', { talkId })
  }

  static addCommentAPI (comment: CommentAddVO) {
    return request.post('comment/postComment', comment)
  }

  static addHugAPI (hug: HugAddVO) {
    return request.post('hug/addHug', hug)
  }

  static deleteTalkAPI (talkId: number) {
    return request.post('talk/deleteTalk', new TalkDeleteVO(talkId))
  }

  static deleteCommentAPI (commentId: number) {
    return request.post('comment/deleteComment', new CommentDeleteVO(commentId))
  }
}
