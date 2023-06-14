<!--
<template>
  <div class="bg-white text-md w100p" v-if="user">
    <div v-if="user.identityAuth" class="h400 row-all-center">
      <div class="text-xxl color-red">您已认证通过</div>
    </div>
    <template v-else>
      <div class="mt-sm pt">
        <div class="text-md ml">您好：</div>
        <div class="text-md ml pl-md">应平台要求减少未成年人早恋行为的内容</div>
        <div class="text-md ml">故进行成年认证，恳请您的谅解，平台要求如下：</div>
        <div class="row-all-center mt-sm">
          <image mode="aspectFill"
                 src="https://cdxapp-1257733245.file.myqcloud.com/socialuni/common/other/qqbansjuvenilelove.png"
                 data-src="https://cdxapp-1257733245.file.myqcloud.com/socialuni/common/other/qqbansjuvenilelove.png"
                 @click="previewImage"/>
        </div>
      </div>

      <div class="mt">
        <div class="ml">
          基于平台要求，对相关功能做出以下调整:
        </div>
        <div class="ml mt-sm">
          1、用户需要进行成年认证，才可以发布包含人物图像的图片
        </div>
      </div>

      <view class="ml mt-sm">
        <div class="text-lg">相关声明：</div>
        <view class="text-md mt-sm">
          1、上传真实的本人信息才可通过成年认证，认证次数有限，请勿使用虚假信息
        </view>
        <view class="text-md mt-sm">
          2、本页面上传的信息仅用于本人成年认证，不会向任何人展示
        </view>
      </view>

      <div class="mt">
        <div class="ml">
          上传个人身份证照片和自拍完成成年认证
        </div>
      </div>


      <div class="w100p col-all-center py">
        <div class="h160 w250 col-all-center bd-sub bd-dashed bd-2 bg-click pd-xs" @click="chooseIdImage">
          <template v-if="!userIdImgFile">
            <q-icon class="color-sub" size="40" icon="plus"/>
            <div class="color-sub mt-sm">请点击上传身份证正面照片</div>
          </template>
          <image v-else class="size100p"
                 mode="aspectFit"
                 :src="userIdImgFile.path"
          ></image>
        </div>
        <div class="row-all-center mt-sm">
          <div class="color-sub bg-click" @click="chooseIdImage">点击可重新上传</div>
          <div class="color-sub ml-sm bg-click" @click="clearIdImgFile">
            清除
            <q-icon class="color-sub ml-mn" size="16" icon="close-circle-fill"/>
          </div>
        </div>
      </div>

      <div class="ml">身份证校验结果：
        <span v-if="idInfoPreCheckResult&&idInfoPreCheckResult.Birth" class="color-success_dark">通过</span>
        <span v-else class="color-error_dark">未上传</span>
      </div>

      <div class="w100p py col-all-center">
        <div class="h160 w250 col-all-center bd-sub bd-dashed bd-2 bg-click pd-xs" @click="chooseImg">
          <template v-if="!imgFile">
            <q-icon class="color-sub" size="40" icon="camera-fill"/>
            <div class="color-sub mt-sm">点击进行自拍</div>
          </template>
          <image v-else class="size100p"
                 mode="aspectFit"
                 :src="imgFile.path"
          ></image>
        </div>
        <div class="row-all-center mt-sm">
          <div class="color-sub bg-click" @click="chooseImg">点击可重新自拍</div>
          <div class="color-sub ml-sm bg-click" @click="clearImgFile">
            清除
            <q-icon class="color-sub ml-mn" size="16" icon="close-circle-fill"/>
          </div>
        </div>
      </div>

      <view class="flex-row mt-20px px">
        <q-button class="flex-1" primary :disabled="authBtnDisabled" @click="idAuthPreCheck">预校验</q-button>
        <q-button class="flex-1 ml" success :disabled="authBtnDisabled" @click="identityAuth">认证</q-button>
        &lt;!&ndash;      <q-button success @click="identityAuth" :disabled="!imgFile || authBtnDisabled">认证</q-button>&ndash;&gt;
      </view>


      <div class="ml mt pb-50 font-bold">预校验结果：
        <span v-if="preCheckResult" class="color-green_dark">预校验分数：{{ preCheckResult.authScore }}，{{
            preCheckResult.authHint
          }}</span>
        <span v-else>请点击预校验</span>
      </div>
    </template>

    <div class="px pb-30">
      <q-button @click="goBack" primary>返回</q-button>
    </div>
  </div>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import {socialConfigModule} from "socialuni-sdk/src/store/store";
import {socialUserModule} from 'socialuni-sdk/src/store/store';
import DomFile from 'socialuni-api/src/model/DomFile';
import SocialUserIdentityAuthPreCheckRO from "socialuni-api/src/model/RO/user/SocialUserIdentityAuthPreCheckRO";
import TencentCosIdInfoRO from "socialuni-api/src/model/RO/tencent/cos/idImgInfo/TencentCosIdInfoRO";
import QButton from "socialuni-view/src/components/QButton/QButton.vue";
import QIcon from "socialuni-view/src/components/QIcon/QIcon.vue";
import CosUtil from "socialuni-sdk/src/utils/CosUtil";
import UniUtil from "socialuni-sdk/src/utils/UniUtil";
import MsgUtil from "socialuni-sdk/src/utils/MsgUtil";
import TencentCosAPI from "socialuni-api/src/api/TencentCosAPI";
import ToastUtil from "socialuni-sdk/src/utils/ToastUtil";
import SocialUserIdentityAPI from "socialuni-api/src/api/socialuni/SocialUserIdentityAPI";
import SocialUserIdentityAuthQO from "socialuni-api/src/model/QO/user/SocialUserIdentityAuthQO";
import AlertUtil from "socialuni-sdk/src/utils/AlertUtil";
import RouterUtil from "socialuni-sdk/src/utils/RouterUtil";
import PageUtil from "socialuni-sdk/src/utils/PageUtil";

@Options({
  components: {
    QButton,
    QIcon
  }
})
export default class IdentityAuthView extends Vue {
  get user() {
    return socialuniUserModule.mineUser
  }

  imgFile: DomFile = null
  userIdImgFile: DomFile = null
  authBtnDisabled = false
  preCheckResult: SocialUserIdentityAuthPreCheckRO = null
  idInfoPreCheckResult: TencentCosIdInfoRO = null

  clearPreCheckResult() {
    this.preCheckResult = null
  }

  /**
   * 图片前台压缩，往后台传一个压缩后的可看清的图，然后后台弄出来一个压缩图，
   */
  async chooseIdImage() {
    const cosAuthRO = await CosUtil.getCosAuthRO()
    const imgFiles: DomFile[] = await UniUtil.chooseImage()
    const imgFile = imgFiles[0]
    try {
      MsgUtil.showUploadLoading()
      this.clearIdInfoPreCheckResult()
      this.userIdImgFile = imgFile
      imgFile.src = cosAuthRO.uploadImgPath + 'userAuthImg/userIdImg/' + imgFile.src
      const res = await TencentCosAPI.uploadFileAPI(imgFile, cosAuthRO)
      try {
        const idRes = await TencentCosAPI.getIdCardInfoAPI(res.Location, imgFile.src, cosAuthRO)
        this.idInfoPreCheckResult = idRes
      } finally {
        this.checkIdInfoResult()
      }
    } catch (e) {
      this.clearIdInfoPreCheckResult()
    } finally {
      this.clearPreCheckResult()
      UniUtil.hideLoading()
    }
  }

  clearIdInfoPreCheckResult() {
    this.idInfoPreCheckResult = null
    this.clearPreCheckResult()
  }

  clearIdImgFile() {
    this.userIdImgFile = null
    this.clearIdInfoPreCheckResult()
  }

  clearImgFile() {
    this.imgFile = null
    this.clearPreCheckResult()
  }

  async chooseImg() {
    const cosAuthRO = await CosUtil.getCosAuthRO()
    const imgFiles: DomFile[] = await UniUtil.takePicture()
    const imgFile = imgFiles[0]
    try {
      MsgUtil.showUploadLoading()
      this.imgFile = imgFile
      imgFile.src = cosAuthRO.uploadImgPath + 'userAuthImg/userSelfImg/' + imgFile.src
      TencentCosAPI.uploadFileAPI(imgFile, cosAuthRO)
    } finally {
      this.clearPreCheckResult()
      UniUtil.hideLoading()
    }
  }

  async identityAuth() {
    this.idAuthCheck()
    if (!this.preCheckResult) {
      ToastUtil.error('请先点击预校验再进行认证')
    }
    this.authBtnDisabled = true
    UniUtil.showLoading('认证中')
    try {
      const {data} = await SocialUserIdentityAPI.userIdentityAuthAPI(new SocialUserIdentityAuthQO(this.userIdImgFile.src, this.imgFile.src))
      AlertUtil.hint(data)
      socialuniUserModule.getMineUserAction()
    } finally {
      UniUtil.hideLoading()
      this.authBtnDisabled = false
    }
  }

  previewImage(e) {
    const current = e.target.dataset.src
    uni.previewImage({
      current: current,
      urls: [current]
    })
  }

  checkIdInfoResult() {
    if (!this.userIdImgFile) {
      ToastUtil.error('请上传真实的身份证正面照片')
    }
    if (!this.idInfoPreCheckResult || !this.idInfoPreCheckResult.Birth) {
      ToastUtil.error('请上传真实的身份证正面照片，' + socialConfigModule.appMoreConfig.errorMsgContactService)
    }
  }

  idAuthCheck() {
    this.checkIdInfoResult()
    if (!this.imgFile || !this.imgFile.src) {
      ToastUtil.error('请上传自拍照片')
    }
  }

  async idAuthPreCheck() {
    this.idAuthCheck()
    UniUtil.showLoading('校验中')
    this.authBtnDisabled = true
    this.clearPreCheckResult()
    try {
      //研究promise setTimeout then resole
      const res = await SocialUserIdentityAPI.userIdentityAuthPreCheckAPI(new SocialUserIdentityAuthQO(this.userIdImgFile.src, this.imgFile.src))
      this.preCheckResult = res.data
    } finally {
      UniUtil.hideLoading()
      this.authBtnDisabled = false
    }
  }

  goBack() {
    PageUtil.goBackOrHome()
  }
}
</script>
-->
