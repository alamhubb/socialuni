export default class SocialuniContentRO {
    id: string = null

    constructor(contentRo?: SocialuniContentRO) {
        if (contentRo) {
            this.id = contentRo.id
        }
    }
}
