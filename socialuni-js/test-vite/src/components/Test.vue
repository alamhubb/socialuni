<template>
  <div>

  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator';
import B, {type TTTTAaaa} from "@/plugins/TypeIoc/Testaa.ts";
import typeIocContainer, {Resource, Service} from "@/plugins/TypeIoc/TypeIocContainer";

@Service
@toNative
@Component({
  components: {}
})
export default class TestView extends Vue {


  b: TTTTAaaa = new B()


  obj: TTTTAaaa

}
</script>
