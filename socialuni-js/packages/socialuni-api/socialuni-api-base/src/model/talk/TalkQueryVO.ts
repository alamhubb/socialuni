export default class TalkQueryVO {
    homeTabName: string
    gender: string
    minAge: number
    maxAge: number
    adCode: string
    lon: number
    lat: number
    firstLoad: boolean
    queryTime: Date
    tagNames: string[]

    constructor(homeTabName: string, gender: string, minAge: number, maxAge: number, queryTime: Date, tagNames: string[], firstLoad: boolean) {
        this.firstLoad = firstLoad
        this.homeTabName = homeTabName
        this.gender = gender
        this.minAge = minAge
        this.maxAge = maxAge
        this.queryTime = queryTime
        this.tagNames = tagNames
    }
}
