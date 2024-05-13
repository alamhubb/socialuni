import SocialuniPageQueryQO from './SocialuniPageQueryQO'
import SocialuniContentRO from "../social/SocialuniContentRO";
import LoadMoreType from "socialuni-constant/constant/LoadMoreType";

export default class SocialuniPageQueryUtil<T extends SocialuniContentRO, Q> {
    queryQO: SocialuniPageQueryQO<T, Q> = new SocialuniPageQueryQO()
    api = null
    loadMore:string = LoadMoreType.more
    listData: T[] = []

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
        this.loadMore = LoadMoreType.loading
        this.queryQO.pageNum = 1
        try {
            const res = await this.api(this.queryQO)
            this.queryQO.pageNum++
            this.queryQO.firstLoad = false
            this.listData = res.data
            this.loadMore = LoadMoreType.noMore
            if (res.data.length) {
                this.queryQO.queryTime = res.data[res.data.length - 1].updateTime
                if (res.data.length >= this.queryQO.pageSize) {
                    this.loadMore = LoadMoreType.more
                }
            }
        } catch (e) {
            this.loadMore = LoadMoreType.more
            this.queryQO.firstLoad = true
            this.listData = []
            throw (e)
        }
    }

    async nextPageQuery(queryData?: Q) {
        if (this.loadMore === LoadMoreType.loading) {
            return
        }
        if (queryData) {
            this.queryQO.queryData = queryData
        }
        this.loadMore = LoadMoreType.loading
        try {
            const res = await this.api(this.queryQO)
            this.listData.push(...res.data)
            console.log(this.listData)
            this.queryQO.pageNum++
            this.loadMore = LoadMoreType.noMore
            if (res.data.length) {
                this.queryQO.queryTime = res.data[res.data.length - 1].updateTime
                if (res.data.length >= this.queryQO.pageSize) {
                    this.loadMore = LoadMoreType.more
                }
            }
        } catch (e) {
            this.loadMore = LoadMoreType.more
            throw (e)
        }
    }
}
