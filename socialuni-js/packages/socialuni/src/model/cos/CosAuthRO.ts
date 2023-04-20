/* eslint-disable */
// #ifdef MP
//@ts-ignore
import COS from 'cos-wx-sdk-v5'
// #endif
// #ifdef H5
//@ts-ignore
import COS from 'cos-js-sdk-v5'
// #endif
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
  cos: COS = null

  constructor (authRO: CosAuthRO) {
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
