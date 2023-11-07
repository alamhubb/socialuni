<template>
  <div class="h100p bg-white px">
    <div class="use-click px-sm hover-theme_light q-box bd-round">
      首页
    </div>
    <div class="use-click px-sm hover-theme_light q-box bd-round" @click="toChatView">
      聊天室
    </div>
    <div class="use-click px-sm hover-theme_light q-box bd-round">
      晒自拍
    </div>
  </div>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-facing-decorator'
import PageUtil from '@socialuni/socialuni-util/src/util/PageUtil'
import ImPageUtil from '@socialuni/socialuni-im-sdk/src/util/ImPageUtil'
import RouterUtil from '@socialuni/socialuni-util/src/util/RouterUtil'
import ImPagePath from '@socialuni/socialuni-im-sdk/src/constant/ImPagePath'

@Component({})
export default class LeftWindowView extends Vue {

  menus = ['首页','聊天室','晒自拍']


  toChatView(){
    RouterUtil.switchTab(ImPagePath.chatView)
  }

  created(){
    console.log(5767868)
  }

}
</script>
