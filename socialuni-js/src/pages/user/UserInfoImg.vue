<template>
  <div>
    <div class="px-sm">
      <div class="mx-mn mb-xs row-between-center">
        <div>相册
          <span v-if="isMine" class="ml-xs color-sub font-12">(长按图片可删除)</span>
          <span v-else class="ml-xs color-sub font-12">(长按图片可举报)</span>
        </div>
        <div v-if="isMine">
          <q-icon icon="plus" class="color-sub" @click="uploadUserImg"></q-icon>
        </div>
      </div>
      <div class="bg-white py-sm px-xs bd-radius-10 elevation-4 mb-sm">
        <div v-if="imgUrls.length" class="flex-row">
          <div v-for="(img,index) in imgUrls.slice(0,4)" :key="img" class="row-all-center w25p flex-none px-xs">
            <!--          src="https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/uploadimgmini.png"-->
            <image v-if="index!==3" class="w100p h75 bd-radius" @longpress="imgLongPress(index)"
                   mode="aspectFill"
                   :data-src="img"
                   @click="previewImage(img)"
                   :src="img"
            ></image>

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
import { Component, PropSync, Vue } from 'vue-property-decorator'
import UniUtil from '@/socialuni/utils/UniUtil'
import SocialuniUserAPI from '@/socialuni/api/socialuni/SocialuniUserAPI'
import ImgUtil from '@/socialuni/utils/ImgUtil'
import CenterUserDetailRO from '@/socialuni/model/social/CenterUserDetailRO'
import { socialUserModule, socialUserStore } from '@/socialuni/store'
import ImgFileVO from '@/socialuni/model/ImgFileVO'
import AlertUtil from '@/socialuni/utils/AlertUtil'
import SocialuniReportDialog from '@/pages/report/SocialuniReportDialog.vue'
import ReportContentType from '@/socialuni/constant/ReportContentType'
import CosUtil from '@/socialuni/utils/CosUtil'
import DomFile from '@/socialuni/model/DomFile'
import TencentCosAPI from '@/api/TencentCosAPI'
import ImgAddQO from '@/socialuni/model/user/ImgAddQO'
import PageUtil from '@/socialuni/utils/PageUtil'
import QIcon from '@/qing-ui/components/QLocation/QIcon.vue'

@Component({
  components: { QIcon, SocialuniReportDialog }
})
export default class UserInfoImg extends Vue {
  $refs!: {
    reportDialog: SocialuniReportDialog;
  }
  @socialUserStore.State('user') mineUser: CenterUserDetailRO
  @PropSync('user') pageUser: CenterUserDetailRO

  get imgUrls () {
    if (this.pageUser && this.pageUser.imgs) {
      return this.pageUser.imgs.map(item => ImgUtil.getUserLargeImgUrl(item.src))
    } else {
      return []
    }
  }

  get isMine (): boolean {
    // 两个都有值，且两个都相等，才为自己
    return this.pageUser && this.mineUser && this.pageUser.id === this.mineUser.id
  }

  async uploadUserImg () {
    try {
      const cosAuthRO = await CosUtil.getCosAuthRO()
      const imgFiles: DomFile[] = await UniUtil.chooseImage(1)
      UniUtil.showLoading('上传中')
      const imgFile: DomFile = imgFiles[0]
      imgFile.src = cosAuthRO.uploadImgPath + 'img/' + imgFile.src
      const res = await Promise.all([TencentCosAPI.uploadFileAPI(imgFile, cosAuthRO), SocialuniUserAPI.addUserImgAPI(new ImgAddQO(imgFile))])
      TencentCosAPI.getImgTextContentAPI(res[0].Location, imgFile.src, cosAuthRO)
      socialUserModule.setUser(res[1].data)
    } catch (e) {
      console.error(e)
    } finally {
      UniUtil.hideLoading()
    }
  }

  previewImage (current) {
    uni.previewImage({
      current: current,
      urls: this.imgUrls
    })
  }

  imgLongPress (imgIndex: number) {
    if (this.isMine) {
      UniUtil.actionSheet(['删除']).then((index: number) => {
        if (index === 0) {
          this.deleteImg(imgIndex)
        }
      })
    } else {
      UniUtil.actionSheet(['举报']).then((index: number) => {
        if (index === 0) {
          this.openReportDialog(imgIndex)
        }
      })
    }
  }

  deleteImg (imgIndex) {
    AlertUtil.warning('请确认是否删除照片？').then(() => {
      const imgs: ImgFileVO[] = this.frontDeleteImg(imgIndex)
      SocialuniUserAPI.deleteUserImgNewAPI(imgs[0]).then((res: any) => {
        socialUserModule.setUser(res.data)
      })
    })
  }

  frontDeleteImg (imgIndex) {
    return this.pageUser.imgs.splice(imgIndex, 1)
  }

  openReportDialog (imgIndex) {
    const imgId = this.pageUser.imgs[imgIndex].id
    this.$refs.reportDialog.openReportDialog(ReportContentType.userImg, imgId)
  }

  reportSuccessDeleteImg (imgId) {
    const imgIndex = this.pageUser.imgs.findIndex(item => item.id === imgId)
    this.frontDeleteImg(imgIndex)
  }

  toUserImgList () {
    PageUtil.toUserImgList(this.pageUser.id)
  }
}
</script>
