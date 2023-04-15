<template>
  <view class="bg-blue6 h100p flex-col">
    <div class="chunk-blue6 px-smm py-md col-all-center flex-none">
      <div class="font-12">贝壳总数</div>
      <div class="font-bold font-28 mt"> {{ mineUser.socialCoin }}</div>
    </div>

    <div class="bg-white bt-radius-30 flex-1 pb-smm px-smm">
      <!--      <div class="flex-row">
              <div v-for="type in coinPageTypes"
                   class="flex-1 row-all-center chunk-theme_light q-box color-content mx-xs use-click"
                   @click="toCoinRecordPage(type)">{{ type }}记录
              </div>
            </div>-->
      <!--      <div class="bt mt pt">-->
      <div class="mt pt">
        <text class="row-all-center font-lg font-bold">
          充值
        </text>

        <div class="flex-row mt">
          <div v-for="value in payValues" class="flex-1 mx-xs q-box-md"
               :class="[value===checkedPayValue?'chunk-theme':'chunk-theme_light color-content']"
               @click="checkPayValue(value)">
            <div class="row-all-center">
              {{ value }}元
            </div>
            <div class="row-all-center font-12 mt-xs">
              {{ value * 100 }}贝壳
            </div>
          </div>
        </div>

        <div class="mt color-content ml-sm">
          提示：交易成功后，不支持退款，请谨慎付款
        </div>

        <div class="mt">
          <q-button class="w100p font-bold" @click="userPay" md theme :disabled="disabled">充值</q-button>
        </div>
      </div>
    </div>
  </view>
</template>

<script lang="ts">
import {Vue, Options} from 'vue-property-decorator'
import QRowItem from '@/components/div-item/div-item.vue'
import {onLoad} from "@dcloudio/uni-app";
import {socialuniConfigModule, socialuniSystemModule, socialuniUserModule} from "socialuni-sdk/src/store/store";
import ShellOrderVO from "socialuni-base-api/src/model/ShellOrderVO";
import QIcon from 'socialuni-ui/src/components/QIcon/QIcon.vue'
import EnumVO from "socialuni-constant/constant/EnumVO";
import QButton from "socialuni-ui/src/components/QButton/QButton.vue";
import QTabs from "../../../components/QTabs/QTabs.vue";
import SocialuniCoinPageType from "socialuni-constant/constant/user/SocialuniCoinPageType";
import PageUtil from "socialuni-util/src/util/PageUtil";
import SocialuniProviderType from "socialuni-constant/constant/SocialuniProviderType";
import PlatformUtils from "socialuni-base/src/utils/PlatformUtils";
import PayType from "socialuni-constant/constant/PayType";

@Options({
  components: {QTabs, QIcon, QButton}
})
export default class SocialuniCoinView extends Vue {
  coinPageTypes = SocialuniCoinPageType.allCoinTypes
  currentTabIndex = 0

  payValues = [1, 5, 10]

  checkedPayValue = 1

  disabled = false

  get mineUser() {
    return socialuniUserModule.mineUser
  }

  shellOrders: ShellOrderVO[] = []

  created() {
    onLoad(() => {
      /*UserAPI.queryShellAPI().then(res => {
        this.shellOrders = res.data
      })*/
    })
  }

  async userPay() {
    this.disabled = true
    //如果是app则默认微信支付，否则就是当前环境,app 微信，其他微信mp微信，qqmp，qq
    // const provider = socialuniSystemModule.isMp ? socialuniSystemModule.provider : SocialuniProviderType.wx
    try {
      await PlatformUtils.payCoin(this.checkedPayValue)
      this.mineUser.socialCoin += this.checkedPayValue * 100
    } finally {
      this.disabled = false
    }
  }

  toCoinRecordPage(pageType: string) {
    PageUtil.toCoinRecordPage(pageType)
  }

  checkPayValue(payValue) {
    this.checkedPayValue = payValue
  }
}
</script>
