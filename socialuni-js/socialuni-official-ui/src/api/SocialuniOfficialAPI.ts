import socialuniAppRequest from "socialuni-app-api/src/socialuniAppRequest.ts";

export default class SocialuniOfficialAPI {
    static statisticsAPI() {
        return socialuniAppRequest.get('socialuni/official/statistics')
    }

    static loginAPI(nickname) {
        return socialuniAppRequest.get('socialuni/official/login', {params: {name: nickname}})
    }

    static getMineUserAPI() {
        return socialuniAppRequest.get('socialuni/official/getMineUser')
    }

    static queryTalksAPI() {
        return socialuniAppRequest.get('socialuni/official/queryTalks')
    }

    static postTalkAPI(content) {
        return socialuniAppRequest.get('socialuni/official/postTalk', {params: {content: content}})
    }

    static postCommentAPI(talkId, content) {
        return socialuniAppRequest.get('socialuni/official/postComment', {params: {talkId: talkId, content: content}})
    }
}
