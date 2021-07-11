import socialHttp from '@/plugins/social/http'
import SocialServerConfig from '@/plugins/social/api/center/SocialServerConfig'

export default class SocialReportAPI {
  static queryReportTypesAPI () {
    return socialHttp.post<string[]>(SocialServerConfig.SOCIAL_UNI_SERVER_URL + 'report/queryReportTypes')
  }
}
