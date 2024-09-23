<template>
  <main class="h100p mt-sm row-all-center">

  </main>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator';

@Component({})
class HomeView extends Vue {

  people: People = null

  created() {
  }


}
export default toNative(HomeView)
</script>
