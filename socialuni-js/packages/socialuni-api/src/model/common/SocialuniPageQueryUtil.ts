import SocialuniPageQueryQO from './SocialuniPageQueryQO'

export default class SocialuniPageQueryUtil {
    queryQO = new SocialuniPageQueryQO()
    api = null

    constructor(api: Function = null) {
        this.api = api
    }

    async initQuery(queryData) {
        console.log('zhicinu98')
        this.queryQO = new SocialuniPageQueryQO()
        this.queryQO.queryData = queryData
        try {
            const res = await this.api(this.queryQO)
            this.queryQO.firstLoad = false
            this.queryQO.listData = res.data
            if (res.data.length) {
                this.queryQO.queryTime = res.data[res.data.length - 1].updateTime
            }
        } catch (e) {
            this.queryQO.firstLoad = true
            this.queryQO.listData = []
            throw(e)
        }
    }

    async nextPageQuery(queryData) {
        this.queryQO.queryData = queryData
        const res = await this.api(this.queryQO)
        this.queryQO.listData.push(...res.data)
        if (res.data.length) {
            this.queryQO.queryTime = res.data[res.data.length - 1].updateTime
        }
    }
}
