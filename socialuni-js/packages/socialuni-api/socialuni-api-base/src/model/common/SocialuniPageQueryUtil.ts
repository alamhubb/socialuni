import SocialuniPageQueryQO from './SocialuniPageQueryQO'
import SocialuniContentRO from "../social/SocialuniContentRO";
import LoadMoreType from "socialuni-constant/constant/LoadMoreType";
import CommonUtil from "qing-util/src/util/CommonUtil";

export default class SocialuniPageQueryUtil<T extends SocialuniContentRO, Q> {
    queryQO: SocialuniPageQueryQO<T, Q> = new SocialuniPageQueryQO()
    api = null
    loadMore: string = LoadMoreType.more
    listData: T[] = []
    interval = 2000

    constructor(api: Function = null, queryData?: Q) {
        this.api = api
        console.log(this.queryQO)
        if (queryData) {
            this.queryQO.queryData = queryData
            console.log(this.queryQO)
            console.log(565656)
        }
    }

    async initQuery(queryData?: Q) {
        console.log(queryData)
        const initQuery = await CommonUtil.debounce(this.initQueryNoDebounce, this.interval)
        await initQuery(queryData)
    }

    async forceLoadNextPage(queryData?: Q) {
        const forceLoadNextPage = CommonUtil.debounce(this.forceLoadNextPage, this.interval)
        await forceLoadNextPage(queryData)
    }

    async loadNextPage(queryData?: Q) {
        const loadNextPage = CommonUtil.debounce(this.loadNextPage, this.interval)
        await loadNextPage(queryData)
    }


    private async initQueryNoDebounce(queryData?: Q) {
        console.log(123123)
        console.log(this.queryQO)
        console.log(4564654)
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

    private async forceLoadNextPageNoDebounce(queryData?: Q) {
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

    private async loadNextPageNoDebounce(queryData?: Q) {
        if (this.loadMore === LoadMoreType.noMore) {
            return
        }
        this.forceLoadNextPage(queryData)
    }
}
