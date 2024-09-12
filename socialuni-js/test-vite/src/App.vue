<template>
  <div class="h100p overflow-hidden flex-col bg-default socialuni-app-h5">
    123
    {{ typeIocContainer }}
    {{ b }}
  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator';
import B, {type TTTTAaaa} from "@/plugins/TypeIoc/Testaa.ts";
import typeIocContainer, {Resource, Service} from "@/plugins/TypeIoc/TypeIocContainer";
import TestView from "@/components/Test.vue";
import TestSerivce from "@/plugins/TestSerivce";


@toNative
@Component({
  components: {}
})
export default class App extends Vue {


  b: TTTTAaaa = new B()

  created() {
    const test = new TestSerivce()
    // test.test()
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
