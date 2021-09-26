<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { socialAppModule, socialSystemModule } from '@/socialuni/store'
import UserService from '@/socialuni/service/UserService'
import UniUtil from '@/socialuni/utils/UniUtil'

@Component
export default class SocialMinxinVue extends Vue {
  onLaunch(){
    //无论如何都要获取当前用户信息
    UserService.getMineUserInitDataAction()
    // 执行获取系统信息的函数,始终保持第一，因为别的都依赖于他
    // 获取用户需要使用需要限制性
    socialSystemModule.getSystemInfo()
    //为三方只授权不需要查询信息
    if (socialAppModule.threeSecretKey) {
      // appModule.getThreeDevUserAction()


    } else {
      //页面启动，启动函数
      socialSystemModule.appLunchAction()
    }
    //页面启动，启动函数
    // systemModule.appInit()
    UniUtil.showShareMenu()
  }


  onShow () {
    // this.socialPageProvide.msgInputShow = true
  }

  onHide () {
    // console.log(this.socialPageProvide.msgInputShow)
    // this.socialPageProvide.msgInputShow = false
  }
}
</script>
