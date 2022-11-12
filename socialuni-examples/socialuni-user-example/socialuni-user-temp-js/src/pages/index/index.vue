<template>
  <view class="content">
    <image class="logo" src="/static/logo.png" />
    <view class="text-area">
      <text class="title" @click="haha">{{ title }}</text>
    </view>
  </view>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'

@Options({
})
export default class IndexPage extends Vue {
  title = 'asdf'

  haha(){
    console.log(123)
    console.log(333)
  }
}
</script>
