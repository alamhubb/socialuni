<template>
  <view class="w100p">
    <view class="w100p position-fixed nav-index" :class="customClass">
      <!--            此处为状态栏-->
      <view class="w100p" :style="{ height: statusBarHeight + 'px' }"></view>
      <!--            此处为导航栏-->
      <view class="row-col-center px-sm" :style="[navbarInnerStyle]">
        <div @click="goBack">
          <slot name="back">
            <q-icon v-if="showBack" icon="arrow-left" class="color-th mr"></q-icon>
          </slot>
        </div>
        <q-icon v-if="showHome" icon="home" class="color-th mr" @click="goHome"></q-icon>
        <slot></slot>
      </view>
    </view>

    <!-- 解决fixed定位后导航栏塌陷的问题 -->
    <view v-if="stance" :style="{width: '100%',height: titleHeight + 'px'}">
    </view>
  </view>
</template>

<script lang="ts">
import {Options, Prop, Vue} from 'vue-property-decorator'
import GetMenuButtonBoundingClientRectRes = UniApp.GetMenuButtonBoundingClientRectRes;
import QIcon from "../QIcon/QIcon.vue";
import {socialSystemModule} from "socialuni-sdk/src/store/store";
import PageUtil from "../socialuni-js-packages/packages/socialuni-common/src/utils/PageUtil";

// 如果是小程序，获取右上角胶囊的尺寸信息，避免导航栏右侧内容与胶囊重叠(支付宝小程序非本API，尚未兼容)
// #ifdef MP
const menuButtonInfo: GetMenuButtonBoundingClientRectRes = uni.getMenuButtonBoundingClientRect()
// #endif
/*
显示出来已经选了的城市，给她画上钩
* */
@Options({
  components: {QIcon}
})
export default class QNavBar extends Vue {
  @Prop() customClass: string
  @Prop({
    type: Boolean,
    default: false
  }) showBack: boolean
  @Prop({
    type: Boolean,
    default: false
  }) showHome: boolean

  @Prop({
    type: Boolean,
    default: true
  }) stance: boolean

  get statusBarHeight() {
    return socialSystemModule.statusBarHeight
  }

  get navBarHeight() {
    return socialSystemModule.navBarHeight
  }

  get titleHeight() {
    return socialSystemModule.titleHeight
  }

  get navbarInnerStyle() {
    const style: any = {}
    // 导航栏宽度，如果在小程序下，导航栏宽度为胶囊的左边到屏幕左边的距离
    style.height = this.navBarHeight + 'px'
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
  }

  goBack() {
    PageUtil.goBackOrHome()
  }

  goHome() {
    PageUtil.goHome()
  }
}
</script>
