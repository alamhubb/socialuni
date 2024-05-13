<template>
  <div class="h100p overflow-hidden flex-col bg-default">
    <s-user-nav-bar>
      <div class="row-end-center h100p mr">
        <q-button primary @click="emitTalkAdd">发帖</q-button>
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
import QNavMenu from "qing-ui-h5/src/components/QNavMenu.vue";
import {constantRoutes} from "@/router";
import CommonEventUtil from "qingjs/src/util/CommonEventUtil.ts";
import QScroll from "qing-ui/src/components/QScroll.vue";
import QButton from "qing-ui/src/components/QButton.vue";
import CommonUtil from "qing-util/src/util/CommonUtil.ts";
import QDialog from "qing-ui-h5/src/components/QDialog.vue";
import CommunityEventConst from "socialuni-community-sdk/src/constant/CommunityEventConst.ts";

@toNative
@Component({
  components: {SUserNavBar, QNavMenu, QButton, QScroll, QDialog}
})
export default class App extends Vue {

  mounted() {
    console.log('chufale')
    console.log('222')
    CommonUtil.delayTime(100).then(() => {
      console.log('chufale')
      console.log('333')
      // this.defaultActive = '/expand'
      this.$router.push('/community')
    })
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
</script>
