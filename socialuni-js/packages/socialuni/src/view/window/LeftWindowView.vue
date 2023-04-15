<template>
  <div class="h100p bg-white px">
    <div v-for="menu in menus" class="use-click px-sm hover-theme_light q-box bd-round">
      {{menu}}
    </div>
  </div>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'

@Options({})
export default class LeftWindowView extends Vue {

  menus = ['首页','聊天室','晒自拍']
}
</script>
