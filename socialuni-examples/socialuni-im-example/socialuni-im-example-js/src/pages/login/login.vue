<template>
  <login-view class="h100p"></login-view>
</template>


<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import LoginView from "socialuni-user/src/views/login/LoginView";

@Options({
  components: {
    LoginView
  }
})
export default class LoginPage extends Vue {
}
</script>
