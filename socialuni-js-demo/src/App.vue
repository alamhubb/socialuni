<script setup lang="ts">
import {onError, onLaunch} from "@dcloudio/uni-app";

onLaunch(() => {
  console.log('App Launch')
})

onError(() => {
  console.log('Arrr')
})
</script>

<style lang="scss">
@import "socialuni/styles/index.scss";
</style>
