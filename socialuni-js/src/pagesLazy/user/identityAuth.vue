<template>
  <div class="bg-white text-md" v-if="user">
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


      <div class="w100p pd col-all-center">
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

      <div class="w100p pd col-all-center">
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
        <q-button class="flex-1" primary :click="idAuthPreCheck">预校验</q-button>
        <q-button class="flex-1 ml" success :click="identityAuth">认证</q-button>
        <!--      <q-button success @click="identityAuth" :disabled="!imgFile || authBtnDisabled">认证</q-button>-->
      </view>


      <div class="ml mt pb-50 font-bold">预校验结果：
        <span v-if="preCheckResult" class="color-green_dark">预校验分数：{{ preCheckResult.authScore }}，{{
            preCheckResult.authHint
          }}</span>
        <span v-else>请点击预校验</span>
      </div>
    </template>

    <q-button @click="goBack" primary class="mx">返回</q-button>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import CenterUserDetailRO from '@/socialuni/model/social/CenterUserDetailRO'
import QIcon from '@/qing-ui/components/QIcon/QIcon.vue'
import DomFile from '@/socialuni/model/DomFile'
import UniUtil from '@/socialuni/utils/UniUtil'
import CosUtil from '@/socialuni/utils/CosUtil'
import QButton from '@/qing-ui/components/QButton/QButton.vue'
import TencentCosAPI from '@/api/TencentCosAPI'
import SocialUserIdentityAuthQO from '@/socialuni/model/QO/user/SocialUserIdentityAuthQO'
import SocialUserIdentityAuthPreCheckRO from '@/socialuni/model/RO/user/SocialUserIdentityAuthPreCheckRO'
import MsgUtil from '@/socialuni/utils/MsgUtil'
import TencentCosIdInfoRO from '@/socialuni/model/RO/tencent/cos/idImgInfo/TencentCosIdInfoRO'
import SocialUserIdentityAPI from '@/socialuni/api/SocialUserIdentityAPI'
import ConfigMap from '@/socialuni/constant/ConfigMap'
import ToastUtil from '@/socialuni/utils/ToastUtil'
import AlertUtil from '@/socialuni/utils/AlertUtil'
import { socialUserModule, socialUserStore } from '@/socialuni/store'
import RouterUtil from '@/socialuni/utils/RouterUtil'

@Component({
  components: {
    QButton,
    QIcon
  }
})
export default class IdentityAuthView extends Vue {
  @socialUserStore.State('user') user: CenterUserDetailRO
  imgFile: DomFile = null
  userIdImgFile: DomFile = null
  authBtnDisabled = false
  preCheckResult: SocialUserIdentityAuthPreCheckRO = null
  idInfoPreCheckResult: TencentCosIdInfoRO = null

  /**
   * 图片前台压缩，往后台传一个压缩后的可看清的图，然后后台弄出来一个压缩图，
   */
  async chooseIdImage () {
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
      this.clearCheckResult()
      UniUtil.hideLoading()
    }
  }

  clearIdInfoPreCheckResult () {
    this.idInfoPreCheckResult = null
    this.clearCheckResult()
  }

  clearIdImgFile () {
    this.userIdImgFile = null
    this.clearIdInfoPreCheckResult()
  }

  clearImgFile () {
    this.imgFile = null
    this.clearCheckResult()
  }

  async chooseImg () {
    const cosAuthRO = await CosUtil.getCosAuthRO()
    const imgFiles: DomFile[] = await UniUtil.takePicture()
    const imgFile = imgFiles[0]
    try {
      MsgUtil.showUploadLoading()
      this.imgFile = imgFile
      imgFile.src = cosAuthRO.uploadImgPath + 'userAuthImg/userSelfImg/' + imgFile.src
      TencentCosAPI.uploadFileAPI(imgFile, cosAuthRO)
    } finally {
      this.clearCheckResult()
      UniUtil.hideLoading()
    }
  }

  clearCheckResult () {
    this.preCheckResult = null
  }

  async identityAuth () {
    this.idAuthCheck()
    if (!this.preCheckResult) {
      ToastUtil.error('请先点击预校验再进行认证')
    }
    const { data } = await SocialUserIdentityAPI.userIdentityAuthAPI(new SocialUserIdentityAuthQO(this.userIdImgFile.src, this.imgFile.src))
    AlertUtil.hint(data)
    socialUserModule.getMineUserAction()
  }

  previewImage (e) {
    const current = e.target.dataset.src
    uni.previewImage({
      current: current,
      urls: [current]
    })
  }

  checkIdInfoResult () {
    if (!this.userIdImgFile) {
      ToastUtil.error('请上传真实的身份证正面照片')
    }
    if (!this.idInfoPreCheckResult || !this.idInfoPreCheckResult.Birth) {
      ToastUtil.error('请上传真实的身份证正面照片，' + ConfigMap.systemError604Default)
    }
  }

  idAuthCheck () {
    this.checkIdInfoResult()
    if (!this.imgFile || !this.imgFile.src) {
      ToastUtil.error('请上传自拍照片')
    }
  }

  async idAuthPreCheck () {
    this.clearCheckResult()
    UniUtil.showLoading('校验中')
    try {
      //研究promise setTimeout then resole
      this.idAuthCheck()
      const res = await SocialUserIdentityAPI.userIdentityAuthPreCheckAPI(new SocialUserIdentityAuthQO(this.userIdImgFile.src, this.imgFile.src))
      this.preCheckResult = res.data
    } finally {
      UniUtil.hideLoading()
    }
  }

  goBack () {
    RouterUtil.goBackOrHome()
  }
}
</script>
