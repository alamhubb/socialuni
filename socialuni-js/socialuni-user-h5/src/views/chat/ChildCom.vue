<template>
  <div>
    <slot></slot>
  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator';
import 'golden-layout/dist/css/goldenlayout-base.css';
import 'golden-layout/dist/css/themes/goldenlayout-light-theme.css';


@Component({
  components: {}
})
class ChildCom extends Vue {
  layoutType = 'row'
}
export default toNative(ChildCom)
</script>
