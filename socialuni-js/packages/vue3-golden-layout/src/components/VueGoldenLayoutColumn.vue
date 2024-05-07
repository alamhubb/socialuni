<template>
  <div>
    <slot></slot>
  </div>
</template>

<script lang="ts">
import {Component, toNative, Vue, Watch} from 'vue-facing-decorator';
import 'golden-layout/dist/css/goldenlayout-base.css';
import 'golden-layout/dist/css/themes/goldenlayout-light-theme.css';

@toNative
@Component({
  components: {}
})
export default class VueGoldenLayoutColumn extends Vue {
  layoutType = 'column'
}
</script>
