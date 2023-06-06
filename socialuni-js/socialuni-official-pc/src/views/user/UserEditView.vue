<template>
    <div v-if="mineUser" class="row-center">
        <el-form :model="editUser" label-width="70px">
            <el-form-item label="昵称">
                <el-input class="w200" v-model.trim="editUser.nickname" clearable maxlength="6"/>
            </el-form-item>
            <el-form-item label="性别">
                <el-checkbox-group v-model="editUser.gender">
                    <el-checkbox v-for="report in genders" :label="report.label" :name="report.value"/>
                </el-checkbox-group>
            </el-form-item>
            <el-form-item label="出生日期">
                <el-date-picker
                        class="w200"
                        v-model="editUser.birthday"
                        type="date"
                />
            </el-form-item>
            <el-form-item label="所在地">
                <el-input class="w200" v-model.trim="editUser.city" clearable maxlength="10"/>
            </el-form-item>
        </el-form>
    </div>
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
import SocialuniMineUserAPI from "socialuni-user-api/src/api/SocialuniMineUserAPI";
import PageUtil from "socialuni-util/src/util/PageUtil";
import SButton from "@/components/socialuni/SButton.vue";
import type SocialuniMineUserRO from "socialuni-api-base/src/model/user/SocialuniMineUserRO";

@Options({
    components: {SButton}
})
export default class UserEditView extends Vue {
    get mineUser() {
        return socialuniUserModule.mineUser
    }

    editUser: SocialuniMineUserRO = null

    created() {
        this.endDate = DateUtil.parseDate(new Date())
        this.initData()
    }

    endDate = ''
    btnDisabled = false

    genders: EnumStrVO [] = GenderType.userEditEnums

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

    @Watch('user')
    watchUserChange() {
        this.initData()
    }

    @Emit('close')
    closeUserEditPop() {
        // PageUtil.goBackOrMine()
    }

    clearNickname() {
        this.nickname = ''
    }

    clearLocation() {
        this.city = ''
    }

    clearWxAccount() {
        this.wxAccount = ''
    }

    clearContactAccount() {
        this.contactAccount = ''
    }

    clearQqAccount() {
        this.qqAccount = ''
    }

    clearWbAccount() {
        this.wbAccount = ''
    }

    async saveUser() {
        if (this.contactAccount) {
            if (this.contactAccount.length > 30) {
                AlertUtil.hint('联系方式不能超过30个字符，例如：vx:491369310')
                return
            } else if (this.contactAccount.length < 5) {
                AlertUtil.hint('联系方式必须大于4个字符，例如：vx:491369310')
                return
            }
        }

        //修改了性别
        if (this.mineUser.gender !== this.gender) {
            const confirm = await AlertUtil.confirm('性别修改后不可再更改，请确认是否修改')
            if (!confirm) {
                return ToastUtil.toastLong('您选择了不修改性别')
            }
        }

        if (this.birthday && this.birthday.length > 4) {
            const age = BirthAgeUtil.getAgeByBirth(this.birthday)
            if (age < 18) {
                return AlertUtil.hint('年龄不能小于18岁')
            }
        }
        this.btnDisabled = true
        AlertUtil.confirm('是否确定修改个人信息').then(() => {
            const userCopy: UserEditVO = ObjectUtil.deepClone(this.mineUser) as any
            userCopy.nickname = this.nickname
            userCopy.gender = this.gender
            userCopy.birthday = this.birthday
            userCopy.city = this.city
            userCopy.contactAccount = this.contactAccount
            userCopy.wxAccount = this.wxAccount
            userCopy.qqAccount = this.qqAccount
            userCopy.wbAccount = this.wbAccount
            UniUtil.showLoading('保存中')
            SocialuniMineUserAPI.editUserAPI(userCopy).then((res: any) => {
                socialuniUserModule.setUser(res.data)
                ToastUtil.toast('已修改')
                this.closeUserEditPop()
            }).finally(() => {
                this.btnDisabled = false
                UniUtil.hideLoading()
            })
        }).catch(() => {
            this.closeUserEditPop()
            this.btnDisabled = false
        })
    }

    dateChange({detail}) {
        this.birthday = detail.value
    }

    genderChange({detail}) {
        this.gender = detail.value
    }

    goBack() {
        PageUtil.goBackOrMine()
    }
}
</script>
