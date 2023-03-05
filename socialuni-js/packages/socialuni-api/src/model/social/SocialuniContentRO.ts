export default class SocialuniContentRO {
    id: string = null
    updateTime: Date = null

    constructor(contentRo?: SocialuniContentRO) {
        if (contentRo) {
            this.id = contentRo.id
            this.updateTime = contentRo.updateTime;
        }
    }
}
