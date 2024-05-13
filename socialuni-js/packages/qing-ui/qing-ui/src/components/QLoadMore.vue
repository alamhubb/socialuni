<template>
  <div
      class="u-loading-icon"
  >
    <!--				'animation-timing-function': 'ease-in-out'-->
    <div
        class="u-loading-icon__spinner u-loading-icon__spinner--circle"
        :style="{
				'animation-duration': `1000ms`,
				'animation-timing-function': 'linear'
			}"
    >
    </div>
  </div>
</template>

<script lang="ts">
import { Emit, Component, Prop, Vue , toNative} from 'vue-facing-decorator'

@toNative
@Component({})
export default class QLoadMore extends Vue {
}
</script>
