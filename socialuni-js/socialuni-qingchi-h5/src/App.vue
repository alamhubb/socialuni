<template>
  <div class="h100p overflow-hidden flex-col bg-default">
    <s-nav-bar title="清池"/>
    <div class="flex-1 overflow-hidden">
      <RouterView/>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, toNative, Vue} from 'vue-facing-decorator';
import SNavBar from "socialuni-user-view-h5/src/components/SUserNavBar.vue";

@toNative
@Component({
  components: {SNavBar}
})
export default class App extends Vue {
}
</script>
