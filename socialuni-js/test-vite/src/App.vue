<template>
  <div class="h100p overflow-hidden flex-col bg-default socialuni-app-h5">
    123
    {{ typeIocContainer }}
    {{ b }}
    <button @click="printa"> click</button>
  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator';
import B, {type TTTTAaaa} from "@/plugins/TypeIoc/Testaa.ts";
import typeIocContainer from "@/plugins/TypeIoc/TypeIocContainer";
import TestSerivce from "@/plugins/TestSerivce";


@toNative
@Component({
  components: {}
})
export default class App extends Vue {


  b: TTTTAaaa = new B()
  d: TTTTAaaa = new TestSerivce()

  created() {
    console.log(324653)
    const test = new TestSerivce()
    console.log(test.testA)
    test.test()
  }

  printa() {
    const test: TestSerivce = typeIocContainer.containerGet('TestSerivce')
    console.log(test)
    test.test()
  }


  get typeIocContainer() {
    return typeIocContainer
  }
}
</script>
<style>
.socialuni-navbar-h5 .el-menu-item {
  font-size: 18px;
}
</style>
