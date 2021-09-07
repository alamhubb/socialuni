import socialHttp from '@/socialuni/http/socialHttp'

export default class LoveValueAPI {
  static watchVideoAdsAPI (success: boolean) {
    return socialHttp.post('loveValue/watchVideoAd2?success=' + success)
  }

  static queryTodayLoveValueAPI () {
    return socialHttp.post('loveValue/queryTodayLoveValue')
  }
}
