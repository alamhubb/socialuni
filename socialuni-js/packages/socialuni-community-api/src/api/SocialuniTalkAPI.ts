import UserTalkQueryVO from "socialuni-base-api/src/model/user/UserTalkQueryVO";
import socialuniCommunityRequest from "../request/socialuniCommunityRequest";
import TalkVO from "socialuni-base-api/src/model/talk/TalkVO";
import DomFile from "socialuni-util/src/model/DomFile";
import DistrictVO from "socialuni-base-api/src/model/DistrictVO";
import ImgAddQO from "socialuni-base-api/src/model/user/ImgAddQO";
import TalkAddVO from "socialuni-base-api/src/model/talk/TalkAddVO";
import TalkQueryVO from "socialuni-base-api/src/model/talk/TalkQueryVO";
import CommentAddVO from "socialuni-base-api/src/model/comment/CommentAddVO";
import HugAddVO from "socialuni-base-api/src/model/HugAddVO";
import TalkDeleteVO from "socialuni-base-api/src/model/talk/TalkDeleteVO";
import CommentDeleteVO from "socialuni-base-api/src/model/comment/CommentDeleteVO";

export default class SocialuniTalkAPI {
    static queryStickTalksAPI() {
        return socialuniCommunityRequest.get<TalkVO[]>('socialuni/talk/queryStickTalks')
    }

    static addTalkAPI(content: string, imgs: DomFile[], district: DistrictVO, visibleType: string, visibleGender: string, circleName: string, tagNames: string[]) {
        const data: TalkAddVO = new TalkAddVO(content, imgs.map(item => new ImgAddQO(item)), district, visibleType, visibleGender, circleName, tagNames)
        return socialuniCommunityRequest.post('socialuni/talk/postTalk', data)
    }

    static queryTalksAPIGet() {
        return socialuniCommunityRequest.get<TalkVO[]>('socialuni/talk/queryTalks')
    }

    static queryTalksAPI(talkQO: TalkQueryVO) {
        return socialuniCommunityRequest.post<TalkVO>('socialuni/talk/queryTalks', talkQO)
    }

    static queryUserTalksAPI(userId: string, talkIds: string[]) {
        return socialuniCommunityRequest.post('socialuni/talk/queryUserTalks', new UserTalkQueryVO(userId, talkIds))
    }

    static queryTalkInfoAPI(talkId: string) {
        return socialuniCommunityRequest.post('socialuni/talk/queryTalkInfo', {talkId})
    }

    static queryTalkDetailAPI(talkId: string) {
        return socialuniCommunityRequest.get('socialuni/talk/queryTalkDetail/' + talkId)
    }

    static addCommentAPI(comment: CommentAddVO) {
        return socialuniCommunityRequest.post('socialuni/comment/postComment', comment)
    }

    static addHugAPI(hug: HugAddVO) {
        return socialuniCommunityRequest.post('socialuni/hug/addHug', hug)
    }

    static deleteTalkAPI(talkId: string) {
        return socialuniCommunityRequest.post('socialuni/talk/deleteTalk', new TalkDeleteVO(talkId))
    }

    static deleteCommentAPI(commentId: string) {
        return socialuniCommunityRequest.post('socialuni/comment/deleteComment', new CommentDeleteVO(commentId))
    }
}
