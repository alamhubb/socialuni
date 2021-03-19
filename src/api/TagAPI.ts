import http from '@/plugins/http'
import TagAddVO from '@/model/tag/TagAddVO'

export default class TagAPI {
  static addTagAPI (tagName: string, description: string) {
    const tagAdd: TagAddVO = new TagAddVO(tagName, description)
    return http.post('tag/addTag', tagAdd)
  }

  //前端新增talk时，需要选择tag，前端搜索时使用
  static queryTagsAPI () {
    return http.post('tag/queryTags')
  }

  static queryHotTagsAPI () {
    return http.post('tag/queryHotTags')
  }

  static queryTagTypesAPI () {
    return http.post('tag/queryTagTypes')
  }

  static queryHotTagTypesAPI () {
    return http.post('tag/queryHotTagTypes')
  }

  static queryUserRecentlyDistrictsAPI () {
    return http.post('district/queryUserRecentlyTags')
  }
}
