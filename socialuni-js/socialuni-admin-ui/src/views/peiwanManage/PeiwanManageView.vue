<template>
  <div class="h100p">
    <div class="flex-row">
      <label-item label="陪玩名称">
        <el-input></el-input>
      </label-item>

      <label-item label="位置和经纬度：">
        <el-button class="mr-sm" size="small" type="primary" @click="openMapDialog">选择位置</el-button>
        <div>
          {{peiwanInfo.district}}：{{peiwanInfo.lat}}，{{peiwanInfo.lng}}
        </div>
      </label-item>
    </div>


    <dialog-box title="选择位置" ref="mapDialog" width="70%" top="3vh" dialog-body-height="450px">
      <iframe id="mapPage" width="100%" height="420px" frameborder=0
              src="https://apis.map.qq.com/tools/locpicker?search=1&type=1&policy=1&key=FZDBZ-5XCLO-OCYW6-SRG4Y-ZFP7O-IJBSD&referer=peiwan">
      </iframe>
    </dialog-box>


  </div>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-property-decorator'
import DataTable from '@/components/dataTable/DataTable.vue'
import LabelItem from "@/components/LabelItem.vue";
import {DialogInterface} from "@/interface/DialogInterface";
import PeiwanRO from "@/model/peiwan/PeiwanRO";

@Component({
  components: {LabelItem, DataTable}
})
export default class PeiwanManageView extends Vue {
  $refs: {
    mapDialog: DialogInterface
  }

  openMapDialog() {
    this.$refs.mapDialog.open()
  }

  district = null

  peiwanInfo: PeiwanRO = new PeiwanRO()

  beijingDistrict = [
    '东城',
    '西城',
    '朝阳',
    '丰台',
    '石景山',
    '海淀',
    '房山',
    '通州',
    '顺义',
    '昌平',
    '大兴'
  ]

  created() {
    window.addEventListener('message', (event) => {
      // 接收位置信息，用户选择确认位置点后选点组件会触发该事件，回传用户的位置信息
      const loc = event.data
      if (loc) {
        if (loc.module === 'locationPicker') {
          if (loc.latlng) {
            this.peiwanInfo.lat = loc.latlng.lat
            this.peiwanInfo.lng = loc.latlng.lng
          }
          for (const district of this.beijingDistrict) {
            if (loc.poiaddress && loc.poiaddress.includes(district)) {
              this.peiwanInfo.district = district
            }
          }
        }
      }
    }, false)
  }
}
</script>

<style scoped>

</style>
