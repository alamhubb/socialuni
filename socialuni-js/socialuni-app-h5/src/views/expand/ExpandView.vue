<template>
    <div>
      覆盖页面123
    </div>
</template>

<script lang="ts">
import { Component, Vue , toNative} from 'vue-facing-decorator';


@Component({})
class ExpandView extends Vue {

  created(){
  }

}
export default toNative(ExpandView)
</script>
