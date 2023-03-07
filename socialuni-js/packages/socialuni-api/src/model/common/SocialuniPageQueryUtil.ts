import SocialuniPageQueryQO from './SocialuniPageQueryQO'
import SocialuniContentRO from "../social/SocialuniContentRO";
import LoadMoreType from "socialuni-constant/constant/LoadMoreType";

export default class SocialuniPageQueryUtil<T extends SocialuniContentRO> {
    queryQO: SocialuniPageQueryQO<T> = new SocialuniPageQueryQO<T>()
    api = null

    constructor(api: Function = null) {
        this.api = api
    }

    async initQuery(queryData) {
        this.queryQO = new SocialuniPageQueryQO()
        this.queryQO.queryData = queryData
        this.queryQO.loadMore = LoadMoreType.loading
        try {
            const res = await this.api(this.queryQO)
            this.queryQO.firstLoad = false
            this.queryQO.listData = res.data
            this.queryQO.loadMore = LoadMoreType.noMore
            if (res.data.length) {
                this.queryQO.queryTime = res.data[res.data.length - 1].updateTime
                if (res.data.length > 10) {
                    this.queryQO.loadMore = LoadMoreType.more
                }
            }
        } catch (e) {
            this.queryQO.loadMore = LoadMoreType.more
            this.queryQO.firstLoad = true
            this.queryQO.listData = []
            throw(e)
        }
    }

    async nextPageQuery(queryData) {
        console.log(456)
        console.log(this.queryQO.loadMore)
        if (this.queryQO.loadMore === LoadMoreType.loading){
            return
        }
        this.queryQO.queryData = queryData
        this.queryQO.loadMore = LoadMoreType.loading
        try {
            const res = await this.api(this.queryQO)
            this.queryQO.listData.push(...res.data)
            if (res.data.length) {
                this.queryQO.queryTime = res.data[res.data.length - 1].updateTime
                if (res.data.length > 10) {
                    this.queryQO.loadMore = LoadMoreType.more
                }
            }
        } catch (e) {
            this.queryQO.loadMore = LoadMoreType.more
            throw(e)
        }
    }
}
