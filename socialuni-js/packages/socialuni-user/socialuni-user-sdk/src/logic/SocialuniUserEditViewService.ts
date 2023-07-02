import {socialuniUserModule} from "../store/SocialuniUserModule";
import SocialuniMineUserRO from "@socialuni/socialuni-api-base/src/model/user/SocialuniMineUserRO";
import EnumStrVO from "@socialuni/socialuni-constant/constant/EnumStrVO";
import GenderType from "@socialuni/socialuni-constant/constant/GenderType";
import DateUtil from "@socialuni/socialuni-util/src/util/DateUtil";
import ObjectUtil from "@socialuni/socialuni-util/src/util/ObjectUtil";
import BirthAgeUtil from "../util/BirthAgeUtil";
import SocialuniAppUtil from "@socialuni/socialuni-native-util/src/util/SocialuniAppUtil";
import SocialuniMineUserAPI from "@socialuni/socialuni-user-api/src/api/SocialuniMineUserAPI";
import CosService from "@socialuni/socialuni-app-sdk/src/util/CosService";
import UUIDUtil from "@socialuni/socialuni-util/src/util/UUIDUtil";
import ImgUtil from "@socialuni/socialuni-util/src/util/ImgUtil";
import TencentCosAPI from "@socialuni/socialuni-app-api/src/api/TencentCosAPI";
import ImgAddQO from "@socialuni/socialuni-api-base/src/model/user/ImgAddQO";
import DomFile from "@socialuni/socialuni-app-sdk/src/model/DomFile";
import SocialuniViewService from "@socialuni/socialuni/src/interface/SocialuniViewService";
import {ComponentInternalInstance} from "@vue/runtime-core";
import {reactive, watch} from "vue";
import {SDialog} from "@socialuni/socialuni-ui-types/src/types/SocialuniUiTypes";
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
    appGenderType = GenderType.all
    GenderTypeAll = GenderType.all

    initService(instance: ComponentInternalInstance) {
        super.initService(instance);
        this.initData()
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
        this.btnDisabled = true
        SocialuniAppUtil.UniUtil.showLoading('保存中')
        SocialuniMineUserAPI.editUserAPI(this.editUser).then((res: any) => {
            socialuniUserModule.setUser(res.data)
            SocialuniAppUtil.ToastUtil.success('编辑成功')
            this.closeUserEditPop()
        }).finally(() => {
            this.btnDisabled = false
            SocialuniAppUtil.UniUtil.hideLoading()
        })
    }

    dateChange({detail}) {
        this.editUser.birthday = detail.value
    }

    genderChange({detail}) {
        this.editUser.gender = detail.value
    }

    goBack() {
        UserPageUtil.goBackOrMine()
    }

}

const socialuniUserEditViewService = reactive(new SocialuniUserEditViewService())
export default socialuniUserEditViewService
