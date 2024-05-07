<template>
    <div class="h100p">
        <div class="flex-row">

            <q-label-item label="用户名">
                <el-input v-model="peiwanInfo.nickname"></el-input>
            </q-label-item>

            <div class="flex-1 row-end">
                <el-button @click="commonSava">保存</el-button>
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
            <q-table ref="dataTable" :data="peiwanList">
                <!--                <q-table-select label="技能" prop="skills" :options="skillTags" multiple
                                                @change="skillChange"></q-table-select>-->
                <el-table-column type="index"></el-table-column>
                <q-table-input label="昵称" prop="nickname" width="200px"></q-table-input>
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
                <!--                <q-table-column label="头像" prop="avatar">
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
                                </q-table-column>-->
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

                            <div v-for="(item,index) in row.imgs" class="position-relative"
                                 @dblclick="dbClickImgSetLevelTop(index,row.imgs)">
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
            </q-table>
        </div>

        <q-dialog title="选择位置" ref="mapDialog" width="70%" top="3vh" dialog-body-height="450px">
            <iframe id="mapPage" width="100%" height="420px" frameborder=0
                    src="https://apis.map.qq.com/tools/locpicker?search=1&type=1&policy=1&key=FZDBZ-5XCLO-OCYW6-SRG4Y-ZFP7O-IJBSD&referer=peiwan">
            </iframe>
        </q-dialog>

    </div>
</template>

<script lang="ts">
import {Component, toNative, Vue} from 'vue-facing-decorator'
import PeiwanRO from "socialuni-admin-api/src/model/peiwan/PeiwanRO";
import QDialog from "qing-ui-h5/src/components/QComponents/QDialog.vue";
import SLabelItem from "qing-ui-h5/src/components/QComponents/QLabelItem.vue";
import STable from "qing-ui-h5/src/components/QComponents/QTable.vue";
import STableColumn from "qing-ui-h5/src/components/QComponents/QTableColumn/STableColumn.vue";
import STableInput from "qing-ui-h5/src/components/QComponents/QTableInput/STableInput.vue";
import STableSelect from "qing-ui-h5/src/components/QComponents/QTableSelect/STableSelect.vue";
import {Plus} from '@element-plus/icons-vue'
import FileUtilH5 from "qingjs-h5/src/util/FileUtilH5";
import type DomFile from "qingjs/src/model/DomFile";
import ImgUtilH5 from "qingjs-h5/src/util/ImgUtilH5";
import TencentCosAPI from "socialuni-app-api/src/api/TencentCosAPI";
import CosService from "socialuni-app-sdk/src/util/CosService";
import UUIDUtil from "@/utils/UUIDUtil";
import SocialuniPeiwanAdminAPI
    from "../../../../packages/socialuni-peiwan/socialuni-peiwan-api/src/api/SocialuniPeiwanAdminAPI";
import {useMagicKeys} from '@vueuse/core'
import {watch} from "vue";
import AlertUtil from "qingjs-h5/src/util/AlertUtil";
import WindowEventListener from "qing-util/src/util/WindowEventListener";
import AdminPeiwanAppInitData from "socialuni-admin-api/src/model/peiwan/AdminPeiwanAppInitData";
import SSelect from "qing-ui-h5/src/components/QComponents/QSelect/SSelect.vue";
import ImgFileVO from "socialuni-admin-api/src/model/community/ImgFileVO";
import resData from "./resdata.json"

@toNative
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
            // 检查是否按下了 ctrl+s
            console.log('触发了ctrl+s')
            this.commonSava()
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
        this.peiwanInfo = new PeiwanRO()
        this.queryPeiwanListAPI()
    }

    async queryPeiwanListAPI() {
        /*this.peiwanList = []
        setTimeout(() => {
            this.peiwanList.push(...resData.data)
        }, 100)*/
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

        console.log(imgFile)

        //fileName
        const img = {src: imgFile.path}

        //设置上传信息
        // ImgUtilH5.setUploadInfo(imgFile)

        row.imgs.push(img)

        await ImgUtilH5.setImgQualityAndAspectRatio(imgFile)

        imgFile.src = this.cosAuthRO.uploadImgPath + `imgs/useShowImg/` + imgFile.src

        console.log(imgFile)

        const res = await Promise.all([TencentCosAPI.uploadFileAPI(imgFile, this.cosAuthRO), SocialuniPeiwanAdminAPI.addPeiwanImgListAPI(row.userId, [imgFile])]);

        console.log(res)
        console.log(res[1].data)
        Object.assign(img, res[1].data[0])
    }

    commonSava() {
        if (this.peiwanInfo.nickname) {
            this.addPeiwanAPI()
        } else {
            this.saveUpdatePeiwanList()
        }
    }

    deletePeiwanImgAPI(row: PeiwanRO, index) {
        console.log(row.imgs)
        AlertUtil.confirm('是否确认删除图片').then(res => {
            const imgId = row.imgs[index].id
            row.imgs.splice(index, 1)
            SocialuniPeiwanAdminAPI.deletePeiwanImgAPI(imgId)
        })
    }


    saveUpdatePeiwanList() {

        const changeData = this.$refs.dataTable.changeData
        console.log(changeData)

        if (changeData.length) {
            SocialuniPeiwanAdminAPI.updatePeiwanListAPI(changeData).then(() => {
                this.queryPeiwanListAPI()
            })
        }

    }

    addPeiwanInfoListAPI() {
        SocialuniPeiwanAdminAPI.addPeiwanInfoListAPI(resData.data).then(() => {
            this.queryPeiwanListAPI()
        })
    }

    dbClickImgSetLevelTop(index: number, imgs: ImgFileVO[]) {
        const img = imgs[index]
        imgs.splice(index, 1)
        imgs.unshift(img)
    }
}
</script>
