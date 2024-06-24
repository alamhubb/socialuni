<template>
  <q-popup ref="cityDialog" bottom @cancel="dialogClose" @confirm="input">
    <template #headerLeft>
      <div class="flex-row ml-sm">
        <div class="text-black text-md font-bold">城市筛选</div>
        <div class="row-col-center text-orange font-bold px bg-click" @click="getLocation">
          <q-icon icon="map-fill" class="mr-nm" size="16"></q-icon>
          定位
        </div>
      </div>
    </template>
    <div class="row-col-center px bg-white mt-sm">
      <div class="font-bold">
        当前选择：
      </div>
      <div v-if="cityValue" class="row-col-center">
        <q-icon v-if="cityValue.position" class="mr-mn" icon="map-fill" size="16"/>
        <div class="font-bold" v-if="cityValue.provinceName">
          {{ cityValue.provinceName }}
        </div>
        <div class="font-bold" v-else>
          {{ cityValue.adName }}
        </div>
        <div v-if="cityValue.cityName" class="font-bold">
          - {{ cityValue.cityName }}
        </div>
        <div v-if="cityValue.districtName" class="font-bold">
          - {{ cityValue.districtName }}
        </div>
      </div>
    </div>

    <div class="mt-sm h360" v-if="districts && districts.length">
      <q-picker ref="citySelect" class="bg-white h100p" v-model="cityValue"
                :dataList="districts"></q-picker>
    </div>
  </q-popup>
</template>

<script lang="ts">
import {Component, Emit, Model, Vue, Prop, toNative} from 'vue-facing-decorator'
import QPopup from "qing-ui/src/components/QPopup.vue";
import QPicker from "qing-ui/src/components/QPicker.vue";
import DistrictVO from "socialuni-api-base/src/model/DistrictVO";
import {socialLocationModule} from "socialuni-community-sdk/src/store/SocialLocationModule";
import LocationUtil from "socialuni-community-sdk/src/util/LocationUtil";
import QingAppUtil from "qing-compat-js/src/util/QingAppUtil";
import QIcon from "qing-ui/src/components/QIcon.vue";

@toNative
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


  @Emit('update:modelValue')
  input() {
    return this.cityValue
  }

  initPopupCity() {
    // this.$refs.citySelect.initSelectData()
  }

  // 如果当前定位是附近则发表后跳转到talk页要查询附近的，发表动态时修改store
  getLocation() {
    return QingAppUtil.AlertUtil.confirm('是否将当前定位地区设置为选择地区').then(() => {
      return LocationUtil.getCurLocationCom().then((district: DistrictVO) => {
        this.cityValue = district
        this.initPopupCity()
      }).catch(() => {
          QingAppUtil.AlertUtil.hint('定位功能已关闭，请手动开启')
        throw Error()
      })
    })
  }
}
</script>
