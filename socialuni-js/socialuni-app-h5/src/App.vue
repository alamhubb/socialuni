<template>
  <div class="h100p overflow-hidden flex-col bg-default">
    <!--        <nav-bar/>-->
    <div class="flex-1 overflow-hidden">
      <RouterView/>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator';

@toNative
@Component({
})
export default class App extends Vue {
}
</script>
