import socialuniCommunityRequest from "../request/socialuniCommunityRequest";
import TagAddVO from "socialuni-base-api/src/model/community/tag/TagAddVO";

export default class TagAPI {
  static addTagAPI(tagName: string, description: string) {
    const tagAdd: TagAddVO = new TagAddVO(tagName, description)
    return socialuniCommunityRequest.post('socialuni/tag/addTag', tagAdd)
  }

  //前端新增talk时，需要选择tag，前端搜索时使用
  // static queryTagsAPI() {
  //   return socialuniCommunityRequest.get('socialuni/tag/queryTags')
  // }
  //
  // static queryHotTagsAPI() {
  //   return socialuniCommunityRequest.get('socialuni/tag/queryHotTags')
  // }
  //
  // static queryTagTypesAPI() {
  //   return socialuniCommunityRequest.get('socialuni/tag/queryTagTypes')
  // }
  //
  // static queryHotTagTypesAPI() {
  //   return socialuniCommunityRequest.get('socialuni/tag/queryHotTagTypes')
  // }
  //
  // static queryUserRecentlyDistrictsAPI() {
  //   return socialuniCommunityRequest.post('socialuni/district/queryUserRecentlyTags')
  // }
}
