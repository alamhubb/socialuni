<template>
  <div class="h100p overflow-hidden flex-col bg-default">
    <s-nav-bar title="清池社区"/>
    <div class="flex-1 overflow-hidden">
      <RouterView/>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-facing-decorator';
import SNavBar from "socialuni-user-view-h5/src/components/SNavBar.vue";

@Component({
  components: {SNavBar}
})
export default class App extends Vue {
}
</script>
