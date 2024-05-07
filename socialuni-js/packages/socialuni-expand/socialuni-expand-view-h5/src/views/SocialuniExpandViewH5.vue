<template>
  <div class="h100p">

  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'
import SScrollbar from "qing-ui-h5/src/components/QComponents/QScrollbar.vue";

@toNative
@Component({Components: {SScrollbar}})
export default class SocialuniExpandViewH5 extends Vue {


}
</script>
