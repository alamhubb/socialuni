import { SocialuniPlugin } from './SocialuniPlugin'
import {Router} from "vue-router";

export interface SocialuniOption {
  plugins: SocialuniPlugin[]
  router: Router
  // requestConfig: HttpRequestConfigInterface
}
