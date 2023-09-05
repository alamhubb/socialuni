<template>
  <div class="h100p">
    <div class="flex-row">
      <!--            <label-item label="陪玩名称">
                      <el-input></el-input>
                  </label-item>-->

      <!--            <label-item label="图片">

                  </label-item>-->
      <el-upload drag class="size100 overflow-hidden bd-radius" :auto-upload="false" :on-change="avatarImgChange">
        <div v-if="peiwanInfo.avatar" class=" position-relative">
          <el-image class="size100 overflow-hidden" :src="peiwanInfo.avatar"
                    fit="fill"/>
          <div
              class="position-absolute z-index-button color-white size20 bg-grey8 bg-half-transparent row-all-center topRight bd bbl-radius"
              @click="deleteImg(index)">×
          </div>
        </div>

        <el-icon v-else class="size100 font-50 color-sub">
          <Plus/>
        </el-icon>
      </el-upload>

      <el-button @click="addPeiwanAPI">创建</el-button>

      <!--            <label-item label="位置和经纬度：">
                      <el-button class="mr-sm" size="small" type="primary" @click="openMapDialog">选择位置</el-button>
                      <div>
                          {{ peiwanInfo.district }}：{{ peiwanInfo.lat }}，{{ peiwanInfo.lng }}
                      </div>
                  </label-item>-->
    </div>

    <s-dialog title="选择位置" ref="mapDialog" width="70%" top="3vh" dialog-body-height="450px">
      <iframe id="mapPage" width="100%" height="420px" frameborder=0
              src="https://apis.map.qq.com/tools/locpicker?search=1&type=1&policy=1&key=FZDBZ-5XCLO-OCYW6-SRG4Y-ZFP7O-IJBSD&referer=peiwan">
      </iframe>
    </s-dialog>

  </div>
</template>

<script lang="ts">
import {Component, Vue} from 'vue-facing-decorator'
import LabelItem from "@/components/LabelItem.vue";
import {DialogInterface} from "@/interface/DialogInterface";
import PeiwanRO from "@socialuni/socialuni-admin-api/src/model/peiwan/PeiwanRO";
import SDialog from "@socialuni/socialuni-ui-h5/src/components/SDialog.vue";
import {Plus} from '@element-plus/icons-vue'
import FileUtilH5 from "@socialuni/socialuni-native-h5/src/util/FileUtilH5";
import type DomFile from "@socialuni/socialuni-native-util/src/model/DomFile";
import ImgUtilH5 from "@socialuni/socialuni-native-h5/src/util/ImgUtilH5";
import TencentCosAPI from "@socialuni/socialuni-app-api/src/api/TencentCosAPI";
import SocialuniMineUserAPI from "@socialuni/socialuni-user-api/src/api/SocialuniMineUserAPI";
import ImgAddQO from "@socialuni/socialuni-api-base/src/model/user/ImgAddQO";
import CosService from "@socialuni/socialuni-app-sdk/src/util/CosService";
import UUIDUtil from "@/utils/UUIDUtil";
import SocialuniPeiwanAPI from "@socialuni/socialuni-peiwan-api/src/api/SocialuniPeiwanAPI";

@Component({
  components: {LabelItem, SDialog, Plus}
})
export default class PeiwanManageView extends Vue {
  $refs: {
    mapDialog: SDialog
  }

  openMapDialog() {
    this.$refs.mapDialog.open()
  }

  peiwanUuid = UUIDUtil.getUUID()


  async addPeiwanAPI() {
    await SocialuniPeiwanAPI.addPeiwanInfoAPI(this.peiwanInfo)
    this.queryPeiwanListAPI()
  }

  queryPeiwanListAPI() {
    SocialuniPeiwanAPI.queryPeiwanInfoListAPI()
  }

  created() {
    this.peiwanUuid = UUIDUtil.getUUID()

    console.log('进入了')
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

  async avatarImgChange(file: any) {
    const imgFile: DomFile = FileUtilH5.fileSetPath(file.raw)

    this.peiwanInfo.avatar = imgFile.path

    const cosAuthRO = await CosService.getCosAuthRO()

    await ImgUtilH5.setImgQualityAndAspectRatio(imgFile)

    imgFile.src = cosAuthRO.uploadImgPath + `manage/${this.peiwanUuid}/` + imgFile.src

    console.log(imgFile)

    const res = await TencentCosAPI.uploadFileAPI(imgFile, cosAuthRO)

    console.log(res)
    // this.peiwanInfo.avatar = imgFile.src
  }

}
</script>
