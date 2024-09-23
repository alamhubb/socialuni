<template>
  <div>

  </div>
</template>

<script lang="ts">
import {Component, toNative, Vue} from "vue-facing-decorator";
import {TTTTAaaa} from "../ioc/Testaa.ts";
import {TypeIocService} from "typeiocjs/src/TypeIocDecorator.ts";

@TypeIocService
@Component({
  components: {}
})
class TestView extends Vue {


  b: TTTTAaaa = new B()


  obj: TTTTAaaa

}
export default toNative(TestView)
</script>
