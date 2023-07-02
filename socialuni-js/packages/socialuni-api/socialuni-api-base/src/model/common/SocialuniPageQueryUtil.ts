import SocialuniPageQueryQO from './SocialuniPageQueryQO'
import SocialuniContentRO from "../social/SocialuniContentRO";
import LoadMoreType from "@socialuni/socialuni-constant/constant/LoadMoreType";

export default class SocialuniPageQueryUtil<T extends SocialuniContentRO, Q> {
    queryQO: SocialuniPageQueryQO<T, Q> = new SocialuniPageQueryQO()
    api = null

    constructor(api: Function = null, queryData?: Q) {
        this.api = api
        if (queryData) {
            this.queryQO.queryData = queryData
        }
    }

    async initQuery(queryData?: Q) {
        this.queryQO.firstLoad = true
        this.queryQO.queryTime = new Date()
        if (queryData) {
            this.queryQO.queryData = queryData
        }
        this.queryQO.loadMore = LoadMoreType.loading
        try {
            const res = await this.api(this.queryQO)
            this.queryQO.firstLoad = false
            this.queryQO.listData = res.data
            this.queryQO.loadMore = LoadMoreType.noMore
            if (res.data.length) {
                this.queryQO.queryTime = res.data[res.data.length - 1].updateTime
                if (res.data.length >= this.queryQO.pageSize) {
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

    async nextPageQuery(queryData?:Q) {
        if (this.queryQO.loadMore === LoadMoreType.loading) {
            return
        }
        if (queryData) {
            this.queryQO.queryData = queryData
        }
        this.queryQO.loadMore = LoadMoreType.loading
        try {
            const res = await this.api(this.queryQO)
            this.queryQO.listData.push(...res.data)
            this.queryQO.loadMore = LoadMoreType.noMore
            if (res.data.length) {
                this.queryQO.queryTime = res.data[res.data.length - 1].updateTime
                if (res.data.length >= this.queryQO.pageSize) {
                    this.queryQO.loadMore = LoadMoreType.more
                }
            }
        } catch (e) {
            this.queryQO.loadMore = LoadMoreType.more
            throw(e)
        }
    }
}
