import SocialuniContentRO from "../social/SocialuniContentRO";
import LoadMoreType from "socialuni-constant/constant/LoadMoreType";

export default class SocialuniPageQueryQO<T extends SocialuniContentRO, Q> {
    firstLoad = true
    queryTime = new Date()
    listData: T[] = []
    queryData: Q = null
    loadMore = LoadMoreType.more
}
