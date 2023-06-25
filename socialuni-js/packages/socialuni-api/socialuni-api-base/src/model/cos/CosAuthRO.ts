

import {socialuniSystemModule} from "socialuni-util/src/store/SocialuniSystemModule";

let COS
if (socialuniSystemModule.isUniApp) {
    // import COS from 'cos-wx-sdk-v5'
} else {
   const cos = await import('cos-js-sdk-v5')
    console.log(cos)
}

import CosCredentialsRO from './CosCredentialsRO'

export default class CosAuthRO {
    credentials: CosCredentialsRO = null
    requestId: string = null
    expiration: string = null
    startTime: number = null
    expiredTime: number = null

    bucket: string = null
    uploadImgPath: string = null
    region: string = null
    cos = null

    constructor(authRO: CosAuthRO) {
        this.credentials = authRO.credentials
        this.requestId = authRO.requestId
        this.expiration = authRO.expiration
        this.startTime = authRO.startTime
        this.expiredTime = authRO.expiredTime
        this.bucket = authRO.bucket
        this.uploadImgPath = authRO.uploadImgPath
        this.region = authRO.region
    }
}
