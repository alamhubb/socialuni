<template>
  <div class="h100p overflow-hidden flex-col bg-default">
    <nav-bar/>
    <div class="flex-1 overflow-hidden row-center">
      <div class="w1000 overflow-hidden flex-row">
        <nav-menu class="w150 flex-none"/>
        <RouterView/>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator';
import NavBar from "@/layout/NavBar.vue";
import NavMenu from '@/layout/NavMenu.vue'

@toNative
@Component({
  components: {NavBar, NavMenu}
})
export default class App extends Vue {
}
</script>
