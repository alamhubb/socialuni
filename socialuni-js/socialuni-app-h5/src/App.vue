<template>
  <div class="h100p overflow-hidden flex-col bg-default">
    <!--        <nav-bar/>-->
    <div class="flex-1 overflow-hidden">
      <RouterView/>
    </div>
  </div>
</template>

<script lang="ts">
import {Component, toNative, Vue} from 'vue-facing-decorator';

console.log(456465)
console.log(Component)

@Component({
})
class App extends Vue {
}
export default toNative(App)
</script>
