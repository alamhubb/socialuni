import { socialConfigModule, socialSystemModule, socialUserModule } from '../store'

import AppMsg from '../constant/AppMsg'
import AlertUtil from './AlertUtil'
import ToastUtil from './ToastUtil'
import PageUtil from './PageUtil'
import CenterUserDetailRO from '../model/social/CenterUserDetailRO'
import UniUtil from '@/socialuni/utils/UniUtil'

export default class MsgUtil {
  static unBindPhoneNum () {
    const user: CenterUserDetailRO = socialUserModule.user
    if (!user) {
      MsgUtil.unLoginMessage()
    } else {
      // 如果登录了仅仅没绑定手机号，则提示跳转，区分qq和微信不同
      AlertUtil.confirm('绑定手机号才能发布内容，是否跳转至绑定手机号页面')
        .then(() => {
          // #ifdef MP-WEIXIN
          PageUtil.toMinePage()
          // #endif
          // #ifndef MP-WEIXIN
          PageUtil.toPhonePage()
          // #endif
        })
    }
  }

  static unLoginMessage () {
    AlertUtil.info(socialConfigModule.systemError601)
      .then(() => {
        // 没token才执行登录,有token证明已经登录，如果有错误应该清空token在执行这个
        PageUtil.toMinePage()
      })
  }

  static showUploadLoading () {
    UniUtil.showLoading('上传中')
  }


  static systemErrorMsg () {
    AlertUtil.hint(socialConfigModule.systemError604)
  }

  static unUploadImg () {
    AlertUtil.confirm('请完善用户信息绑定手机号并上传照片，才能进行此项操作，是否前往完善信息', '前往').then(() => {
      PageUtil.toMinePage()
    })
  }

  static unIdentityAuth () {
    AlertUtil.confirm('请对照片进行认证，才能进行此项操作', '前往认证').then(() => {
      PageUtil.toMinePage()
    })
  }

  static iosDisablePay () {
    return AlertUtil.hint(AppMsg.iosDisablePayMsg)
  }

  static notMpDisablePay () {
    return AlertUtil.hint(AppMsg.notMpDisablePayMsg)
  }

  static notPay () {
    return AlertUtil.hint(AppMsg.notPayMsg)
  }

  static payFailMsg () {
    return AlertUtil.hint(AppMsg.payFailMsg)
  }

  static sysErrMsg () {
    return AlertUtil.hint(socialConfigModule.systemError604)
  }

  static cantPopupPromptToast () {
    if (socialSystemModule.isIosAndMpQQ) {
      ToastUtil.toastLong('如遇到无法弹出输入框，请重启应用')
    }
  }
}
