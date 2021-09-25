<template>
  <social-login></social-login>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import SocialLogin from '@/socialuni/components/SocialLogin/SocialLogin.vue'

@Component({
  components: {
    SocialLogin
  }
})
export default class SocialLoginPage extends Vue {
}
</script>
