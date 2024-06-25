<template>
  <div>
    <div class="px-sm">
      <div class="mx-mn mb-xs row-between-center">
        <div>相册
          <template v-if="imgUrls.length">
            <span v-if="isMine" class="ml-xs color-sub font-12">(长按图片可删除)</span>
            <span v-else class="ml-xs color-sub font-12">(长按图片可举报)</span>
          </template>
        </div>
        <div v-if="isMine">
          <q-icon icon="plus" class="color-sub" @click="uploadUserImg"></q-icon>
        </div>
      </div>
      <div class="bg-white py-sm px-xs bd-radius-10 elevation-4 mb-sm overflow-hidden">
        <div v-if="imgUrls.length" class="flex-row w100p">
          <div v-for="(img,index) in imgUrls.slice(0,4)" :key="img"
               class="row-all-center w25p flex-none px-xs">
            <!--          src="https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/uploadimgmini.png"-->
            <img v-if="index!==3" class="w100p h75 bd-radius" @longpress="imgLongPress(index)"
                 mode="aspectFill"
                 :data-src="img"
                 @click="previewImage(img)"
                 :src="img"
            />

            <div v-else class="w100p h75 bd-radius row-all-center"
                 :style="{'background-image': 'url('+img+')'}"
                 @click="toUserImgList"
                 style="background-size: cover">
              <div class="size100p bg-shadow bd-radius row-all-center color-white">
                更多
              </div>
            </div>
          </div>
        </div>
        <div v-else class="flex-row h75 row-all-center">
          <div class="color-sub">点击右上角加号上传照片</div>
        </div>
      </div>
    </div>

    <socialuni-report-dialog ref="reportDialog" @delete-content="reportSuccessDeleteImg"></socialuni-report-dialog>
  </div>
</template>

<script lang="ts">
import QIcon from "qingjs-ui/src/components/QIcon.vue";
import {Component, Prop, Vue, toNative} from 'vue-facing-decorator';
import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO";
import ImgAddQO from "socialuni-api-base/src/model/user/ImgAddQO";
import ImgFileVO from "socialuni-api-base/src/model/ImgFileVO";
import ReportContentType from "socialuni-constant/constant/ReportContentType";
import SocialuniMineUserAPI from "socialuni-user-api/src/api/SocialuniMineUserAPI";
import SocialuniReportDialog from "../../components/SocialuniReportDialog.vue";
import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule";
import UserPageUtil from "socialuni-user-sdk/src/util/UserPageUtil";
import CosService from "socialuni-app-sdk/src/util/CosService";
import DomFile from "socialuni-app-sdk/src/model/DomFile";
import TencentCosAPI from "socialuni-app-api/src/api/TencentCosAPI";
import QingAppUtil from "qing-compat-js/src/util/QingAppUtil";
import SocialuniImgUtil from "socialuni-user-sdk/src/util/SocialuniImgUtil";
import SocialuniUserRO from "socialuni-api-base/src/model/user/SocialuniUserRO";
import {socialuniAppUserModule} from "socialuni-user-sdk/src/store/SocialuniAppUserModule";
import SocialuniUserImgAPI from "socialuni-user-api/src/api/SocialuniUserImgAPI";

@toNative
@Component({
  components: {QIcon, SocialuniReportDialog}
})
export default class SocialuniUserInfoImg extends Vue {
  $refs!: {
    reportDialog: SocialuniReportDialog;
  }

  get mineUser() {
    return socialuniUserModule.mineUser
  }

  @Prop() user: CenterUserDetailRO
  @Prop() imgs: ImgFileVO[]

  get imgUrls() {
    if (this.imgs.length) {
      return this.imgs.map(item => SocialuniImgUtil.getUserLargeImgUrl(item.src))
    } else {
      return []
    }
  }

  get isMine(): boolean {
    // 两个都有值，且两个都相等，才为自己
    return socialuniUserModule.isMine(this.user)
  }

  async uploadUserImg() {
    try {
      const cosAuthRO = await CosService.getCosAuthRO()
      const imgFiles: DomFile[] = await QingAppUtil.NativeUtil.chooseImage(1)
      QingAppUtil.NativeUtil.showLoading('上传中')
      const imgFile: DomFile = imgFiles[0]
      imgFile.src = cosAuthRO.uploadImgPath + 'img/' + imgFile.src
      const res = await Promise.all([TencentCosAPI.uploadFileAPI(imgFile, cosAuthRO), SocialuniUserImgAPI.addUserImgAPI(new ImgAddQO(imgFile))])
      socialuniAppUserModule.setUserImgs(res[1].data)
    } catch (e) {
      console.error(e)
    } finally {
      QingAppUtil.NativeUtil.hideLoading()
    }
  }

  previewImage(current) {
    uni.previewImage({
      current: current,
      urls: this.imgUrls
    })
  }

  imgLongPress(imgIndex: number) {
    if (this.isMine) {
      QingAppUtil.NativeUtil.actionSheet(['删除']).then((index: number) => {
        if (index === 0) {
          this.deleteImg(imgIndex)
        }
      })
    } else {
      QingAppUtil.NativeUtil.actionSheet(['举报']).then((index: number) => {
        if (index === 0) {
          this.openReportDialog(imgIndex)
        }
      })
    }
  }

  deleteImg(imgIndex) {
    QingAppUtil.AlertUtil.warning('请确认是否删除照片？').then(() => {
      const imgs: ImgFileVO[] = this.frontDeleteImg(imgIndex)
      SocialuniUserImgAPI.deleteUserImgNewAPI(imgs[0]).then((res: any) => {
        socialuniAppUserModule.setUserImgs(res.data)
      })
    })
  }

  frontDeleteImg(imgIndex) {
    return this.imgs.splice(imgIndex, 1)
  }

  openReportDialog(imgIndex) {
    const imgId = this.imgs[imgIndex].id
    this.$refs.reportDialog.openReportDialog(ReportContentType.userImg, imgId)
  }

  reportSuccessDeleteImg(imgId) {
    const imgIndex = this.imgs.findIndex(item => item.id === imgId)
    this.frontDeleteImg(imgIndex)
  }

  toUserImgList() {
    UserPageUtil.toUserImgList(this.user.id)
  }
}
</script>
