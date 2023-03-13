<template>
  <login-view class="h100p"></login-view>
</template>


<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import LoginView from "socialuni-view/src/views/login/LoginView.vue";
@Options({
  components: {
    LoginView
  }
})
export default class PhonePage extends Vue {
}
</script>
