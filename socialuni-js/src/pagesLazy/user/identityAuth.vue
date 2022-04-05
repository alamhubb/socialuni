<template>
  <div class="h100p text-md">
    <div class="mt-sm">
      <div class="text-md ml">您好：</div>
      <div class="text-md ml pl-md">应平台要求减少未成年人发布早恋内容</div>
      <div class="text-md ml">恳请您的谅解，平台要求如下：</div>
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
        1、用户发表包含人物的图像时，需要获取进行成年认证
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
        <div class="color-sub ml-sm bg-click" @click="userIdImgFile = null">
          清除
          <q-icon class="color-sub ml-mn" size="16" icon="close-circle-fill"/>
        </div>
      </div>
    </div>

    <div class="ml">身份证校验结果：
      <span v-if="idInfoPreCheckResult" class="color-success_dark">通过</span>
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
        <div class="color-sub ml-sm bg-click" @click="imgFile = null">
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
      <span v-if="preCheckResult">预校验分数：{{ preCheckResult.authScore }}，{{ preCheckResult.authHint }}</span>
      <span v-else>请点击预校验</span>
    </div>
    <!--    <view class="article-row">
          <view class="text-xxl color-red">您已认证通过，无需重复认证</view>
        </view>
        <view class="article-row">
          <view class="text-xxl color-red">您已达到认证次数上限，请联系客服处理</view>
        </view>-->

  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'
import { namespace } from 'vuex-class'
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

const userStore = namespace('user')
@Component({
  components: {
    QButton,
    QIcon
  }
})
export default class IdentityAuthView extends Vue {
  @userStore.State('user') user: CenterUserDetailRO
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
      this.idInfoPreCheckResult = null
      this.userIdImgFile = imgFile
      imgFile.src = cosAuthRO.uploadImgPath + 'userAuthImg/userIdImg/' + imgFile.src
      const res = await TencentCosAPI.uploadFileAPI(imgFile, cosAuthRO)
      console.log(imgFile.src)
      console.log(res.Location)
      try {
        const idRes = await TencentCosAPI.getIdCardInfoAPI(res.Location, imgFile.src, cosAuthRO)
        console.log(idRes)
        this.idInfoPreCheckResult = idRes
      } catch (e) {
        this.checkIdInfoResult()
      }
    } finally {
      this.preCheckResult = null
      UniUtil.hideLoading()
    }
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
      this.preCheckResult = null
      UniUtil.hideLoading()
    }
  }

  async identityAuth () {
    this.idAuthCheck()
    if (!this.preCheckResult) {
      ToastUtil.toast('请先点击预校验再进行认证')
    }
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
    if (!this.idInfoPreCheckResult) {
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
    this.preCheckResult = null
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
}
</script>
