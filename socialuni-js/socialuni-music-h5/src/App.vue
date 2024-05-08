<template>
  <div class="h100p overflow-hidden flex-col bg-default">
    <s-user-nav-bar></s-user-nav-bar>

    <div class="overflow-auto row-center">
      <q-nav-menu class="w150 flex-none br position-sticky top-0" :menus="menus"/>
      <div class="w1000">
        <RouterView/>
      </div>
    </div>
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

@toNative
@Component({
  components: {SUserNavBar, QNavMenu}
})
export default class App extends Vue {


  get menus() {
    return constantRoutes
  }
}
</script>
