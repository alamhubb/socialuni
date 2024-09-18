<template>
  <div
      class="u-loading-icon"
  >
    <!--				'animation-timing-function': 'ease-in-out'-->
    <div
        class="u-loading-icon__spinner u-loading-icon__spinner--circle bd-round"
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


@Component({})
class QLoading extends Vue {
}
export default toNative(QLoading)
</script>
<style lang="scss">
.u-loading-icon {
  flex-direction: row;
  align-items: center;
  justify-content: center;
  color: black;

  &__spinner {
    width: 24px;
    height: 24px;
    animation: u-rotate 1s linear infinite;
  }

  &__spinner--circle {
    border-width: 2px;
    border-color: #909399 #e9e9eb #e9e9eb;
    border-style: solid;
  }
}

@keyframes u-rotate {
  0% {
    transform: rotate(0deg)
  }

  to {
    transform: rotate(1turn)
  }
}
</style>
