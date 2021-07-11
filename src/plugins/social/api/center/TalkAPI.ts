import TalkAddVO from '@/model/talk/TalkAddVO'
import socialHttp from '@/plugins/social/http'
import CommentAddVO from '@/model/comment/CommentAddVO'
import HugAddVO from '@/model/HugAddVO'
import TalkQueryVO from '@/model/talk/TalkQueryVO'
import UserTalkQueryVO from '@/model/user/UserTalkQueryVO'
import DistrictVO from '@/model/DistrictVO'
import TalkDeleteVO from '@/model/talk/TalkDeleteVO'
import CommentDeleteVO from '@/model/comment/CommentDeleteVO'
import TalkVO from '@/model/talk/TalkVO'
import TalkIdQO from '@/model/talk/TalkIdQO'
import DomFile from '@/model/DomFile'

export default class TalkAPI {
  static addTalkAPI (content: string, imgs: DomFile[], district: DistrictVO, tagIds: number[], visibleType: string, visibleGender: string) {
    const data: TalkAddVO = new TalkAddVO(content, imgs, district, tagIds, visibleType, visibleGender)
    return socialHttp.post('socialuni/talk/addTalk', data)
  }

  static queryTalksAPI (talkIds: number[], tagIds: number[], homeType: string, gender: string, minAge: number, maxAge: number) {
    return socialHttp.post<TalkVO>('socialuni/talk/queryTalks', new TalkQueryVO(talkIds, tagIds, homeType, gender, minAge, maxAge))
  }

  static queryUserTalksAPI (userId: string, talkIds: number[]) {
    return socialHttp.post('talk/queryUserTalks', new UserTalkQueryVO(userId, talkIds))
  }

  static queryTalkDetailAPI (talkId: string) {
    return socialHttp.post('socialuni/talk/queryTalkDetail', new TalkIdQO(talkId))
  }

  static addCommentAPI (comment: CommentAddVO) {
    return socialHttp.post('socialuni/comment/addComment', comment)
  }

  static addHugAPI (hug: HugAddVO) {
    return socialHttp.post('hug/addHug', hug)
  }

  static deleteTalkAPI (talkId: number) {
    return socialHttp.post('socialuni/talk/deleteTalk', new TalkDeleteVO(talkId))
  }

  static deleteCommentAPI (commentId: number) {
    return socialHttp.post('comment/deleteComment', new CommentDeleteVO(commentId))
  }
}
