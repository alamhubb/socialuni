<template>
  <q-popup ref="cityDialog" bottom @cancel="dialogClose" @confirm="input">
    <template #headerLeft>
      <view class="flex-row ml-sm">
        <view class="text-black text-md font-bold">城市筛选</view>
        <view class="row-col-center text-orange font-bold px bg-click" @click="getLocation">
          <q-icon icon="map-fill" class="mr-nn" size="16"></q-icon>
          定位
        </view>
      </view>
    </template>
    <div class="row-col-center px bg-white mt-sm">
      <view class="font-bold">
        当前选择：
      </view>
      <view v-if="cityValue" class="row-col-center">
        <q-icon v-if="cityValue.isLocation" class="mr-mn" icon="map-fill" size="16"/>
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
import { Component, Emit, Model, Prop, Vue, Watch } from 'vue-property-decorator'
import DistrictVO from '../../model/DistrictVO'
import { socialLocationModule, socialLocationStore } from '../../store'
import LocationUtil from '../../utils/LocationUtil'
import AlertUtil from '../../utils/AlertUtil'
import QIcon from '@/qing-ui/components/QIcon/QIcon.vue'
import QPicker from '@/qing-ui/components/QPicker/QPicker.vue'
import QPopup from '@/qing-ui/components/QPopup/QPopup.vue'

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

  // 页面初始化模块
  @socialLocationStore.State('districts') readonly districts: DistrictVO[]

  @Model() readonly value: DistrictVO

  cityValue: DistrictVO = LocationUtil.getLocation()

  dialogShow: boolean = false

  open () {
    this.cityValue = this.value
    this.$refs.cityDialog.open()
    // 如果未加载过全部数据，数据不完整，则加载全部数据，如果第二个没有子节点且或者子节点为0
    if (!this.districts.length || !this.districts[1].childs || !this.districts[1].childs.length) {
      socialLocationModule.getDistrictsAction()
    }
  }

  close () {
    this.$refs.cityDialog.close()
    this.initPopupCity()
  }

  @Emit('close')
  dialogClose () {
    return false
  }


  @Emit()
  input () {
    return this.cityValue
  }

  initPopupCity () {
    // this.$refs.citySelect.initSelectData()
  }

  // 如果当前定位是附近则发表后跳转到talk页要查询附近的，发表动态时修改store
  getLocation () {
    return AlertUtil.confirm('是否将当前定位地区设置为选择地区').then(() => {
      return LocationUtil.getCurLocationCom().then((district: DistrictVO) => {
        this.cityValue = district
        this.initPopupCity()
      }).catch(() => {
        AlertUtil.hint('定位功能已关闭，请手动开启')
        throw Error()
      })
    })
  }
}
</script>
