<template>
  <div class="h100p">
    <div class="flex-row">
      <!--            <label-item label="陪玩名称">
                      <el-input></el-input>
                  </label-item>-->

      <!--            <label-item label="图片">

                  </label-item>-->
      <el-upload drag class="size100 overflow-hidden bd-radius" :auto-upload="false" :on-change="avatarImgChange">
        <div v-if="avatarImg" class=" position-relative">
          <el-image class="size100 overflow-hidden" :src="avatarImg"
                    fit="fill"/>
          <div
              class="position-absolute z-index-button color-white size20 bg-grey8 bg-half-transparent row-all-center topRight bd bbl-radius"
              @click="avatarImg = null">×
          </div>
        </div>

        <el-icon v-else class="size100 font-50 color-sub">
          <Plus/>
        </el-icon>
      </el-upload>

      <el-button @click="addPeiwanAPI">创建</el-button>
      <el-button @click="addPeiwanAPI">更新</el-button>

      <!--            <label-item label="位置和经纬度：">
                      <el-button class="mr-sm" size="small" type="primary" @click="openMapDialog">选择位置</el-button>
                      <div>
                          {{ peiwanInfo.district }}：{{ peiwanInfo.lat }}，{{ peiwanInfo.lng }}
                      </div>
                  </label-item>-->


    </div>

    <div>
      <div>
      </div>
      <el-table :data="peiwanList">
        <!--                <y-table-input label="昵称" prop="username"></y-table-input>-->
        <!--                <y-table-column label="头像" prop="avatar">
                            <template #default="{row}">
                                <img :src="row.avatar">
                            </template>
                        </y-table-column>-->
        <y-table-column label="上传" prop="avatar">
          <template #default="{row}">
            <el-upload drag class="size100 overflow-hidden bd-radius" :auto-upload="false"
                       :on-change="(file)=>tableAvatarImgChange(row,file)">
              <div v-if="row.avatar" class="position-relative">
                <el-image class="size100 overflow-hidden" :src="row.avatar"
                          fit="fill"/>
              </div>

              <el-icon v-else class="size100 font-50 color-sub">
                <Plus/>
              </el-icon>
            </el-upload>
          </template>
        </y-table-column>
        <y-table-column label="上传2">
          <template #default="{row}">
            <div class="flex-row">
              <el-upload drag class="size100 overflow-hidden bd-radius" :auto-upload="false"
                         multiple
                         :on-change="(file)=>tablePeiwanImgChange(row,file)">
                <el-icon class="size100 font-50 color-sub">
                  <Plus/>
                </el-icon>
              </el-upload>

              <div v-for="(item,index) in row.imgs" class="position-relative">
                <el-image class="size100 overflow-hidden" :src="item.src"
                          fit="fill"/>
                <div
                    class="position-absolute color-white size20 bg-grey8 bg-half-transparent row-all-center topRight bd bbl-radius use-click"
                    @click="deletePeiwanImgAPI(row,index)">×
                </div>
              </div>
            </div>
          </template>
        </y-table-column>
        <!--                <y-table-input label="昵称" prop="username"></y-table-input>
                        <el-table-column label="头像" prop="avatar">
                            <template #default="{row}">
                                <img :src="row.avatar">
                            </template>
                        </el-table-column>-->
      </el-table>
    </div>

    <q-dialog title="选择位置" ref="mapDialog" width="70%" top="3vh" dialog-body-height="450px">
      <iframe id="mapPage" width="100%" height="420px" frameborder=0
              src="https://apis.map.qq.com/tools/locpicker?search=1&type=1&policy=1&key=FZDBZ-5XCLO-OCYW6-SRG4Y-ZFP7O-IJBSD&referer=peiwan">
      </iframe>
    </q-dialog>

  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'
import {DialogInterface} from "@/interface/DialogInterface";
import PeiwanRO from "socialuni-admin-api/src/model/peiwan/PeiwanRO";
import QDialog from "qingjs-ui-h5/src/components/QDialog.vue";
import {Plus} from '@element-plus/icons-vue'
import FileUtilH5 from "qing-compat-js-h5/src/util/FileUtilH5";
import type DomFile from "qing-compat-js/src/model/DomFile";
import ImgUtilH5 from "qing-compat-js-h5/src/util/ImgUtilH5";
import TencentCosAPI from "socialuni-app-api/src/api/TencentCosAPI";
import CosService from "socialuni-app-sdk/src/util/CosService";
import {useMagicKeys} from '@vueuse/core'
import {watch} from "vue";
import AlertUtil from "qing-compat-js-h5/src/util/AlertUtil";
import SocialuniPeiwanAdminAPI from "socialuni-peiwan-api/src/api/SocialuniPeiwanAdminAPI";
import UUIDUtil from "qing-util/src/util/UUIDUtil";



@Component({
  components: {SDialog, Plus}
})
class PeiwanManageView extends Vue {
  $refs: {
    mapDialog: SDialog
  }

  openMapDialog() {
    this.$refs.mapDialog.open()
  }


  peiwanUuid = UUIDUtil.getUUID()

  peiwanList: PeiwanRO[] = []

  avatarImg = null


  async addPeiwanAPI() {
    await SocialuniPeiwanAdminAPI.addPeiwanInfoAPI(this.peiwanInfo)
    this.queryPeiwanListAPI()
  }

  async queryPeiwanListAPI() {
    console.log(1232)
    const res = await SocialuniPeiwanAdminAPI.queryPeiwanInfoListAPI()
    console.log(456)
    this.peiwanList = res.data
  }

  created() {
    console.log(123)
    this.peiwanUuid = UUIDUtil.getUUID()

    this.queryPeiwanListAPI()
    this.queryCosAuthAPI()
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

    const keys = useMagicKeys({
      passive: false,
      onEventFired(e) {
        e.preventDefault()
        console.log(e)
        console.log(1111)
        return false
      }
    })

    const ctrls = keys['ctrl+s']

    watch(ctrls, (v) => {
      if (v) {
        console.log('触发了保存')
      }
    })
  }

  district = null

  peiwanInfo: PeiwanRO = new PeiwanRO()


  updatePeiwanList


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

    this.avatarImg = imgFile.path

    await ImgUtilH5.setImgQualityAndAspectRatio(imgFile)

    imgFile.src = this.cosAuthRO.uploadImgPath + `manage/img/avatar/${this.peiwanUuid}/` + imgFile.src

    this.peiwanInfo.avatar = imgFile.src

    console.log(imgFile)

    const res = await TencentCosAPI.uploadFileAPI(imgFile, this.cosAuthRO)

    console.log(res)
    // this.peiwanInfo.avatar = imgFile.src
  }

  cosAuthRO = null

  async queryCosAuthAPI() {
    this.cosAuthRO = await CosService.getCosAuthRO()
  }

  async tableAvatarImgChange(row: PeiwanRO, file: any) {
    const imgFile: DomFile = FileUtilH5.fileSetPath(file.raw)

    row.avatar = imgFile.path

    await ImgUtilH5.setImgQualityAndAspectRatio(imgFile)

    imgFile.src = this.cosAuthRO.uploadImgPath + `imgs/userAvatar/` + imgFile.src


    console.log(imgFile)

    const res = await Promise.all([TencentCosAPI.uploadFileAPI(imgFile, this.cosAuthRO), SocialuniPeiwanAdminAPI.updatePeiwanAvatarAPI(row.userId, imgFile.src)]);

    row.avatar = res[1].data

  }

  async tablePeiwanImgChange(row: PeiwanRO, file: any) {
    const imgFile: DomFile = FileUtilH5.fileSetPath(file.raw)

    row.imgs.push({src: file.path})

    await ImgUtilH5.setImgQualityAndAspectRatio(imgFile)

    imgFile.src = this.cosAuthRO.uploadImgPath + `imgs/useShowImg/` + imgFile.src

    console.log(imgFile)

    const res = await Promise.all([TencentCosAPI.uploadFileAPI(imgFile, this.cosAuthRO), SocialuniPeiwanAdminAPI.addPeiwanImgListAPI(row.userId, [imgFile])]);

    row.imgs.push(res[1].data)
  }


  deletePeiwanImgAPI(row: PeiwanRO, index) {
    console.log(row.imgs)
    AlertUtil.confirm('是否确认删除图片').then(res => {
      const imgId = row.imgs[index].id as number
      row.imgs.splice(index, 1)
      SocialuniPeiwanAdminAPI.deletePeiwanImgAPI(imgId)
    })
  }
}
export default toNative(PeiwanManageView)
</script>
