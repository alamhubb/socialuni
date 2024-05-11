export default class TalkQueryVO {
    homeTabName: string
    gender: string
    minAge: number
    maxAge: number
    adCode: string
    lon: number
    lat: number
    tagNames: string[]

    constructor(homeTabName: string, gender: string, minAge: number, maxAge: number, tagNames: string[]) {
        this.homeTabName = homeTabName
        this.gender = gender
        this.minAge = minAge
        this.maxAge = maxAge
        this.tagNames = tagNames
    }
}
