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
import {Resource, Service} from "typeiocjs/src/TypeIocDecorator.ts";

@Service
@Component({
  components: {}
})
class App extends Vue {
  b: TTTTAaaa = new B()
  e: TestSerivce = new TestSerivce()
  d: TestSerivce = null

  get test(): TestSerivce {
    return typeIocContainer.containerGet("D:/project/socialuni-all/socialuni_vip/socialuni-js/test-vite/src/TestSerivce.ts$TestSerivce")
  }

  created() {
    console.log(77777)
    console.log(this.d)
    this.d = typeIocContainer.containerGet("D:/project/socialuni-all/socialuni_vip/socialuni-js/test-vite/src/TestSerivce.ts$TestSerivce")
    // this.d.eat()
    // test.test()
  }

  printa() {
    // this.d.eat()
    console.log(typeIocContainer)
    this.d = typeIocContainer.containerGet("D:/project/socialuni-all/socialuni_vip/socialuni-js/test-vite/src/TestSerivce.ts$TestSerivce")
    console.log(this.d)
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
