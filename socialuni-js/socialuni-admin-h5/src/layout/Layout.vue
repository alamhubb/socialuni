<template>
  <div class="h100p overflow-hidden flex-col bg-default socialuni-app-h5">
    <s-user-nav-bar></s-user-nav-bar>

    <div class="flex-1 overflow-hidden flex-row">
      <q-nav-menu class="w150"></q-nav-menu>
      <div class="flex-1 overflow-auto">
        <RouterView/>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator';
import SUserNavBar from "socialuni-user-view-h5/src/components/SUserNavBar.vue";
import QNavMenu from "qingjs-ui-h5/src/components/QNavMenu.vue";
import {constantRoutes} from "@/router/router.ts";
import CommonEventUtil from "qing-compat-js/src/util/CommonEventUtil.ts";
import QScroll from "qingjs-ui/src/components/QScroll.vue";
import QButton from "qingjs-ui/src/components/QButton.vue";
import QInput from "qingjs-ui/src/components/QInput.vue";
import QDialog from "qingjs-ui-h5/src/components/QDialog.vue";
import CommunityEventConst from "socialuni-community-sdk/src/constant/CommunityEventConst.ts";
import SocialuniUserEventOn from "socialuni-user-sdk/src/event/SocialuniUserEventOn";

console.log(123123)


@Component({
  components: {SUserNavBar, QNavMenu, QButton, QInput, QScroll, QDialog}
})
class Layout extends Vue {

  toLogin(){
    this.$router.push('/login')
  }


  toHome() {
    this.$router.push('/')
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
}
export default toNative(Layout)
</script>
<style>
.socialuni-navbar-h5 .el-menu-item {
  font-size: 18px;
}
</style>
