import TalkAddVO from '../../model/talk/TalkAddVO'
import CommentAddVO from '../../model/comment/CommentAddVO'
import HugAddVO from '../../model/HugAddVO'
import TalkQueryVO from '../../model/talk/TalkQueryVO'
import DistrictVO from '../../model/DistrictVO'
import TalkDeleteVO from '../../model/talk/TalkDeleteVO'
import CommentDeleteVO from '../../model/comment/CommentDeleteVO'
import TalkVO from '../../model/talk/TalkVO'
import request from "socialuni-api/src/request/request";
import ImgAddQO from "socialuni-sdk/src/model/user/ImgAddQO";
import DomFile from "socialuni-api/src/model/DomFile";
import UserTalkQueryVO from "socialuni-sdk/src/model/user/UserTalkQueryVO";

export default class SocialuniTalkAPI {
    static queryStickTalksAPI() {
        return request.get<TalkVO[]>('socialuni/talk/queryStickTalks')
    }

    static addTalkAPI(content: string, imgs: DomFile[], district: DistrictVO, visibleType: string, visibleGender: string, circleName: string, tagNames: string[]) {
        const data: TalkAddVO = new TalkAddVO(content, imgs.map(item => new ImgAddQO(item)), district, visibleType, visibleGender, circleName, tagNames)
        return request.post('socialuni/talk/postTalk', data)
    }

    static queryTalksAPIGet() {
        return request.get<TalkVO[]>('socialuni/talk/queryTalks')
    }

    static queryTalksAPI(homeTabName: string, gender: string, minAge: number, maxAge: number, queryTime: Date, tagNames: string[]) {
        return request.post<TalkVO>('socialuni/talk/queryTalks', new TalkQueryVO(homeTabName, gender, minAge, maxAge, queryTime, tagNames))
    }

    static queryUserTalksAPI(userId: string, talkIds: string[]) {
        return request.post('socialuni/talk/queryUserTalks', new UserTalkQueryVO(userId, talkIds))
    }

    static queryTalkInfoAPI(talkId: string) {
        return request.post('socialuni/talk/queryTalkInfo', {talkId})
    }

    static queryTalkDetailAPI(talkId: string) {
        return request.get('socialuni/talk/queryTalkDetail/' + talkId)
    }

    static addCommentAPI(comment: CommentAddVO) {
        return request.post('socialuni/comment/postComment', comment)
    }

    static addHugAPI(hug: HugAddVO) {
        return request.post('socialuni/hug/addHug', hug)
    }

    static deleteTalkAPI(talkId: string) {
        return request.post('socialuni/talk/deleteTalk', new TalkDeleteVO(talkId))
    }

    static deleteCommentAPI(commentId: string) {
        return request.post('socialuni/comment/deleteComment', new CommentDeleteVO(commentId))
    }
}
