<template>
  <login-view class="h100p"></login-view>
</template>


<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'

import LoginView from "socialuni-user-view-uni/src/views/login/LoginView.vue";


@Component({
  components: {
    LoginView
  }
})
class PhonePage extends Vue {
}
export default toNative(PhonePage)
</script>
