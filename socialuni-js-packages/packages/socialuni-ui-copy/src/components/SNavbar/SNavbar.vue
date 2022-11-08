<template>
  <view class="w100%">
    <view class="w100% position-fixed nav-index bg-default" :class="customClass">
      <!--            此处为状态栏-->
      <view class="w100%" :style="{ height: statusBarHeight + 'px' }"></view>
      <!--            此处为导航栏-->
      <view class="row-col-center px-sm" :style="[navbarInnerStyle]">
        <view @click="goBack">
          <slot name="back">
            <s-icon v-if="showBack" icon="arrow-left" class="color-th mr"></s-icon>
          </slot>
        </view>
        <!--        <s-icon v-if="showHome" icon="home" class="color-th mr" @click="goHome"></s-icon>-->
        <slot></slot>
      </view>
    </view>

    <!-- 解决fixed定位后导航栏塌陷的问题 -->
    <view v-if="stance" :style="{width: '100%',height: titleHeight + 'px'}">
    </view>
  </view>
</template>

<script setup lang="ts">
import {computed} from "vue";
import RouterUtil from "socialuni-use/src/utils/RouterUtil";
import SIcon from "../SIcon/SIcon.vue";
import {socialSystemModule} from "socialuni-sdk/src/store/store";
import PageUtil from "socialuni-common/src/utils/PageUtil";

const statusBarHeight = socialSystemModule.statusBarHeight
const navBarHeight = socialSystemModule.navBarHeight
const titleHeight = socialSystemModule.titleHeight

const props = defineProps({
  customClass: String,
  showBack: {
    type: Boolean,
    default: false
  },
  showHome: {
    type: Boolean,
    default: false
  },
  stance: {
    type: Boolean,
    default: false
  },
})

// 如果是小程序，获取右上角胶囊的尺寸信息，避免导航栏右侧内容与胶囊重叠(支付宝小程序非本API，尚未兼容)
// #ifdef MP
const menuButtonInfo: GetMenuButtonBoundingClientRectRes = uni.getMenuButtonBoundingClientRect()
// #endif
const navbarInnerStyle = computed(() => {
  const style: any = {}
  // 导航栏宽度，如果在小程序下，导航栏宽度为胶囊的左边到屏幕左边的距离
  style.height = navBarHeight + 'px'
  // // 如果是各家小程序，导航栏内部的宽度需要减少右边胶囊的宽度
  // #ifdef MP
  //右侧margin为胶囊宽度+15
  let rightButtonWidth = menuButtonInfo.width + 15
  // #ifdef MP-QQ
  //qq时额外15，因为qq开发者工具调试时数值有问题
  rightButtonWidth = 100
  // #endif
  style.marginRight = rightButtonWidth + 'px'
  // #endif
  return style
})

function goBack() {
  PageUtil.goBackOrHome()
}
</script>
