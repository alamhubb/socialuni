import SocialuniContentRO from "../social/SocialuniContentRO";

export default class SocialuniPageQueryQO<T extends SocialuniContentRO> {
    firstLoad = true
    queryTime = new Date()
    listData: T[] = []
    queryData = null
}
