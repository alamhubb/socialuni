import request from '../../plugins/http/request'
import TagAddVO from '../../model/community/tag/TagAddVO'

export default class TagAPI {
  static addTagAPI(tagName: string, description: string) {
    const tagAdd: TagAddVO = new TagAddVO(tagName, description)
    return request.post('socialuni/tag/addTag', tagAdd)
  }

  //前端新增talk时，需要选择tag，前端搜索时使用
  static queryTagsAPI() {
    return request.get('socialuni/tag/queryTags')
  }

  static queryHotTagsAPI() {
    return request.get('socialuni/tag/queryHotTags')
  }

  static queryTagTypesAPI() {
    return request.get('socialuni/tag/queryTagTypes')
  }

  static queryHotTagTypesAPI() {
    return request.get('socialuni/tag/queryHotTagTypes')
  }

  static queryUserRecentlyDistrictsAPI() {
    return request.post('socialuni/district/queryUserRecentlyTags')
  }
}
