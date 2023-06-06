<template>
    <s-dialog ref="userEditDialog" title="编辑用户信息" width="500px" :confirm="confirmEditUser">
        <div v-if="mineUser" class="row-center">
            <el-form :model="editUser" label-width="70px">
                <el-form-item label="头像">
                    <div class="row-col-end">
                        <el-popover v-if="mineUser" placement="right-start">
                            <template #reference>
                                <el-avatar size="large" class="bd" shape="square" :src="editUser.avatar"/>
                            </template>
                            <div>
                                <input id="fileSelector" type="file" @change="uploadData"/>
                                <el-upload class="w100p" @change="uploadData" :auto-upload="false">
                                    <el-button text class="ml-xs pr-lgg">上传头像</el-button>
                                </el-upload>
                                <el-divider class="mb-sm mt-0"/>
                                <el-button text class="w100p" @click.native="randomGenerateAvatar">随机生成头像
                                </el-button>
                            </div>
                        </el-popover>
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
import {Emit, Options, Vue, Watch} from 'vue-property-decorator'
import UniUtil from "socialuni-util/src/util/UniUtil";
import {socialuniUserModule} from "socialuni-user/src/store/SocialuniUserModule";
import GenderType from "socialuni-constant/constant/GenderType";
import type EnumStrVO from "socialuni-constant/constant/EnumStrVO";
import DateUtil from "socialuni-util/src/util/DateUtil";
import AlertUtil from "socialuni-util/src/util/AlertUtil";
import ToastUtil from "socialuni-util/src/util/ToastUtil";
import BirthAgeUtil from "socialuni-util/src/util/BirthAgeUtil";
import type UserEditVO from "socialuni-api-base/src/model/user/UserEditVO";
import ObjectUtil from "socialuni-util/src/util/ObjectUtil";
import SocialuniMineUserAPI, {SocialuniGetRandomAvatarRO} from "socialuni-user-api/src/api/SocialuniMineUserAPI";
import PageUtil from "socialuni-util/src/util/PageUtil";
import SButton from "@/components/socialuni/SButton.vue";
import type SocialuniMineUserRO from "socialuni-api-base/src/model/user/SocialuniMineUserRO";
import SDialog from "@/components/socialuni/SDialog.vue";
import DomFile from "socialuni-util/src/model/DomFile";
import ImgAddQO from "socialuni-api-base/src/model/user/ImgAddQO";
import CosService from "socialuni-app/src/util/CosService";
import TencentCosAPI from "socialuni-app-api/src/api/TencentCosAPI";
import UUIDUtil from "socialuni-util/src/util/UUIDUtil";
import ImgUtil from "socialuni-util/src/util/ImgUtil";

@Options({
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
                return ToastUtil.throwError('年龄不能小于18岁')
            }
        }
        //修改了性别
        if (this.mineUser.gender !== this.editUser.gender) {
            await AlertUtil.confirm('性别修改后不可再更改，请确认是否修改').catch(() => {
                return ToastUtil.info('您选择了不修改性别')
            })
        } else {
            await AlertUtil.confirm('是否确定修改个人信息')
        }
        await SocialuniMineUserAPI.editUserAPI(this.editUser).then((res: any) => {
            socialuniUserModule.setUser(res.data)
            ToastUtil.success('编辑成功')
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
            UniUtil.hideLoading()
        }
    }

    async randomGenerateAvatar() {
        await AlertUtil.confirm('是否使用随机头像，替换当前头像后无法恢复')
        const res = await SocialuniMineUserAPI.randomUserAvatar()
        socialuniUserModule.setUser(res.data)
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
