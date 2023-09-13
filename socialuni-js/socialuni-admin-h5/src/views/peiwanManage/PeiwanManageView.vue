<template>
  <div class="h100p">
    <div class="flex-row">

      <s-label-item label="用户名">
        <el-input v-model="peiwanInfo.nickname"></el-input>
        <el-button class="ml-sm" @click="addPeiwanAPI">保存</el-button>
      </s-label-item>

      <div class="flex-1 row-end">
        <el-button @click="saveUpdatePeiwanList">保存</el-button>
      </div>
      <!--            <label-item label="陪玩名称">
                      <el-input></el-input>
                  </label-item>-->

      <!--            <label-item label="图片">

                  </label-item>-->
      <!--            <el-upload drag class="size100 overflow-hidden bd-radius" :auto-upload="false" :on-change="avatarImgChange">
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
                  <el-button @click="addPeiwanAPI">更新</el-button>-->

      <label-item label="位置和经纬度：">
        <el-button class="mr-sm" size="small" type="primary" @click="openMapDialog(peiwanInfo)">选择位置
        </el-button>
        <div>
          {{ peiwanInfo.district }}：{{ peiwanInfo.lat }}，{{ peiwanInfo.lng }}
        </div>
      </label-item>
    </div>

    <div>
      <div>
      </div>
      <s-table ref="dataTable" :data="peiwanList">
        <!--                <s-table-select label="技能" prop="skills" :options="skillTags" multiple
                                        @change="skillChange"></s-table-select>-->
        <s-table-input label="昵称" prop="nickname" width="200px"></s-table-input>
        <!--                <el-table-column label="位置">
                            <template #default="{row}">
                                <div>
                                    <el-button class="mr-sm" size="small" type="primary" @click="openMapDialog(row)">选择位置
                                    </el-button>
                                </div>
                                <div v-if="row.district">
                                    {{ row.district }}：{{ row.lat }}，{{ row.lng }}
                                </div>
                            </template>
                        </el-table-column>-->
        <!--                <s-table-column label="头像" prop="avatar">
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
                        </s-table-column>-->
        <el-table-column label="上传2" width="800">
          <template #default="{row}">
            <div class="flex-row row-wrap">
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
        </el-table-column>
      </s-table>
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
import PeiwanRO from "@socialuni/socialuni-admin-api/src/model/peiwan/PeiwanRO";
import SDialog from "@socialuni/socialuni-ui-h5/src/components/SComponents/SDialog.vue";
import SLabelItem from "@socialuni/socialuni-ui-h5/src/components/SComponents/SLabelItem.vue";
import STable from "@socialuni/socialuni-ui-h5/src/components/SComponents/STable.vue";
import STableColumn from "@socialuni/socialuni-ui-h5/src/components/SComponents/STableColumn/STableColumn.vue";
import STableInput from "@socialuni/socialuni-ui-h5/src/components/SComponents/STableInput/STableInput.vue";
import STableSelect from "@socialuni/socialuni-ui-h5/src/components/SComponents/STableSelect/STableSelect.vue";
import {Plus} from '@element-plus/icons-vue'
import FileUtilH5 from "@socialuni/socialuni-native-h5/src/util/FileUtilH5";
import type DomFile from "@socialuni/socialuni-native-util/src/model/DomFile";
import ImgUtilH5 from "@socialuni/socialuni-native-h5/src/util/ImgUtilH5";
import TencentCosAPI from "@socialuni/socialuni-app-api/src/api/TencentCosAPI";
import CosService from "@socialuni/socialuni-app-sdk/src/util/CosService";
import UUIDUtil from "@/utils/UUIDUtil";
import SocialuniPeiwanAdminAPI
  from "../../../../packages/socialuni-peiwan/socialuni-peiwan-api/src/api/SocialuniPeiwanAdminAPI";
import {useMagicKeys} from '@vueuse/core'
import {watch} from "vue";
import AlertUtil from "@socialuni/socialuni-native-h5/src/util/AlertUtil";
import WindowEventListener from "@socialuni/socialuni-util/src/util/WindowEventListener";
import AdminPeiwanAppInitData from "@socialuni/socialuni-admin-api/src/model/peiwan/AdminPeiwanAppInitData";
import SSelect from "@socialuni/socialuni-ui-h5/src/components/SComponents/SSelect/SSelect.vue";

@Component({
  components: {SSelect, SDialog, Plus, STableColumn, STableInput, STable, STableSelect, SLabelItem}
})
export default class PeiwanManageView extends Vue {
  $refs: {
    dataTable: STable
  }

  openMapDialog(peiwanInfo: PeiwanRO) {
    this.curMapPeiwanInfo = peiwanInfo
    this.$refs.mapDialog.open()
  }

  skillChange(value) {
    console.log(value)
  }


  peiwanUuid = UUIDUtil.getUUID()

  peiwanList: PeiwanRO[] = []

  avatarImg = null


  district = null

  peiwanInfo: PeiwanRO = new PeiwanRO()

  curMapPeiwanInfo: PeiwanRO = null

  created() {
    this.peiwanUuid = UUIDUtil.getUUID()

    this.getAppInitDataAPI()
    this.queryPeiwanListAPI()
    this.queryCosAuthAPI()
    console.log('进入了')
    this.listenerMessage()
    WindowEventListener.useKeydownListener({ctrl: true, key: 's'}, (event) => {
      this.saveUpdatePeiwanList()
      console.log(event)
      // 检查是否按下了 ctrl+s
      console.log('触发了')
      // 阻止默认行为（保存网页）
      event.preventDefault();
      // 调用自定义事件
    })
  }


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

  skillTags: string[] = []

  appData: AdminPeiwanAppInitData = new AdminPeiwanAppInitData()

  async getAppInitDataAPI() {
    const res = await SocialuniPeiwanAdminAPI.getAppInitDataAPI()
    this.appData = res.data
    this.skillTags = this.appData.skillTags
  }

  async addPeiwanAPI() {
    await SocialuniPeiwanAdminAPI.addPeiwanInfoAPI(this.peiwanInfo)
    this.queryPeiwanListAPI()
  }

  async queryPeiwanListAPI() {
    const res = await SocialuniPeiwanAdminAPI.queryPeiwanInfoListAPI()
    this.peiwanList = res.data
  }


  listenerMessage() {
    window.addEventListener('message', (event) => {
      console.log(event)
      // 接收位置信息，用户选择确认位置点后选点组件会触发该事件，回传用户的位置信息
      const loc = event.data
      if (loc) {
        if (loc.module === 'locationPicker') {
          if (loc.latlng) {
            this.curMapPeiwanInfo.lat = loc.latlng.lat
            this.curMapPeiwanInfo.lng = loc.latlng.lng
          }
          for (const district of this.beijingDistrict) {
            if (loc.poiaddress && loc.poiaddress.includes(district)) {
              this.curMapPeiwanInfo.district = district
            }
          }
        }
      }
    }, false)
  }


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

    console.log(file)
    console.log(imgFile)

    //fileName
    const img = {src: imgFile.path}

    //设置上传信息
    ImgUtilH5.setUploadInfo(imgFile)

    row.imgs.push(img)

    // await ImgUtilH5.setImgQualityAndAspectRatio(imgFile)

    imgFile.src = this.cosAuthRO.uploadImgPath + `imgs/useShowImg/` + imgFile.src

    console.log(imgFile)

    const res = await Promise.all([TencentCosAPI.uploadFileAPI(imgFile, this.cosAuthRO), SocialuniPeiwanAdminAPI.addPeiwanImgListAPI(row.userId, [imgFile])]);

    console.log(res)
    console.log(res[1].data)
    Object.assign(img, res[1].data[0])
  }


  deletePeiwanImgAPI(row: PeiwanRO, index) {
    console.log(row.imgs)
    AlertUtil.confirm('是否确认删除图片').then(res => {
      const imgId = row.imgs[index].id as number
      row.imgs.splice(index, 1)
      SocialuniPeiwanAdminAPI.deletePeiwanImgAPI(imgId)
    })
  }


  saveUpdatePeiwanList() {
    console.log(this.$refs.dataTable.changeData)

    const changeData = this.$refs.dataTable.changeData

    SocialuniPeiwanAdminAPI.updatePeiwanListAPI(changeData).then(() => {
      this.queryPeiwanListAPI()
    })
  }
}
</script>
