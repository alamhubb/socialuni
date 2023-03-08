<template>
  <div class="h100p bg-white px-sm">
    <div>
      首页
    </div>
    <div>
      聊天室
    </div>
    <div>
      晒自拍
    </div>
  </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-property-decorator'
import SocialuniCoinView from 'socialuni-view/src/views/user/coin/SocialuniCoinView.vue'

@Options({})
export default class LeftWindowView extends Vue {
}
</script>
