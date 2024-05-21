<template>
  <div class="h100p overflow-hidden bg-default socialuni-app-h5">
    <RouterView/>
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

console.log(123123)

@toNative
@Component({
  components: {SUserNavBar, QNavMenu, QButton, QInput, QScroll, QDialog}
})
export default class App extends Vue {

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
