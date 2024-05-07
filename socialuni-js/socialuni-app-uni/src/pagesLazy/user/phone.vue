<template>
  <login-view class="h100p"></login-view>
</template>


<script lang="ts">
import {Component, toNative, Vue} from 'vue-facing-decorator'

import LoginView from "socialuni-user-view-uni/src/views/login/LoginView.vue";

@toNative
@Component({
  components: {
    LoginView
  }
})
export default  class PhonePage extends Vue {
}
</script>
