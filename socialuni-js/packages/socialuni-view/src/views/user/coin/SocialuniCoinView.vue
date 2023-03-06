<template>
  <view class="bg-blue6 h100p flex-col">
    <div class="chunk-blue6 px-smm py-md col-all-center flex-none">
      <div class="font-12">贝壳总数</div>
      <div class="font-bold font-28 mt"> {{ user.socialCoin }}</div>
    </div>

    <div class="bg-white bt-radius-30 flex-1 py-smm px-smm">
      <div class="flex-row">
        <div v-for="type in coinPageTypes"
             class="flex-1 row-all-center chunk-theme_light q-box color-content mx-xs use-click"
             @click="toCoinRecordPage(type)">{{ type }}记录
        </div>
      </div>

      <div class="bt mt pt">
        <text class="row-all-center font-lg font-bold">
          充值
        </text>

        <div class="flex-row mt">
          <div v-for="value in payValues" class="flex-1 mx-xs q-box-md" :class="[value===checkedPayValue?'chunk-theme':'chunk-theme_light color-content']" @click="checkPayValue(value)">
            <div class="row-all-center">
              {{ value }}元
            </div>
            <div  class="row-all-center font-12 mt-xs">
              {{ value * 100 }}贝壳
            </div>
          </div>
        </div>

        <div class="mt color-content ml-sm">
          提示：交易成功后，不支持退款，请谨慎付款
        </div>

        <div class="mt">
          <q-button class="w100p font-bold" @click="userPay" md>充值</q-button>
        </div>
      </div>
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
import QButton from "socialuni-view/src/components/QButton/QButton.vue";
import QTabs from "../../../components/QTabs/QTabs.vue";
import SocialuniCoinPageType from "socialuni-constant/constant/user/SocialuniCoinPageType";
import PageUtil from "socialuni-sdk/src/utils/PageUtil";

@Options({
  components: {QTabs, QIcon, QButton}
})
export default class SocialuniCoinView extends Vue {
  coinPageTypes = SocialuniCoinPageType.allCoinTypes
  currentTabIndex = 0

  payValues = [1, 5, 10]

  checkedPayValue = 1

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

  // tabs通知swiper切换
  tabsChange(index) {
    this.currentTabIndex = index
    if (this.tabsPageQueryUtil[this.currentTabIndex].queryQO.firstLoad) {
      uni.startPullDownRefresh()
      this.initQuery()
    }
  }

  // talkSwipe
  talkSwiperChange(e) {
    this.tabsChange(e.detail.current)
  }

  toCoinRecordPage(pageType: string) {
    PageUtil.toCoinRecordPage(pageType)
  }

  checkPayValue(payValue){
    this.checkedPayValue = payValue
  }
}
</script>
