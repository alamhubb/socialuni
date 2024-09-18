<template>
  <RouterView/>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator';
import socialuniAppRequest from "socialuni-app-api/src/socialuniAppRequest.ts";
import SocialuniOfficialAPI from "@/api/SocialuniOfficialAPI.ts";


@Component({
  components: {}
})
class App extends Vue {
  created() {
    SocialuniOfficialAPI.statisticsAPI()
  }
}
export default toNative(App)
</script>
