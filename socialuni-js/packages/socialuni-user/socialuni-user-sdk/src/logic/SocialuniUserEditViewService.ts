import {socialuniUserModule} from "../store/SocialuniUserModule";
import SocialuniMineUserRO from "socialuni-api-base/src/model/user/SocialuniMineUserRO";
import EnumStrVO from "socialuni-constant/constant/EnumStrVO";
import GenderType from "socialuni-constant/constant/GenderType";
import DateUtil from "socialuni-util/src/util/DateUtil";
import ObjectUtil from "socialuni-util/src/util/ObjectUtil";
import BirthAgeUtil from "../util/BirthAgeUtil";
import SocialuniAppUtil from "socialuni-native-util/src/util/SocialuniAppUtil";
import SocialuniMineUserAPI from "socialuni-user-api/src/api/SocialuniMineUserAPI";
import CosService from "socialuni-app-sdk/src/util/CosService";
import UUIDUtil from "socialuni-util/src/util/UUIDUtil";
import ImgUtil from "socialuni-util/src/util/ImgUtil";
import TencentCosAPI from "socialuni-app-api/src/api/TencentCosAPI";
import ImgAddQO from "socialuni-api-base/src/model/user/ImgAddQO";
import DomFile from "socialuni-app-sdk/src/model/DomFile";
import SocialuniViewService from "socialuni/src/interface/SocialuniViewService";
import {ComponentInternalInstance} from "@vue/runtime-core";
import {reactive, watch} from "vue";
import {SDialog} from "socialuni-ui-types/src/types/SocialuniUiTypes";
import {Emit, Watch} from "vue-property-decorator";
import UserEditVO from "socialuni-api-base/src/model/user/UserEditVO";
import UserPageUtil from "../util/UserPageUtil";

interface SocialuniUserEditViewServiceRefs {
    userEditDialog: SDialog
}

class SocialuniUserEditViewService extends SocialuniViewService<SocialuniUserEditViewServiceRefs> {

    get mineUser() {
        return socialuniUserModule.mineUser
    }

    editUser: SocialuniMineUserRO = null
    endDate = ''
    btnDisabled = false
    genders: EnumStrVO [] = GenderType.userEditEnums

    initService(instance: ComponentInternalInstance) {
        super.initService(instance);
        watch(() => this.mineUser, () => {
            this.initData()
        })
    }

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
        if (this.editUser.birthday && this.editUser.birthday.length > 4) {
            const age = BirthAgeUtil.getAgeByBirth(this.editUser.birthday)
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
            SocialuniAppUtil.UniUtil.hideLoading()
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
            const imgFiles: DomFile[] = await SocialuniAppUtil.UniUtil.chooseImage(1)
            SocialuniAppUtil.UniUtil.showLoading('上传中')
            const imgFile: DomFile = imgFiles[0]
            imgFile.src = cosAuthRO.uploadImgPath + 'img/' + imgFile.src
            const res = await Promise.all([TencentCosAPI.uploadFileAPI(imgFile, cosAuthRO), SocialuniMineUserAPI.addUserAvatarImgAPI(new ImgAddQO(imgFile))])
            socialuniUserModule.setUser(res[1].data)
        } catch (e) {
            console.error(e)
        } finally {
            SocialuniAppUtil.UniUtil.hideLoading()
        }
    }

    closeUserEditPop() {
        const emits = defineEmits(['close'])
        emits('close')
    }



    async saveUser() {
        if (this.contactAccount) {
            if (this.contactAccount.length > 30) {
                SocialuniAppUtil.AlertUtil.hint('联系方式不能超过30个字符，例如：vx:491369310')
                return
            } else if (this.contactAccount.length < 5) {
                SocialuniAppUtil.AlertUtil.hint('联系方式必须大于4个字符，例如：vx:491369310')
                return
            }
        }

        //修改了性别
        if (this.mineUser.gender !== this.gender) {
            const confirm = await SocialuniAppUtil.AlertUtil.confirm('性别修改后不可再更改，请确认是否修改')
            if (!confirm) {
                return SocialuniAppUtil.ToastUtil.toastLong('您选择了不修改性别')
            }
        }

        if (this.birthday && this.birthday.length > 4) {
            const age = BirthAgeUtil.getAgeByBirth(this.birthday)
            if (age < 18) {
                return SocialuniAppUtil.AlertUtil.hint('年龄不能小于18岁')
            }
        }
        this.btnDisabled = true
        SocialuniAppUtil.AlertUtil.confirm('是否确定修改个人信息').then(() => {
            const userCopy: UserEditVO = ObjectUtil.deepClone(this.mineUser) as any
            userCopy.nickname = this.nickname
            userCopy.gender = this.gender
            userCopy.birthday = this.birthday
            userCopy.city = this.city
            userCopy.contactAccount = this.contactAccount
            userCopy.wxAccount = this.wxAccount
            userCopy.qqAccount = this.qqAccount
            userCopy.wbAccount = this.wbAccount
            SocialuniAppUtil.UniUtil.showLoading('保存中')
            SocialuniMineUserAPI.editUserAPI(userCopy).then((res: any) => {
                socialuniUserModule.setUser(res.data)
                SocialuniAppUtil.ToastUtil.toast('已修改')
                this.closeUserEditPop()
            }).finally(() => {
                this.btnDisabled = false
                SocialuniAppUtil.UniUtil.hideLoading()
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
        UserPageUtil.goBackOrMine()
    }


    get user() {
        return socialuniUserModule.mineUser
    }


    appGenderType = GenderType.all
    GenderTypeAll = GenderType.all

}

const socialuniUserEditViewService = reactive(new SocialuniUserEditViewService())
export default socialuniUserEditViewService
