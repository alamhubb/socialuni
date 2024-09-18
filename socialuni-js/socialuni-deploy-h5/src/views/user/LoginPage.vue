<template>
  <div class="h100p">
    <iframe class="h100p w100p bd-none" src="http://localhost:5173/user"></iframe>
  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator';
import QingAppUtil from "qing-compat-js/src/util/QingAppUtil.ts";
import WindowEventListener from "qing-util/src/util/WindowEventListener.ts";


@Component({
  components: {}
})
class LoginPage extends Vue {
  loginSuccess() {
    // QingAppUtil.ToastUtil.success('登录成功')
    // this.$refs.loginDialog.close()
  }

  created() {
    WindowEventListener.useMessageListener('http://localhost:5173', (event: MessageEvent) => {
      console.log(event)
      if (event.data === 'loginSuccess') {
        QingAppUtil.ToastUtil.success('登录成功')
      }
    })
  }
}
export default toNative(LoginPage)
</script>
