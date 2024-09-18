<template>
  <div class="h100p overflow-hidden flex-col bg-default socialuni-app-h5">
    123
    <button @click="printa"> click</button>
  </div>
</template>

<script lang="ts">
import B, {type TTTTAaaa} from "@/plugins/TypeIoc/Testaa.ts";
import typeIocContainer from "@/plugins/TypeIoc/TypeIocContainer";
import TestSerivce from "@/TestSerivce.ts";
import {Component, toNative, Vue} from "./plugins/vue-facing-decorator/src";


@Component({
  components: {}
})
class App extends Vue {
  b: TTTTAaaa = new B()
  d: TTTTAaaa = new TestSerivce()

  get test(): TestSerivce {
    return typeIocContainer.containerGet(TestSerivce.name)
  }

  created() {
    console.log(77777)
    console.log(this.test.testA)
    // test.test()
  }

  printa() {
    this.test.test()
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
