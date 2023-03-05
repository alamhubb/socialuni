<template>
  <view class="bg-white">
    <div class="flex-row" v-if="user">
      <view class="row-col-center">
        <q-icon class="text-green" size="50" icon="mdi-bitcoin"/>
        <text class="ml-xs text-lg text-bold">
          贝壳余额：
        </text>
      </view>
      <text class="row-col-center text-lg text-bold">
        {{ user.shell }}
      </text>
    </div>
    <div class="flex-row">
      <text class="text-lg text-bold">
        充值：
      </text>
    </div>
    <view class="px">
      <u-subsection :list="payValues" :current="0" @change="switchAmount"></u-subsection>
      <view class="pt-sm">
        <view class="text-lg">
          提示：1元 = 10贝壳，可用于获取他人联系方式，一但交易成功，不支持退款，请谨慎付款
          <!--，并赠送与贝壳数量同等的等级积分。详细介绍请点 -
          <q-icon class="text-gray" icon="arrow-right" size="32"></q-icon>
          <text class="text-bold">贝壳介绍</text>-->
        </view>
      </view>
      <button class="bg-green-light text-white text-lg bd-none mt bg-active round" @click="userPay">
        <!--  #ifdef MP -->
        充值
        <!--  #endif -->
        <!--  #ifndef MP -->
        微信充值
        <!--  #endif -->
      </button>
    </view>
    <div>
      <view class="w100r flex-col">
        <view class="text-xl text-bold">
          历史获得和消费记录：
        </view>
        <view class="pt-xs">
          <view v-if="shellOrders.length">
            <u-table>
              <u-tr>
                <u-th>贝壳</u-th>
                <u-th>类型</u-th>
                <u-th>时间</u-th>
              </u-tr>
              <u-tr v-for="shellOrder in shellOrders" :key="shellOrder.id">
                <u-td>{{ shellOrder.shell }}</u-td>
                <u-td>{{ shellOrder.type }}</u-td>
                <u-td>{{ shellOrder.createTime | parseTime }}</u-td>
              </u-tr>
            </u-table>
          </view>
          <view v-else class="text-lg text-gray row-center">暂无</view>
        </view>
      </view>
    </div>
  </view>
</template>

<script lang="ts">
import {Vue, Options} from 'vue-property-decorator'
import QRowItem from '@/components/div-item/div-item.vue'
import {onLoad} from "@dcloudio/uni-app";
import {socialUserModule} from "socialuni-sdk/src/store/store";
import ShellOrderVO from "socialuni-api/src/model/ShellOrderVO";
import QIcon from 'socialuni-view/src/components/QIcon/QIcon.vue'
import EnumVO from "socialuni-constant/constant/EnumVO";

@Options({
  components: {QIcon}
})
export default class ShellVue extends Vue {
  payValues = [
    new EnumVO(1, '1元'),
    new EnumVO(5, '5元'),
    new EnumVO(30, '30元')
  ]

  get user() {
    return socialUserModule.mineUser
  }

  currentAmount = Number(this.payValues[0].value)

  shellOrders: ShellOrderVO[] = []

  switchAmount(index) {
    this.currentAmount = Number(this.payValues[index].value)
  }

  created() {
    onLoad(() => {
      UserAPI.queryShellAPI().then(res => {
        this.shellOrders = res.data
      })
    })
  }


  toShellInfo() {
    PageUtil.navigateTo(SkipUrlConst.shellInfoUrl())
  }

  userPay() {
    //如果是app则默认微信支付，否则就是当前环境,app 微信，其他微信mp微信，qqmp，qq
    const provider = systemModule.isApp ? ProviderType.wx : systemModule.provider
    PlatformUtils.userPay(provider, PayType.shell, this.currentAmount)
  }
}
</script>
