<template>
    <div>
      覆盖页面123
    </div>
</template>

<script lang="ts">
import { Component, Vue , toNative} from 'vue-facing-decorator';

@toNative
@Component({})
export default class ExpandView extends Vue {

  created(){
  }

}
</script>
