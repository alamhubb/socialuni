<template>
  <social-login class="h100p"></social-login>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-property-decorator'
import SocialLogin from '@/components/SocialLogin/SocialLogin.vue'

@Component({
  components: {
    SocialLogin
  }
})
export default class SocialLoginPage extends Vue {
}
</script>
