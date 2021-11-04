<template>
    <view class="bd bd-round row-col-center h30">
        <slot></slot>
    </view>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'

/*
显示出来已经选了的城市，给她画上钩
* */
  @Component
export default class QSearch extends Vue {

}
</script>
