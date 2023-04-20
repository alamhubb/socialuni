import { SocialuniPlugin } from './SocialuniPlugin'
import { HttpRequestConfigInterface } from '../request/HttpRequestConfigInterface'

export interface SocialuniOptions {
  plugins: SocialuniPlugin[]
  requestConfig: HttpRequestConfigInterface
}
