import CosCredentialsRO from '@/model/cos/CosCredentialsRO'

export default class CosAuthRO {
  credentials: CosCredentialsRO = null
  requestId: string = null
  expiration: string = null
  startTime: number = null
  expiredTime: number = null

  bucket: string = null
  uploadImgPath: string = null
  region: string = null
}
