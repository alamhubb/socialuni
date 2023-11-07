<template>
  <login-view class="h100p"></login-view>
</template>


<script lang="ts">
import {Component, Vue} from 'vue-facing-decorator'

import LoginView from "@socialuni/socialuni-user-view-uni/src/views/login/LoginView.vue";
@Component({
  components: {
    LoginView
  }
})
export default class PhonePage extends Vue {
}
</script>
