import SocialuniContentRO from "../social/SocialuniContentRO";
import LoadMoreType from "socialuni-constant/constant/LoadMoreType";

export default class SocialuniPageQueryQO<T extends SocialuniContentRO, Q> {
    firstLoad = true
    queryTime = new Date()
    pageSize = 10
    pageNum = 1
    queryData: Q = null
    loadMore = LoadMoreType.more
}
