<template>
  <div class="h100p overflow-hidden flex-col bg-default socialuni-app-h5">
    <s-user-nav-bar></s-user-nav-bar>

    <div class="flex-1 overflow-hidden flex-row">
      <q-nav-menu class="w150"></q-nav-menu>
      <RouterView/>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator';
import SUserNavBar from "socialuni-user-view-h5/src/components/SUserNavBar.vue";
import QNavMenu from "qing-ui-h5/src/components/QNavMenu.vue";
import {constantRoutes} from "@/router/router.ts";
import CommonEventUtil from "qingjs/src/util/CommonEventUtil.ts";
import QScroll from "qing-ui/src/components/QScroll.vue";
import QButton from "qing-ui/src/components/QButton.vue";
import QInput from "qing-ui/src/components/QInput.vue";
import QDialog from "qing-ui-h5/src/components/QDialog.vue";
import CommunityEventConst from "socialuni-community-sdk/src/constant/CommunityEventConst.ts";
import SocialuniUserEventOn from "socialuni-user-sdk/src/event/SocialuniUserEventOn";

console.log(123123)

@toNative
@Component({
  components: {SUserNavBar, QNavMenu, QButton, QInput, QScroll, QDialog}
})
export default class Layout extends Vue {

  created(){
    SocialuniUserEventOn.toLogin(() => {
      this.toLogin()
    })
    SocialuniUserEventOn.loginSuccess(() => {
      this.toHome()
    })
  }

  toLogin(){
    this.$router.push('/login')
  }

  mounted() {
    console.log('chufale')
    console.log('222')

  }

  get menus() {
    return constantRoutes
  }

  scrollToLower() {
    console.log('gundongdao dibu le ')
    console.log(new Date().getTime())
    CommonEventUtil.emit('appScrollToLower')
  }

  emitTalkAdd() {
    CommonEventUtil.emit(CommunityEventConst.socialuniTalkAddEvent)
  }

  toHome() {
    this.$router.push('/')
  }
}
</script>
<style>
.socialuni-navbar-h5 .el-menu-item {
  font-size: 18px;
}
</style>
