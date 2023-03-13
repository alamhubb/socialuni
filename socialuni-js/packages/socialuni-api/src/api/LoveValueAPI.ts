import request from '../../socialuni-common-api/src/request/request';

export default class LoveValueAPI {
    static watchVideoAdsAPI(success: boolean) {
        return request.post('loveValue/watchVideoAd2?success=' + success)
    }

    static queryTodayLoveValueAPI() {
        return request.post('loveValue/queryTodayLoveValue')
    }
}
