<template>
  <div>
    <!--    <u-alert v-if="isMine" type="primary" description="长按图片可删除"></u-alert>-->
    <!--    <u-alert v-else type="primary" description="长按图片可举报"></u-alert>-->
    <div class="bg-white py-sm px-xs row-wrap">
      <div v-for="(img,index) in imgUrls" :key="img" class="row-all-center w25p flex-none px-xs py-xs">
        <!--          src="https://cdxapp-1257733245.file.myqcloud.com/qingchi/static/uploadimgmini.png"-->
        <image class="w100p h75 bd-radius" @longpress="imgLongPress(index)"
               mode="aspectFill"
               :data-src="img"
               @click="previewImage(img)"
               :src="img"
        ></image>
      </div>
    </div>

    <socialuni-report-dialog ref="reportDialog" @delete-content="reportSuccessDeleteImg"></socialuni-report-dialog>
  </div>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import UserSchoolEditDialog from "@/pages/user/UserSchoolEditDialog.vue";
import SocialuniReportDialog from "@/pages/report/SocialuniReportDialog.vue";
import {socialUserModule} from 'socialuni-sdk/src/store/store';
import CenterUserDetailRO from "socialuni-api/src/model/social/CenterUserDetailRO";
import ImgFileVO from "socialuni-api/src/model/ImgFileVO";
import ImgUtil from "socialuni-sdk/src/utils/ImgUtil";
import ToastUtil from "socialuni-sdk/src/utils/ToastUtil";
import HintMsg from "socialuni-constant/constant/HintMsg";
import SocialuniUserAPI from "socialuni-api/src/api/socialuni/SocialuniUserAPI";
import UniUtil from "socialuni-sdk/src/utils/UniUtil";
import AlertUtil from "socialuni-sdk/src/utils/AlertUtil";
import ReportContentType from "socialuni-constant/constant/ReportContentType";

@Options({
  components: {SocialuniReportDialog}
})
export default class UserImgList extends Vue {
  $refs!: {
    reportDialog: SocialuniReportDialog;
    schoolEditDialog: UserSchoolEditDialog;
  }

  get mineUser() {
    return socialUserModule.user
  }

  pageUser: CenterUserDetailRO = null
  imgs: ImgFileVO [] = []


  get isMine(): boolean {
    // 两个都有值，且两个都相等，才为自己
    return this.pageUser && this.mineUser && this.pageUser.id === this.mineUser.id
  }

  get imgUrls() {
    if (this.imgs) {
      return this.imgs.map(item => ImgUtil.getUserLargeImgUrl(item.src))
    } else {
      return []
    }
  }

  onLoad(params) {
    const userId = params.userId
    if (!userId) {
      ToastUtil.error('系统错误' + HintMsg.contactServiceMsg)
    }
    // 这里有问题，有时候直接进入页面没有userId
    SocialuniUserAPI.queryUserDetailAPI(userId).then((res: any) => {
      this.pageUser = res.data
    })
    SocialuniUserAPI.getUserImgListAPI(userId).then(res => {
      this.imgs = res.data
    })
  }

  previewImage(current) {
    uni.previewImage({
      current: current,
      urls: this.imgUrls
    })
  }

  imgLongPress(imgIndex: number) {
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

  deleteImg(imgIndex) {
    AlertUtil.warning('请确认是否删除照片？').then(() => {
      const imgs: ImgFileVO[] = this.frontDeleteImg(imgIndex)
      SocialuniUserAPI.deleteUserImgNewAPI(imgs[0]).then((res: any) => {
        socialUserModule.setUser(res.data)
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
