<template>
  <div>
    <slot></slot>
  </div>
</template>

<script lang="ts">
import {Component, Vue, Watch, toNative} from 'vue-facing-decorator';
import 'golden-layout/dist/css/goldenlayout-base.css';
import 'golden-layout/dist/css/themes/goldenlayout-light-theme.css';


@Component({
  components: {}
})
class VueGoldenLayoutColumn extends Vue {
  layoutType = 'column'
}
export default toNative(VueGoldenLayoutColumn)
</script>
