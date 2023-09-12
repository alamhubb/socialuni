<template>
    <s-dialog ref="userEditDialog" title="编辑用户信息" width="500px" :confirm="confirmEditUser">
        <div v-if="mineUser" class="row-center">
            <el-form :model="editUser" label-width="70px">
                <el-form-item label="头像">
                    <div class="row-col-end">

                        <el-dropdown v-if="mineUser" trigger="click">
                            <el-avatar size="large" class="bd" shape="square" :src="editUser.avatar"/>
                            <template #dropdown>
                                <el-dropdown-menu>
                                    <el-dropdown-item @click.native="uploadUserAvatarImg">上传头像</el-dropdown-item>
                                    <el-dropdown-item divided @click.native="randomGenerateAvatar">随机生成头像</el-dropdown-item>
                                </el-dropdown-menu>
                            </template>
                        </el-dropdown>
                    </div>
                </el-form-item>
                <el-form-item label="昵称">
                    <el-input class="w200" v-model.trim="editUser.nickname" clearable maxlength="6"/>
                </el-form-item>
                <el-form-item label="性别">
                    <el-radio-group v-model="editUser.gender">
                        <el-radio v-for="gender in genders" :label="gender.value">{{ gender.label }}</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="出生日期">
                    <el-date-picker
                            class="w200"
                            v-model="editUser.birthday"
                            value-format="YYYY-MM-DD"
                            type="date"
                    />
                </el-form-item>
                <el-form-item label="所在地">
                    <el-input class="w200" v-model.trim="editUser.city" clearable maxlength="10"/>
                </el-form-item>
            </el-form>
        </div>
    </s-dialog>
</template>

<script lang="ts">
import {Emit, Component, Vue, Watch} from 'vue-facing-decorator'
import SButton from "@socialuni/socialuni-ui-h5/src/components/SButton.vue";
import SDialog from "@socialuni/socialuni-ui-h5/src/components/SDialog.vue";
import {socialuniUserModule} from "@socialuni/socialuni-user-sdk/src/store/SocialuniUserModule";
import type SocialuniMineUserRO from "@socialuni/socialuni-api-base/src/model/user/SocialuniMineUserRO";
import type EnumStrVO from "@socialuni/socialuni-constant/constant/EnumStrVO";
import GenderType from "@socialuni/socialuni-constant/constant/GenderType";
import DateUtil from "@socialuni/socialuni-util/src/util/DateUtil";
import ObjectUtil from "@socialuni/socialuni-util/src/util/ObjectUtil";
import BirthAgeUtil from "@socialuni/socialuni-user-sdk/src/util/BirthAgeUtil";
import SocialuniAppUtil from "@socialuni/socialuni-native-util/src/util/SocialuniAppUtil";
import SocialuniMineUserAPI from "@socialuni/socialuni-user-api/src/api/SocialuniMineUserAPI";
import CosService from "@socialuni/socialuni-app-sdk/src/util/CosService";
import UUIDUtil from "@socialuni/socialuni-util/src/util/UUIDUtil";
import ImgUtil from "@socialuni/socialuni-util/src/util/ImgUtil";
import TencentCosAPI from "@socialuni/socialuni-app-api/src/api/TencentCosAPI";
import ImgAddQO from "@socialuni/socialuni-api-base/src/model/user/ImgAddQO";
import type DomFile from "@socialuni/socialuni-app-sdk/src/model/DomFile";
import NativeUtil from "../../../../packages/socialuni-native/socialuni-native-h5/src/util/NativeUtil";

@Component({
    components: {SDialog, SButton}
})
export default class SocialuniUserEditDialog extends Vue {
    $refs: {
        userEditDialog: SDialog
    }

    get mineUser() {
        return socialuniUserModule.mineUser
    }

    editUser: SocialuniMineUserRO = null
    endDate = ''
    btnDisabled = false
    genders: EnumStrVO [] = GenderType.userEditEnums

    open() {
        this.endDate = DateUtil.parseDate(new Date())
        this.initData()
        this.$refs.userEditDialog.open()
    }

    initData() {
        if (this.mineUser) {
            this.editUser = ObjectUtil.deepClone(socialuniUserModule.mineUser)
            // this.contactAccount = this.user.contactAccount || ''
            // this.wxAccount = this.user.wxAccount || ''
            // this.qqAccount = this.user.qqAccount || ''
            // this.wbAccount = this.user.wbAccount || ''
            this.btnDisabled = false
        }
    }


    async confirmEditUser() {
        console.log(123)
        if (this.editUser.birthday && this.editUser.length > 4) {
            const age = BirthAgeUtil.getAgeByBirth(this.editUser.length)
            if (age < 18) {
                return SocialuniAppUtil.ToastUtil.throwError('年龄不能小于18岁')
            }
        }
        //修改了性别
        if (this.mineUser.gender !== this.editUser.gender) {
            await SocialuniAppUtil.AlertUtil.confirm('性别修改后不可再更改，请确认是否修改').catch(() => {
                return SocialuniAppUtil.ToastUtil.info('您选择了不修改性别')
            })
        } else {
            await SocialuniAppUtil.AlertUtil.confirm('是否确定修改个人信息')
        }
        await SocialuniMineUserAPI.editUserAPI(this.editUser).then((res: any) => {
            socialuniUserModule.setUser(res.data)
            SocialuniAppUtil.ToastUtil.success('编辑成功')
            this.closeUserEditPop()
        })
    }

    async uploadData(e: any) {

        const file = e.target.files[0]
        console.log(e)
        console.log(e.target)
        console.log(e.target.files[0])
        try {
            const cosAuthRO = await CosService.getCosAuthRO()
            console.log(cosAuthRO)
            const imgKey = UUIDUtil.getUUID() + ImgUtil.getFileSuffixName(file.name)
            const res = await Promise.all([TencentCosAPI.uploadFileAPI(imgKey, file, cosAuthRO), SocialuniMineUserAPI.addUserAvatarImgAPI(new ImgAddQO(file))])
            socialuniUserModule.setUser(res[1].data)
        } catch (e) {
            console.error(e)
        } finally {
            SocialuniAppUtil.NativeUtil.hideLoading()
        }
    }

    async randomGenerateAvatar() {
        await SocialuniAppUtil.AlertUtil.confirm('是否使用随机头像，替换当前头像后无法恢复')
        const res = await SocialuniMineUserAPI.randomUserAvatar()
        socialuniUserModule.setUser(res.data)
    }

    async uploadUserAvatarImg() {
        console.log(123123)
        try {
            const cosAuthRO = await CosService.getCosAuthRO()

            console.log(123123)
            console.log(cosAuthRO.uploadImgPath)
            console.log(cosAuthRO)
            console.log(456465)
            console.log(cosAuthRO)
            const imgFiles: DomFile[] = await NativeUtil.chooseImage(1)
            SocialuniAppUtil.NativeUtil.showLoading('上传中')
            const imgFile: DomFile = imgFiles[0]
            imgFile.src = cosAuthRO.uploadImgPath + 'img/' + imgFile.src
            const res = await Promise.all([TencentCosAPI.uploadFileAPI(imgFile, cosAuthRO), SocialuniMineUserAPI.addUserAvatarImgAPI(new ImgAddQO(imgFile))])
            socialuniUserModule.setUser(res[1].data)
        } catch (e) {
            console.error(e)
        } finally {
            SocialuniAppUtil.NativeUtil.hideLoading()
        }
    }


    @Watch('mineUser')
    watchUserChange() {
        this.initData()
    }

    @Emit('close')
    closeUserEditPop() {
        // PageUtil.goBackOrMine()
    }
}
</script>
