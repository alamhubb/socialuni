import TagAddVO from '@/model/tag/TagAddVO'
import socialHttp from '@/plugins/social/http'
import TagVO from '@/model/tag/TagVO'
import SocialServerConfig from '@/plugins/social/api/center/SocialServerConfig'

export default class SocialTagAPI {
  static addTagAPI (tagAdd: TagAddVO) {
    return socialHttp.post('tag/addTag', tagAdd)
  }

  //前端新增talk时，需要选择tag，前端搜索时使用
  static queryTagsAPI () {
    return socialHttp.post<TagVO[]>(SocialServerConfig.SOCIAL_UNI_SERVER_URL + 'tag/queryTags')
  }

  static queryHotTagsAPI () {
    return socialHttp.post(SocialServerConfig.SOCIAL_UNI_SERVER_URL + 'tag/queryHotTags')
  }

  static queryTagTypesAPI () {
    return socialHttp.post(SocialServerConfig.SOCIAL_UNI_SERVER_URL + 'tag/queryTagTypes')
  }

  static queryHotTagTypesAPI () {
    return socialHttp.post(SocialServerConfig.SOCIAL_UNI_SERVER_URL + 'tag/queryHotTagTypes')
  }

  static queryUserRecentlyDistrictsAPI () {
    return socialHttp.post(SocialServerConfig.SOCIAL_UNI_SERVER_URL + 'district/queryUserRecentlyTags')
  }
}
