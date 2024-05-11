export default class TalkQueryVO {
    homeTabName: string
    gender: string
    minAge: number
    maxAge: number
    adCode: string
    circle: string
    lon: number
    lat: number
    tagNames: string[]

    constructor(homeTabName: string, gender: string, minAge: number, maxAge: number, tagNames: string[], circle: string) {
        this.homeTabName = homeTabName
        this.gender = gender
        this.minAge = minAge
        this.maxAge = maxAge
        this.tagNames = tagNames
        this.circle = circle
    }
}
