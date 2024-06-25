<template>
  <div class="h100p overflow-hidden bg-default socialuni-app-h5">
    <RouterView/>
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
import WebsocketUtil from "socialuni-api-base/src/websocket/WebsocketUtil";
import AdminUserAPI from "socialuni-admin-api/src/api/AdminUserAPI";

console.log(123123)

@toNative
@Component({
  components: {SUserNavBar, QNavMenu, QButton, QInput, QScroll, QDialog}
})
export default class App extends Vue {
  created(){
    SocialuniUserEventOn.toLogin(() => {
      this.toLogin()
    })
    SocialuniUserEventOn.loginSuccess(async () => {
      console.log('dingyuechenggong')

      this.toHome()
    })
    SocialuniUserEventOn.loginOut(() => {
      this.toHome()
    })
  }
  mounted() {
    console.log('chufale')
    console.log('222')

  }
  toLogin(){
    this.$router.push('/login')
  }


  toHome() {
    this.$router.push('/')
  }

  get menus() {
    return constantRoutes
  }
}
</script>
<style>
.socialuni-navbar-h5 .el-menu-item {
  font-size: 18px;
}
</style>
