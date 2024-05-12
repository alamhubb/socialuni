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
import {Component, Vue, Watch, toNative} from 'vue-facing-decorator'
@toNative
@Component({
  components: {

  }
})
export default class QLoadMore extends Vue {
}
</script>
<style lang="scss">
.u-loading-icon {
  flex-direction: row;
  align-items: center;
  justify-content: center;
  color: black;

  &__spinner {
    width: 1px;
    height: 1px;
    position: relative;
    box-sizing: border-box;
    max-width: 1px;
    max-height: 1px;
    animation: u-rotate 1s linear infinite;
  }

  &__spinner--circle {
    border: 1px solid black;
  }
}
</style>
