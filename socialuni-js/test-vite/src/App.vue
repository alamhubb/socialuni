<template>
  <div class="h100p overflow-hidden flex-col bg-default socialuni-app-h5">
    123
  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator';
import B from "@/plugins/TypeIoc/Testaa.ts";

@toNative
@Component({
  components: {}
})
export default class App extends Vue {


  mounted() {
    console.log(B.name)
  }
}
</script>
<style>
.socialuni-navbar-h5 .el-menu-item {
  font-size: 18px;
}
</style>
