<template>
  <social-login-page></social-login-page>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import SocialLoginPage from 'socialuni/components/SocialPages/SocialLoginPage.vue'

@Component({
  components: { SocialLoginPage }
})
export default class LoginPage extends Vue {
}
</script>
