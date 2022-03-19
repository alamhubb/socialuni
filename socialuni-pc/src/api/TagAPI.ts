import request from '../plugins/http/request'
import TagAddVO from '../model/community/tag/TagAddVO'

export default class TagAPI {
  static addTagAPI (tagName: string, description: string) {
    const tagAdd: TagAddVO = new TagAddVO(tagName, description)
    return request.post('tag/addTag', tagAdd)
  }

  //前端新增talk时，需要选择tag，前端搜索时使用
  static queryTagsAPI () {
    return request.post('tag/queryTags')
  }

  static queryHotTagsAPI () {
    return request.post('tag/queryHotTags')
  }

  static queryTagTypesAPI () {
    return request.post('tag/queryTagTypes')
  }

  static queryHotTagTypesAPI () {
    return request.post('tag/queryHotTagTypes')
  }

  static queryUserRecentlyDistrictsAPI () {
    return request.post('district/queryUserRecentlyTags')
  }
}
