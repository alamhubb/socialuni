<template>
    <div class="h100p">
        <div class="flex-row">
<!--            <label-item label="陪玩名称">
                <el-input></el-input>
            </label-item>-->

<!--            <label-item label="图片">

            </label-item>-->
            <el-upload drag class="size100 overflow-hidden bd-radius" :auto-upload="false" :on-change="avatarImgChange">
                <el-image v-if="peiwanInfo.avatar" class="size100" :src="peiwanInfo.avatar"
                          fit="fill"/>
                <el-icon v-else class="el-icon&#45;&#45;upload size100">
                    <Plus />
                </el-icon>
            </el-upload>

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

        imgFile.src = cosAuthRO.uploadImgPath + 'img/' + imgFile.src

        console.log(imgFile)

        // const res = await Promise.all([TencentCosAPI.uploadFileAPI(imgFile, cosAuthRO), SocialuniMineUserAPI.addUserAvatarImgAPI(new ImgAddQO(imgFile))])
    }

    created() {
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
}
</script>
