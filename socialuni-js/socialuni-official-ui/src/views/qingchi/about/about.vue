<template>
  <v-container class="h100p pt-0 pb-15">
    <div class="markdown-body">
      <q-markdown :text="markdown"></q-markdown>

      <v-card
          class="w100r pb-10"
          flat
          tile
      >
        <v-card-actions>
          <div class="w100r flex-row overflow-auto">
            <a class="mr-5" target="_blank"
               href="https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MzI1OTcxNTg5Mg==&scene=124#wechat_redirect">
              <img class="ma-2 size180"
                   :src="require('@/assets/qingchi_gzh_code.jpg')">
              <div class="row-center w190px grey--text body-2">
                扫码或长按图片识别进群
              </div>
              <div class="row-center w190px">
                清池app开发日记公众号
              </div>
            </a>
            <a class="mr-5" target="_blank"
               href="https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/wx_group_code.png!thumbnail">
              <img class="ma-2 size180"
                   :src="require('@/assets/wx_group_code.png')">
              <div class="row-center w190px  grey--text body-2">
                扫码或长按图片识别进群
              </div>
              <div class="row-center w190px">
                清池app官方恋爱交友群
              </div>
            </a>
            <a class="mr-5" target="_blank"
               href="https://a.app.qq.com/o/simple.jsp?pkgname=com.qingchiapp">
              <img class="ma-2 size180"
                   :src="require('@/assets/qingchiappcode.png')">
              <div class="row-center w190px grey--text body-2">
                点击或长按图片识别下载
              </div>
              <div class="row-center w190px">
                清池app下载
              </div>
            </a>
            <a target="_blank"
               href="https://mp.weixin.qq.com/mp/waerrpage?type=lowest_version&appid=wx0bf5fe9ceeab514c&uin=MTU0NzIwOTU1&key=5dd492e10b98b5a21a6fb86af214079b588d6b15085fd4899babee3532d7d300a83b6d4b23cf7af2817687a76e21105215dd299ab3df9fd5335f7bb36299ffb5cfb808c851ea76960945d50244c1e79e&devicetype=Windows+10+x64&version=62090070&lang=zh_CN&ascene=1&pass_ticket=%2B6%2BVIm1E8wYnzkE1yZZyH%2B4yQJXPIGgcFMFRgUHPD4E%3D">
              <img class="size200 bd-radius10px"
                   :src="require('@/assets/wx_app_code.jpg')">
              <div class="row-center w190px  grey--text body-2">
                微信扫码识别或点击
              </div>
              <div class="row-center">
                微信小程序
              </div>
            </a>
          </div>
        </v-card-actions>
      </v-card>
    </div>
  </v-container>
</template>

<script lang="ts">
import {
  Vue,
  Component,
} from 'vue-facing-decorator'

//@ts-ignore
import QMarkdown from 'qing-markdown/src/components/q-markdown/q-markdown'
//@ts-ignore
import gzhInfo from './about.md'

@toNative
@Component({
  components: {
    QMarkdown
  }
})
export default class AboutVue extends Vue {
  markdown = gzhInfo
}
</script>
<style>
.markdown-body {
  box-sizing: border-box;
  min-width: 200px;
  max-width: 980px;
  margin: 0 auto;
}
</style>
