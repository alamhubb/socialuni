import TalkAddVO from '@/model/talk/TalkAddVO'
import http from '@/plugins/http'
import CommentAddVO from '@/model/comment/CommentAddVO'
import HugAddVO from '@/model/HugAddVO'
import TalkQueryVO from '@/model/talk/TalkQueryVO'
import UserTalkQueryVO from '@/model/user/UserTalkQueryVO'
import DistrictVO from '@/model/DistrictVO'
import TalkDeleteVO from '@/model/talk/TalkDeleteVO'
import CommentDeleteVO from '@/model/comment/CommentDeleteVO'
import TalkVO from '@/model/talk/TalkVO'
import DomFile from '@/model/DomFile'
import ImgAddQO from '@/model/user/ImgAddQO'

export default class TalkAPI {
  static addTalkAPI (content: string, imgs: DomFile[], district: DistrictVO, tagIds: number[], visibleType: string, visibleGender: string) {
    const data: TalkAddVO = new TalkAddVO(content, imgs.map(item => new ImgAddQO(item)), district, tagIds, visibleType, visibleGender)
    return http.post('talk/postTalk', data)
  }

  static queryTalksAPI (talkIds: number[], tagIds: number[], tabType: string, gender: string, minAge: number, maxAge: number) {
    return http.post<TalkVO>('talk/queryTalks', new TalkQueryVO(talkIds, tagIds, tabType, gender, minAge, maxAge))
  }

  static queryUserTalksAPI (userId: string, talkIds: number[]) {
    return http.post('talk/queryUserTalks', new UserTalkQueryVO(userId, talkIds))
  }

  static queryTalkDetailAPI (talkId: number) {
    return http.post('talk/queryTalkDetail?talkId=' + talkId)
  }

  static addCommentAPI (comment: CommentAddVO) {
    return http.post('comment/postComment', comment)
  }

  static addHugAPI (hug: HugAddVO) {
    return http.post('hug/addHug', hug)
  }

  static deleteTalkAPI (talkId: number) {
    return http.post('talk/deleteTalk', new TalkDeleteVO(talkId))
  }

  static deleteCommentAPI (commentId: number) {
    return http.post('comment/deleteComment', new CommentDeleteVO(commentId))
  }
}
