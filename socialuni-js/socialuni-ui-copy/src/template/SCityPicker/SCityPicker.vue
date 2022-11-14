<template>
  <s-popup ref="cityDialog" :bottom="true" @cancel="dialogClose" @confirm="input">
    <template #headerLeft>
      <view class="flex-row ml-sm">
        <view class="text-black text-md font-bold">城市筛选</view>
        <view class="row-col-center text-orange font-bold px bg-click" @click="getLocation">
          <s-icon icon="map-fill" class="mr-nm" size="16"></s-icon>
          定位
        </view>
      </view>
    </template>
    <div class="row-col-center px bg-white mt-sm">
      <view class="font-bold">
        当前选择：
      </view>
      <view v-if="cityValue" class="row-col-center">
        <s-icon v-if="cityValue.isLocation" class="mr-mn" icon="map-fill" size="16"/>
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
    <view class="mt-sm h360">
      <s-picker ref="citySelect" class="bg-white h100%" v-model="cityValue"
                :dataList="districts"></s-picker>
    </view>
  </s-popup>
</template>

<script setup lang="ts">
import {computed, defineProps, ref} from "vue";
import {socialLocationModule} from "socialuni-sdk/src/store/store";
import DistrictVO from "socialuni-api/model/DistrictVO";
import LocationUtil from "socialuni-user/src/utils/LocationUtil";
import AlertUtil from "socialuni-use/src/utils/AlertUtil";
import SPicker from "socialuni-ui/src/components/SPicker/SPicker.vue";
import SIcon from "socialuni-ui/src/components/SIcon/SIcon.vue";
import SPopup from "socialuni-ui/src/components/SPopup/SPopup.vue";


const cityDialog = ref(null)
const citySelect = ref(null)

const districts = computed<DistrictVO []>(() => {
  console.log(socialLocationModule.districts)
  return socialLocationModule.districts
})

const props = defineProps({
  //如果是头顶或者底部则不为width100
  value: DistrictVO,
})

const cityValue: DistrictVO = LocationUtil.getLocation()

const dialogShow: boolean = false

function open() {
  this.cityValue = this.value
  cityDialog.value.open()
  console.log(districts.value.length)
  // 如果未加载过全部数据，数据不完整，则加载全部数据，如果第二个没有子节点且或者子节点为0
  if (!districts.value.length || !districts[1].value.childs || !districts[1].value.childs.length) {
    socialLocationModule.getDistrictsAction()
  }
}

function close() {
  cityDialog.close()
  initPopupCity()
}

const emit = defineEmits(['close', 'input'])

function dialogClose() {
  emit('close', false)
}


function input() {
  emit('input', cityValue)
}

function initPopupCity() {
  // $refs.citySelect.initSelectData()
}

// 如果当前定位是附近则发表后跳转到talk页要查询附近的，发表动态时修改store
function getLocation() {
  return AlertUtil.confirm('是否将当前定位地区设置为选择地区').then(() => {
    return LocationUtil.getCurLocationCom().then((district: DistrictVO) => {
      cityValue = district
      initPopupCity()
    }).catch(() => {
      AlertUtil.hint('定位功能已关闭，请手动开启')
      throw Error()
    })
  })
}

defineExpose({
  open
});
</script>
