import CosCredentialsRO from '@/model/cos/CosCredentialsRO'

export default class CosAuthRO {
  credentials: CosCredentialsRO = null
  requestId: string = null
  expiration: string = null
  startTime: number = null
  expiredTime: number = null

  bucket: string = null
  region: string = null
  path: string = null
}