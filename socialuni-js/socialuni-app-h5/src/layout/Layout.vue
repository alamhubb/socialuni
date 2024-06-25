<template>
  <div class="h100p overflow-hidden flex-col bg-default">
    <s-user-nav-bar></s-user-nav-bar>

    <!--    <div class="flex-1 overflow-hidden">-->
    <!--           :infinite-scroll-delay="500" -->
    <!--      <div class="h100p overflow-auto row-center"-->
    <div class="flex-1 overflow-auto row-center"
         v-infinite-scroll="scrollToLower" :infinite-scroll-immediate="false" :infinite-scroll-distance="200" :infinite-scroll-delay="2000"
    >
      <q-nav-menu class="w150 flex-none br position-sticky top-0" :menus="menus"/>
      <div class="w1000">
        <RouterView/>
      </div>
    </div>
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

@toNative
@Component({
  components: {SUserNavBar, QNavMenu, QScroll}
})
export default class App extends Vue {


  get menus() {
    return constantRoutes
  }

  scrollToLower() {
    console.log('gundongdao dibu le ')
    console.log(new Date().getTime())
    // CommonEventUtil.emit('appScrollToLower')
  }
}
</script>
