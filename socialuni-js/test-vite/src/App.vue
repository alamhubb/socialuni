<template>
  <div class="h100p overflow-hidden flex-col bg-default socialuni-app-h5">
    123
    <button @click="printa"> click</button>
  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator';
import TestSerivce from "./TestSerivce.ts";
import B, {TTTTAaaa} from "./Testaa.ts";
import typeIocContainer from "typeiocjs/src/TypeIocContainer.ts";
import {Service} from "typeiocjs/src/TypeIocDecorator.ts";

@Service
@Component({
  components: {}
})
class App extends Vue {
  b: TTTTAaaa = new B()
  d: TTTTAaaa = new TestSerivce()

  get test(): TestSerivce {
    return typeIocContainer.containerGet("D:/project/socialuni-all/socialuni_vip/socialuni-js/test-vite/src/TestSerivce.ts$TestSerivce")
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
