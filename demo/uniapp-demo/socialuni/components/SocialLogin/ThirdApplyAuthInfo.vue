<template>
  <!--      底部客服信息-->
  <view class="flex-1">
    <!--            三方授权显示三方的信息-->
    <view class="row-col-center px-sm py-xs" v-if="threeDevUser">
      <u-avatar class="mr-sm" :src="threeDevUser.avatarUrl" mode="square" size="50"></u-avatar>
      <text class="mr-sm font-bold">{{ threeDevUser.appName }}</text>
      申请
    </view>

    <template v-if="isThreeAuth">
      <!--    如果授权用户信息提示显示获取用户信息-->
      <template v-if="isAuthUser">
        <view class="font-bold row-col-center pd-sm text-md">
          获取您的昵称、头像、地区、性别及动态
        </view>
        <!-- 如果为三方授权， 不为手机号授权， 如果有用户显示出来用的信息-->
        <view v-if="user" class="flex-row pd-sm u-border-top-bottom h65px">
          <u-avatar class="mr-sm" :src="user.avatar" mode="square"></u-avatar>
          <view class="col-around flex-1">
            <view>{{ user.nickname }}</view>
            <view class="text-grey text-sm">微信个人信息</view>
          </view>
          <view class="col-center">
            <u-icon name="checkmark" color="success" size="40"></u-icon>
          </view>
        </view>
      </template>
      <template v-else-if="isAuthPhone">
        <!--            为授权手机号显示授权手机号-->
        <view class="font-bold row-col-center pd-sm text-md">
          获取您清池绑定的手机号
          <!--                如果有手机号显示-->
          <!--                <text>：186*****595</text>-->
          <text v-if="hasPhoneNum">：{{ user.phoneNum }}</text>
        </view>
      </template>
      <!--  如果没有用户，展示提示请登录-->
      <!--  如果为三方授权，用户授权，没有登录，则显示，上面这俩可以合并-->
      <view v-if="!user" class="pd-sm">
        您未登录，点击登录进行授权
      </view>
      <view v-else-if="isAuthPhone&&!hasPhoneNum" class="px-sm py-xs row-col-center">
        <!--                请点击绑定手机号进行授权-->
        您未在清池绑定手机号，点击绑定手机号授权
      </view>
    </template>
    <!--  如果三方授权，手机号授权，没有用户，没有手机号，提示-->
    <!--  请登录并绑定手机号后进行授权-->
  </view>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import UniUtil from 'socialuni/utils/UniUtil'
import ConfigMap from 'socialuni/const/ConfigMap'
import SkipUrlConst from 'socialuni/const/SkipUrlConst'
import { socialConfigStore } from 'socialuni/store'

@Component
export default class ThirdApplyAuthInfo extends Vue {
  @socialConfigStore.Getter(ConfigMap.qqServiceKey) qqService: string

  get homeUrl (): string {
    return SkipUrlConst.homeUrl()
  }

  copyServiceNum () {
    UniUtil.textCopy('491369310', '客服联系方式已复制')
  }
}
</script>
