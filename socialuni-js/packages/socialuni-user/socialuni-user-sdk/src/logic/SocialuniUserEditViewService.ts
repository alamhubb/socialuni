import {socialuniUserModule} from "../store/SocialuniUserModule";
import EnumStrVO from "socialuni-constant/constant/EnumStrVO";
import GenderType from "socialuni-constant/constant/GenderType";
import DateUtil from "qing-util/src/util/DateUtil";
import ObjectUtil from "qing-util/src/util/ObjectUtil";
import BirthAgeUtil from "../util/BirthAgeUtil";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import SocialuniMineUserAPI from "socialuni-user-api/src/api/SocialuniMineUserAPI";
import CosService from "socialuni-app-sdk/src/util/CosService";
import UUIDUtil from "qing-util/src/util/UUIDUtil";
import ImgUtil from "qing-util/src/util/ImgUtil";
import TencentCosAPI from "socialuni-app-api/src/api/TencentCosAPI";
import ImgAddQO from "socialuni-api-base/src/model/user/ImgAddQO";
import DomFile from "socialuni-app-sdk/src/model/DomFile";
import SocialuniViewService from "socialuni/src/interface/SocialuniViewService";
import {ComponentInternalInstance} from "@vue/runtime-core";
import {reactive, watch} from "vue";
import UserPageUtil from "../util/UserPageUtil";
import SocialuniUserRO from "socialuni-api-base/src/model/user/SocialuniUserRO";


class SocialuniUserEditViewService extends SocialuniViewService {

    get mineUser() {
        return socialuniUserModule.mineUser
    }

    editUser: SocialuniUserRO = null
    endDate = ''
    btnDisabled = false
    genders: EnumStrVO [] = GenderType.userEditEnums
    appGenderType = GenderType.all
    GenderTypeAll = GenderType.all

    initService() {
        // super.initService(instance);
        this.initData()
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
        try {
            const cosAuthRO = await CosService.getCosAuthRO()
            const imgKey = UUIDUtil.getUUID() + ImgUtil.getFileSuffixName(file.name)
            const res = await Promise.all([TencentCosAPI.uploadFileAPI(file, cosAuthRO), SocialuniMineUserAPI.addUserAvatarImgAPI(new ImgAddQO(file))])
            socialuniUserModule.setUser(res[1].data)
            this.initData()
        } catch (e) {
            console.error(e)
        } finally {
            QingAppUtil.NativeUtil.hideLoading()
        }
    }

    async randomGenerateAvatar() {
        await QingAppUtil.AlertUtil.confirm('是否使用随机头像，替换当前头像后无法恢复')
        const res = await SocialuniMineUserAPI.randomUserAvatar()
        socialuniUserModule.setUser(res.data)
        this.initData()
    }

    async uploadUserAvatarImg() {
        try {
            const cosAuthRO = await CosService.getCosAuthRO()

            const imgFiles: DomFile[] = await QingAppUtil.NativeUtil.chooseImage(1)
            QingAppUtil.NativeUtil.showLoading('上传中')
            const imgFile: DomFile = imgFiles[0]
            imgFile.src = cosAuthRO.uploadImgPath + 'img/' + imgFile.src
            const res = await Promise.all([TencentCosAPI.uploadFileAPI(imgFile, cosAuthRO), SocialuniMineUserAPI.addUserAvatarImgAPI(new ImgAddQO(imgFile))])
            socialuniUserModule.setUser(res[1].data)
            this.initData()
        } catch (e) {
            console.error(e)
        } finally {
            QingAppUtil.NativeUtil.hideLoading()
        }
    }


    async saveUser() {
        if (this.editUser.birthday && this.editUser.birthday.length > 4) {
            const age = BirthAgeUtil.getAgeByBirth(this.editUser.birthday)
            if (age < 18) {
                return QingAppUtil.ToastUtil.throwError('年龄不能小于18岁')
            }
        }
        //修改了性别
        if (this.mineUser.gender !== this.editUser.gender) {
            await QingAppUtil.AlertUtil.confirm('性别修改后不可再更改，请确认是否修改').catch(() => {
                return QingAppUtil.ToastUtil.info('您选择了不修改性别')
            })
        } else {
            await QingAppUtil.AlertUtil.confirm('是否确定修改个人信息')
        }
        this.btnDisabled = true
        QingAppUtil.NativeUtil.showLoading('保存中')
        SocialuniMineUserAPI.editUserAPI(this.editUser).then((res: any) => {
            socialuniUserModule.setUser(res.data)
            this.initData()
            QingAppUtil.ToastUtil.success('编辑成功')
        }).finally(() => {
            this.btnDisabled = false
            QingAppUtil.NativeUtil.hideLoading()
        })
    }

    dateChange({detail}) {
        socialuniUserEditViewService.editUser.birthday = detail.value
    }

    genderChange({detail}) {
        socialuniUserEditViewService.editUser.gender = detail.value
    }

    goBack() {
        UserPageUtil.goBackOrMine()
    }

}

const socialuniUserEditViewService = reactive(new SocialuniUserEditViewService())
export default socialuniUserEditViewService
