<template>
  <div>

  </div>
</template>

<script lang="ts">
import B, {type TTTTAaaa} from "@/plugins/TypeIoc/Testaa.ts";
import typeIocContainer, {Resource, Service} from "@/plugins/TypeIoc/TypeIocContainer";

@Service
@Component({
  components: {}
})
class TestView extends Vue {


  b: TTTTAaaa = new B()


  obj: TTTTAaaa

}
export default toNative(TestView)
</script>
