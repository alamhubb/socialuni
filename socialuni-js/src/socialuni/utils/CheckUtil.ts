import {socialUserModule} from '../store'
import MsgUtil from "@/socialuni/utils/MsgUtil";

export default class CheckUtil {
  static unLoginCheck() {
    if (!socialUserModule.user) {
      MsgUtil.unLoginMessage()
      Error('未登录')
    }
  }
}
