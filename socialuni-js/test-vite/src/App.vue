<template>
  <div class="h100p overflow-hidden flex-col bg-default socialuni-app-h5">
    123
    {{ typeIocContainer }}
    {{ b }}
    <button @click="printa"> click</button>
  </div>
</template>

<script lang="ts">
import B, {type TTTTAaaa} from "@/plugins/TypeIoc/Testaa.ts";
import typeIocContainer from "@/plugins/TypeIoc/TypeIocContainer";
import TestSerivce from "@/plugins/TestSerivce";
import {Component, toNative, Vue} from "./plugins/vue-facing-decorator/src";


@Component({
  components: {}
})
class App extends Vue {
  b: TTTTAaaa = new B()
  d: TTTTAaaa = new TestSerivce()

  created() {
    console.log(77777)
    const test = new TestSerivce()
    console.log(test.testA)
    test.test()
    console.log(8888)
    test.test('dad')
  }

  printa() {
    // const test: TestSerivce = typeIocContainer.containerGet('TestSerivce')
    console.log(test)
    test.test()
    test.test('dad')
  }


  get typeIocContainer() {
    return typeIocContainer
  }
}
export default toNative(App)
</script>
<style>
.socialuni-navbar-h5 .el-menu-item {
  font-size: 18px;
}
</style>
