<template>
  <q-popup :value="value" bottom @input="close">
    <div class="row-between-center box-df bb-1">
      <view class="flex-row">
        <view class="text-black text-md font-bold">城市筛选</view>
        <view class="row-col-center text-orange font-bold px bg-click" @click="getLocation">
          <q-icon icon="map-fill" class="mr-nn" size="16"></q-icon>
          定位
        </view>
      </view>
      <view class="flex-row">
        <view class="text-blue font-bold mx-sm px-sm bg-click" @click="close">取消</view>
        <view class="text-green font-bold mx-sm px-sm bg-click" @click="confirm">确定</view>
      </view>
    </div>
    <div class="row-col-center px bg-white mt-sm">
      <view class="font-bold">
        当前选择：
      </view>
      <view v-if="bottomDistrict" class="row-col-center">
        <q-icon v-if="bottomDistrict.isLocation" class="mr-mn" icon="map-fill" size="16"/>
        <view class="font-bold" v-if="bottomDistrict.provinceName">
          {{ bottomDistrict.provinceName }}
        </view>
        <view class="font-bold" v-else>
          {{ bottomDistrict.adName }}
        </view>
        <view v-if="bottomDistrict.cityName" class="font-bold">
          - {{ bottomDistrict.cityName }}
        </view>
        <view v-if="bottomDistrict.districtName" class="font-bold">
          - {{ bottomDistrict.districtName }}
        </view>
      </view>
      <!--  如果在选择里双击，就会取消选中，bottomDistrict就没值了，就会显示这里-->
      <view v-else-if="district" class="row-col-center">
        <q-icon v-if="district.isLocation" class="mr-mn" icon="map-fill"/>
        <view class="font-bold" v-if="district.provinceName">
          {{ district.provinceName }}
        </view>
        <view class="font-bold" v-else>
          {{ district.adName }}
        </view>
        <view v-if="district.cityName" class="font-bold">
          - {{ district.cityName }}
        </view>
        <view v-if="district.districtName" class="font-bold">
          - {{ district.districtName }}
        </view>
      </view>
    </div>

    <view class="mt-sm h360" v-if="districts && districts.length">
      <q-picker ref="citySelect" class="bg-white h100p" v-model="bottomDistrict"
                :dataList="districts"></q-picker>
    </view>
  </q-popup>
</template>

<script lang="ts">
import { Component, Emit, Model, Prop, Vue, Watch } from 'vue-property-decorator'
import DistrictVO from '../model/DistrictVO'
import { socialLocationModule, socialLocationStore } from '../store'
import LocationUtil from '../utils/LocationUtil'
import AlertUtil from '../utils/AlertUtil'
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
export default class CityPicker extends Vue {
  public $refs!: {
    citySelect: any;
  }

  // 页面初始化模块
  @socialLocationStore.State('districts') readonly districts: DistrictVO[]
  @Prop() readonly district: DistrictVO
  bottomDistrict: DistrictVO = LocationUtil.initDistrict

  @Model('input') readonly value!: any

  @Watch('value')
  valueWatch () {
    if (this.value) {
      this.open()
    }
  }

  open () {
    this.bottomDistrict = this.district
    this.getLocation()
    // 如果未加载过全部数据，数据不完整，则加载全部数据，如果第二个没有子节点且或者子节点为0
    if (!this.districts.length || !this.districts[1].childs || !this.districts[1].childs.length) {
      socialLocationModule.getDistrictsAction()
    }
  }

  close () {
    this.input()
    this.bottomDistrict = this.district
  }

  @Emit()
  input () {
    return false
  }

  @Emit()
  confirm () {
    this.input()
    this.initPopupCity()
    return this.bottomDistrict
  }

  initPopupCity () {
    this.$refs.citySelect.initSelectData()
  }

  // 如果当前定位是附近则发表后跳转到talk页要查询附近的，发表动态时修改store
  getLocation () {
    return LocationUtil.getCurLocationCom().then((district: DistrictVO) => {
      this.bottomDistrict = district
      this.initPopupCity()
    }).catch(() => {
      AlertUtil.hint('定位功能已关闭，请手动开启')
      throw Error()
    })
  }
}
</script>
