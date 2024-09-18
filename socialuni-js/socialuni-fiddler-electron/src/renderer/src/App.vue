<template>
  <div>123</div>
  <div>456</div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator';


@Component({
  components: {}
})
class App extends Vue {
}
export default toNative(App)
</script>
