<template>
  <div class="h100p overflow-hidden flex-col bg-default socialuni-app-h5">
    <!--        <s-user-nav-bar title="鹿森">-->
    <s-user-nav-bar ref="navbar">
      <template #title>
        <div class="flex-1 row-col-center overflow-x-hidden">
          <div class="w100p row-end-center pr">
            <img :src="$getImageUrl('logo.jpg')" class="ml-50 h44 mr-sm cursor-pointer flex-none" alt="logo"
                 @click="toHome">
            <div class="font-22 font-bold cursor-pointer flex-none mr" @click="toHome">鹿森</div>
            <q-input class="flex-none w150"></q-input>
            <!--            <div class="w200">bb</div>-->
          </div>
        </div>
      </template>

      <div class="w600 flex-none row-col-center h100p mr overflow-hidden">
        <!--        <div class="bg-theme">123</div>-->
        <q-nav-menu class="socialuni-navbar-h5 w700" mode="horizontal"></q-nav-menu>
        <q-button class="flex-none" primary @click="emitTalkAdd">发帖</q-button>
      </div>
    </s-user-nav-bar>

    <div class="flex-1 overflow-hidden">
      <RouterView/>
    </div>
    <!--  <div class="h100p overflow-hidden flex-col bg-default">
        <s-user-nav-bar>

          <div class="row-end-center h100p mr">
          </div>
        </s-user-nav-bar>-->


    <!--    <div class="flex-1 overflow-hidden" v-infinite-scroll="scrollToLower" :infinite-scroll-immediate="false"
             :infinite-scroll-distance="200"
             :infinite-scroll-delay="2000">
          <RouterView/>
        </div>-->
    <!--    <div class="flex-1 overflow-auto">-->
    <!--      <div class="mg-x-auto w1200">-->
    <!--        <RouterView/>-->
    <!--      </div>-->
    <!--          <q-nav-menu class="h100p w200 flex-none br position-sticky top-0" :menus="menus"/>-->
    <!--    </div>-->
    <!--  </div>-->
    <!--    <div class="row-center overflow-auto">
          <div class="w1000 flex-row overflow-hidden">
            <q-nav-menu class="w150 flex-none br" :menus="menus"/>
            <div class="flex-1 overflow-hidden">
              <RouterView/>
            </div>
          </div>
        </div>-->
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
import SocialuniUserEventOn from "socialuni-user-sdk/src/event/SocialuniUserEventOn.ts";

@toNative
@Component({
  components: {SUserNavBar, QNavMenu, QButton, QInput, QScroll, QDialog}
})
export default class App extends Vue {
  $refs: {
    navbar: SUserNavBar
  }

  created() {
    SocialuniUserEventOn.toLogin(() => {
      this.$refs.navbar.openLoginDialog()
    })
  }

  mounted() {
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
