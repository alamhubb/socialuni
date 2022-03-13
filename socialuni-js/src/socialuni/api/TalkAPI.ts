import TalkAddVO from '../model/talk/TalkAddVO'
import request from '../plugins/http/request'
import CommentAddVO from '../model/comment/CommentAddVO'
import HugAddVO from '../model/HugAddVO'
import TalkQueryVO from '../model/talk/TalkQueryVO'
import UserTalkQueryVO from '../model/user/UserTalkQueryVO'
import DistrictVO from '../model/DistrictVO'
import TalkDeleteVO from '../model/talk/TalkDeleteVO'
import CommentDeleteVO from '../model/comment/CommentDeleteVO'
import TalkVO from '../model/talk/TalkVO'
import DomFile from '../model/DomFile'
import ImgAddQO from '../model/user/ImgAddQO'
import TalkTabVO from '@/socialuni/model/talk/TalkTabVO'
import TalkTabType from '@/socialuni/const/TalkTabType'

export default class TalkAPI {
  static addTalkAPI (content: string, imgs: DomFile[], district: DistrictVO, tagIds: number[], visibleType: string, visibleGender: string, circleName: string) {
    const data: TalkAddVO = new TalkAddVO(content, imgs.map(item => new ImgAddQO(item)), district, tagIds, visibleType, visibleGender, circleName)
    return request.post('talk/postTalk', data)
  }

  static queryHomeTalkTabsAPI () {
    const talkTabFollowDefault = new TalkTabVO(TalkTabType.follow_name, TalkTabType.follow_type)
    const talkTabHomeDefault = new TalkTabVO(TalkTabType.home_name, TalkTabType.home_type)
    const talkTabCityDefault = new TalkTabVO(TalkTabType.city_name, TalkTabType.city_type)
    const cdxDefault = new TalkTabVO('处对象', TalkTabType.circle_type)
    const kuolieDefault = new TalkTabVO('扩列', TalkTabType.circle_type)
    const wangzheDefault = new TalkTabVO('王者', TalkTabType.circle_type)
    const friendsDefault = new TalkTabVO('交友', TalkTabType.circle_type)
    return [
      talkTabFollowDefault, talkTabHomeDefault, talkTabCityDefault, cdxDefault, kuolieDefault, wangzheDefault, friendsDefault
    ]
  }


  static queryTalksAPI (talkIds: number[], tagIds: number[], tabType: string, gender: string, minAge: number, maxAge: number, queryDate: Date) {
    return request.post<TalkVO>('talk/queryTalks', new TalkQueryVO(talkIds, tagIds, tabType, gender, minAge, maxAge, queryDate))
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
