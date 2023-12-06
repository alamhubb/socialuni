export default class SocialPhoneNumLoginQO {
    phoneNum: string = null
    password: string = null
    authCode: string = null

    constructor(phoneNum: string = null, password: string = null, authCode: string = null) {
        this.phoneNum = phoneNum
        this.authCode = authCode
        this.password = password
    }
}
