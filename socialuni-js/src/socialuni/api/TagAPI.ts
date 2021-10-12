import socialHttp from '../plugins/http/socialHttp'
import TagAddVO from '../model/tag/TagAddVO'

export default class TagAPI {
  static addTagAPI (tagName: string, description: string) {
    const tagAdd: TagAddVO = new TagAddVO(tagName, description)
    return socialHttp.post('tag/addTag', tagAdd)
  }

  //前端新增talk时，需要选择tag，前端搜索时使用
  static queryTagsAPI () {
    return socialHttp.post('tag/queryTags')
  }

  static queryHotTagsAPI () {
    return socialHttp.post('tag/queryHotTags')
  }

  static queryTagTypesAPI () {
    return socialHttp.post('tag/queryTagTypes')
  }

  static queryHotTagTypesAPI () {
    return socialHttp.post('tag/queryHotTagTypes')
  }

  static queryUserRecentlyDistrictsAPI () {
    return socialHttp.post('district/queryUserRecentlyTags')
  }
}
