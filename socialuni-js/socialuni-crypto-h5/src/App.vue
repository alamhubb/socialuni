<template>
  <div class="h100p overflow-hidden flex-col bg-default">
    <s-nav-bar title="维洛斯"/>
    <div class="flex-1 overflow-hidden">
      <RouterView/>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator';
import SNavBar from "socialuni-user-view-h5/src/components/SUserNavBar.vue";


@Component({
  components: {SNavBar}
})
class App extends Vue {
}
export default toNative(App)
</script>
