<template>
  <q-popup ref="cityDialog" bottom @cancel="dialogClose" @confirm="input">
    <template #headerLeft>
      <view class="flex-row ml-sm">
        <view class="text-black text-md font-bold">城市筛选</view>
        <view class="row-col-center text-orange font-bold px bg-click" @click="getLocation">
          <q-icon icon="map-fill" class="mr-nm" size="16"></q-icon>
          定位
        </view>
      </view>
    </template>
    <div class="row-col-center px bg-white mt-sm">
      <view class="font-bold">
        当前选择：
      </view>
      <view v-if="cityValue" class="row-col-center">
        <q-icon v-if="cityValue.position" class="mr-mn" icon="map-fill" size="16"/>
        <view class="font-bold" v-if="cityValue.provinceName">
          {{ cityValue.provinceName }}
        </view>
        <view class="font-bold" v-else>
          {{ cityValue.adName }}
        </view>
        <view v-if="cityValue.cityName" class="font-bold">
          - {{ cityValue.cityName }}
        </view>
        <view v-if="cityValue.districtName" class="font-bold">
          - {{ cityValue.districtName }}
        </view>
      </view>
    </div>

    <view class="mt-sm h360" v-if="districts && districts.length">
      <q-picker ref="citySelect" class="bg-white h100p" v-model="cityValue"
                :dataList="districts"></q-picker>
    </view>
  </q-popup>
</template>

<script lang="ts">
import {Component, Emit, Model, Vue, Prop} from 'vue-facing-decorator'
import QPopup from "@socialuni/socialuni-ui-uni/src/components/QPopup/QPopup.vue";
import QPicker from "@socialuni/socialuni-ui-uni/src/components/QPicker/QPicker.vue";
import DistrictVO from "@socialuni/socialuni-api-base/src/model/DistrictVO";
import {socialLocationModule} from "@socialuni/socialuni-community-sdk/src/store/SocialLocationModule";
import LocationUtil from "@socialuni/socialuni-community-sdk/src/util/LocationUtil";
import SocialuniAppUtil from "@socialuni/socialuni-native-util/src/util/SocialuniAppUtil";
import QIcon from "@socialuni/socialuni-ui-uni/src/components/QIcon/QIcon.vue";

@Component({
  components: {
    QPicker,
    QIcon,
    QPopup
  }
})
export default class QCityPicker extends Vue {
  public $refs!: {
    cityDialog: QPopup;
    citySelect: QPicker;
  }

  get districts() {
    return socialLocationModule.districts
  }

  @Prop() readonly value: DistrictVO

  cityValue: DistrictVO = LocationUtil.getLocation()

  dialogShow: boolean = false

  open() {
    this.cityValue = this.value
    this.$refs.cityDialog.open()
    // 如果未加载过全部数据，数据不完整，则加载全部数据，如果第二个没有子节点且或者子节点为0
    if (!this.districts.length || !this.districts[1].childs || !this.districts[1].childs.length) {
      socialLocationModule.getDistrictsAction()
    }
  }

  close() {
    this.$refs.cityDialog.close()
    this.initPopupCity()
  }

  @Emit('close')
  dialogClose() {
    return false
  }


  @Emit()
  input() {
    return this.cityValue
  }

  initPopupCity() {
    // this.$refs.citySelect.initSelectData()
  }

  // 如果当前定位是附近则发表后跳转到talk页要查询附近的，发表动态时修改store
  getLocation() {
    return SocialuniAppUtil.AlertUtil.confirm('是否将当前定位地区设置为选择地区').then(() => {
      return LocationUtil.getCurLocationCom().then((district: DistrictVO) => {
        this.cityValue = district
        this.initPopupCity()
      }).catch(() => {
          SocialuniAppUtil.AlertUtil.hint('定位功能已关闭，请手动开启')
        throw Error()
      })
    })
  }
}
</script>
