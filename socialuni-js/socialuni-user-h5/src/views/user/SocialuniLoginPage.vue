<template>
  <div>
      <socialuni-login-view @login-success="loginSuccess"></socialuni-login-view>
  </div>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-facing-decorator'
import SocialuniLoginView from "socialuni-user-view-h5/src/views/SocialuniLoginView.vue";

@Component({
  components: {SocialuniLoginView}
})
export default class SocialuniLoginPage extends Vue {

}
</script>
