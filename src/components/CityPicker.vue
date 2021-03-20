<template>
  <u-popup v-model="showCityPopup" mode="bottom" :border-radius="20" @close="close">
    <q-bar round class="solid-bottom">
      <view class="flex-row">
        <view class="text-black text-lgg text-bold">城市筛选</view>
        <view class="text-orange text-bold px bg-active" @click="getLocation">
          <q-icon icon="map-fill" class="mr-nn" size="16"></q-icon>
          定位
        </view>
      </view>
      <view class="flex-row">
        <view class="text-blue text-bold mx-sm px-sm bg-active" @click="close">取消</view>
        <view class="text-green text-bold mx-sm px-sm bg-active" @click="confirm">确定</view>
      </view>
    </q-bar>

    <q-row-line class="mt-sm">
      <view class="text-bold">
        当前选择：
      </view>
      <view v-if="bottomDistrict" class="row-col-center">
        <q-icon v-if="bottomDistrict.isLocation" class="mr-mn" icon="map-fill" size="16" />
        <view class="text-bold" v-if="bottomDistrict.provinceName">
          {{ bottomDistrict.provinceName }}
        </view>
        <view class="text-bold" v-else>
          {{ bottomDistrict.adName }}
        </view>
        <view v-if="bottomDistrict.cityName" class="text-bold">
          - {{ bottomDistrict.cityName }}
        </view>
        <view v-if="bottomDistrict.districtName" class="text-bold">
          - {{ bottomDistrict.districtName }}
        </view>
      </view>
      <!--  如果在选择里双击，就会取消选中，bottomDistrict就没值了，就会显示这里-->
      <view v-else-if="district" class="row-col-center">
        <q-icon v-if="district.isLocation" class="mr-mn" icon="map-fill"/>
        <view class="text-bold" v-if="district.provinceName">
          {{ district.provinceName }}
        </view>
        <view class="text-bold" v-else>
          {{ district.adName }}
        </view>
        <view v-if="district.cityName" class="text-bold">
          - {{ district.cityName }}
        </view>
        <view v-if="district.districtName" class="text-bold">
          - {{ district.districtName }}
        </view>
      </view>
    </q-row-line>

    <view class="mt-sm h360px" v-if="districts && districts.length">
      <q-picker ref="citySelect" class="bg-white" v-model="bottomDistrict"
                :dataList="districts"></q-picker>
    </view>
  </u-popup>
</template>

<script lang="ts">
import { Vue, Component, Emit, Model, Watch, Prop } from 'vue-property-decorator'
import { namespace } from 'vuex-class'
import DistrictVO from '@/model/DistrictVO'
import { appModule, locationModule } from '@/plugins/store'
import LocationUtil from '@/utils/LocationUtil'
import UniUtil from '@/utils/UniUtil'
import CommonUtil from '@/utils/CommonUtil'
import Alert from '@/utils/Alert'


const locationStore = namespace('location')
@Component
export default class CityPicker extends Vue {
  public $refs!: {
    citySelect: any;
  }

  // 页面初始化模块
  @locationStore.State('districts') readonly districts: DistrictVO[]
  @Prop() readonly district: DistrictVO
  bottomDistrict: DistrictVO = LocationUtil.initDistrict
  showCityPopup = false

  @Model('input') readonly value!: any

  @Watch('value')
  valueWatch () {
    if (this.value) {
      this.open()
    }
  }

  open () {
    this.showCityPopup = true
    this.bottomDistrict = this.district
    this.getLocation()
    // 如果未加载过全部数据，数据不完整，则加载全部数据，如果第二个没有子节点且或者子节点为0
    if (!this.districts.length || !this.districts[1].childs || !this.districts[1].childs.length) {
      locationModule.getDistrictsAction()
    }
  }

  close () {
    this.input()
    this.bottomDistrict = this.district
  }

  @Emit()
  input () {
    this.showCityPopup = false
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
    return LocationUtil.getCurPositionCom().then((district: DistrictVO) => {
      this.bottomDistrict = district
      this.initPopupCity()
    }).catch(() => {
      Alert.hint('定位功能已关闭，请手动开启')
      throw Error()
    })
  }
}
</script>
