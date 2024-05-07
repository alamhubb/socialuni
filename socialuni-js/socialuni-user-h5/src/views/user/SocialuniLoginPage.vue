<template>
  <div>
    <socialuni-login-view @login-success="loginSuccess"></socialuni-login-view>
  </div>
</template>

<script lang="ts">
import {Component, toNative, Vue} from 'vue-facing-decorator'
import SocialuniLoginView from "socialuni-user-view-h5/src/views/SocialuniLoginView.vue";
import QingAppUtil from "qingjs/src/util/QingAppUtil.ts";

@toNative
@Component({
  components: {SocialuniLoginView}
})
export default class SocialuniLoginPage extends Vue {
  loginSuccess(res) {
    console.log(res)
    window.parent.postMessage(res, '*')
    console.log('fasongxiaoxile ')

    QingAppUtil.ToastUtil.success('登录成功')
    this.$refs.loginDialog.close()
  }
}
</script>
