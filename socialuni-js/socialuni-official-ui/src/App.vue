<template>
  <RouterView/>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator';
import socialuniAppRequest from "socialuni-app-api/src/socialuniAppRequest.ts";

@toNative
@Component({
  components: {}
})
export default class App extends Vue {
  created() {
    socialuniAppRequest.get('socialuni/official/statistics')
  }
}
</script>
