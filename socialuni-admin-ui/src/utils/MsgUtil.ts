import ToastUtil from '@/utils/ToastUtil'
import ErrorMsg from '@/constants/ErrorMsg'

export default class MsgUtil {
  static unLoginMessage() {
    ToastUtil.error(ErrorMsg.unLogin601Msg)
    /* .then(() => {
        // 没token才执行登录,有token证明已经登录，如果有错误应该清空token在执行这个
        PageUtil.toMinePage()
      })*/
  }

  static systemErrorMsg() {
    ToastUtil.error(ErrorMsg.systemError604Msg)
  }
}
