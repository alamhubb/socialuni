<template>
  <div>
    <!--    <u-alert v-if="isMine" type="primary" description="长按图片可删除"></u-alert>-->
    <!--    <u-alert v-else type="primary" description="长按图片可举报"></u-alert>-->
    <div class="bg-white py-sm px-xs row-wrap">
      <div v-for="(img,index) in imgUrls" :key="img" class="row-all-center w25p flex-none px-xs py-xs">
        <!--          src="https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/uploadimgmini.png"-->
        <img class="w100p h75 bd-radius" @longpress="imgLongPress(index)"
               mode="aspectFill"
               :data-src="img"
               @click="previewImage(img)"
               :src="img"
        />
      </div>
    </div>

    <socialuni-report-dialog ref="reportDialog" @delete-content="reportSuccessDeleteImg"></socialuni-report-dialog>
  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'
import SocialuniReportDialog from "socialuni-user-view-uni/src/components/SocialuniReportDialog.vue";
import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule";
import CenterUserDetailRO from "socialuni-api-base/src/model/social/CenterUserDetailRO";
import ImgFileVO from "socialuni-api-base/src/model/ImgFileVO";
import SocialuniImgUtil from "socialuni-user-sdk/src/util/SocialuniImgUtil";
import QingAppUtil from "qing-compat-js/src/util/QingAppUtil";
import AppMsg from "socialuni-constant/constant/AppMsg";
import SocialuniUserAPI from "socialuni-user-api/src/api/SocialuniUserAPI";
import SocialuniMineUserAPI from "socialuni-user-api/src/api/SocialuniMineUserAPI";
import ReportContentType from "socialuni-constant/constant/ReportContentType";
import {getCurrentInstance} from "vue";
import {onLoad} from "@dcloudio/uni-app";
import SocialuniUserImgAPI from "socialuni-user-api/src/api/SocialuniUserImgAPI";
import {socialuniAppUserModule} from "socialuni-user-sdk/src/store/SocialuniAppUserModule";

@toNative
@Component({
  components: {SocialuniReportDialog}
})
export default class UserImgListPage extends Vue {
  $refs!: {
    reportDialog: SocialuniReportDialog;
  }

  get mineUser() {
    return socialuniUserModule.mineUser
  }

  pageUser: CenterUserDetailRO = null
  imgs: ImgFileVO [] = []


  get isMine(): boolean {
    // 两个都有值，且两个都相等，才为自己
    return this.pageUser && this.mineUser && this.pageUser.id === this.mineUser.id
  }

  get imgUrls() {
    if (this.imgs) {
      return this.imgs.map(item => SocialuniImgUtil.getUserLargeImgUrl(item.src))
    } else {
      return []
    }
  }

  created() {
    onLoad((params) => {
      console.log(123)
      const userId = params.userId
      if (!userId) {
        QingAppUtil.ToastUtil.error('系统错误' + AppMsg.contactServiceMsg)
      }
      // 这里有问题，有时候直接进入页面没有userId
      SocialuniUserAPI.queryUserDetailAPI(userId).then((res: any) => {
        this.pageUser = res.data
      })
      SocialuniUserImgAPI.getUserImgListAPI(userId).then(res => {
        this.imgs = res.data
      })
    }, getCurrentInstance())
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
}
</script>
