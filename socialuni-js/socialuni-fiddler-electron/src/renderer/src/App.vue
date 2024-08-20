<template>
  <div>123</div>
  <div>456</div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator';

@toNative
@Component({
  components: {}
})
export default class App extends Vue {
}
</script>
