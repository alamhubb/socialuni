<template>
    <div>
        123
        <!--    <u-alert v-if="isMine" type="primary" description="长按图片可删除"></u-alert>-->
        <!--    <u-alert v-else type="primary" description="长按图片可举报"></u-alert>-->
    </div>
</template>

<script lang="ts">
import {Options, Vue} from 'vue-property-decorator'
import {setup} from "vue-class-component";

setup(() => {
    console.log(9989)
})

@Options({})
export default class UserImgListPage extends Vue {

    setup() {
        console.log(789)
    }

    created() {
        setup(() => {
            console.log(9989)
        })
        console.log(this)
        console.log(3232)
    }

    onLoad() {
        console.log(123)
    }

    /*$refs!: {
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

    onShow(){
        console.log(123123)
    }

    onLoad(params) {
        console.log(123)
      const userId = params.userId
      if (!userId) {
        SocialuniAppUtil.ToastUtil.error('系统错误' + AppMsg.contactServiceMsg)
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
        SocialuniAppUtil.UniUtil.actionSheet(['删除']).then((index: number) => {
          if (index === 0) {
            this.deleteImg(imgIndex)
          }
        })
      } else {
        SocialuniAppUtil.UniUtil.actionSheet(['举报']).then((index: number) => {
          if (index === 0) {
            this.openReportDialog(imgIndex)
          }
        })
      }
    }

    deleteImg(imgIndex) {
      SocialuniAppUtil.AlertUtil.warning('请确认是否删除照片？').then(() => {
        const imgs: ImgFileVO[] = this.frontDeleteImg(imgIndex)
        SocialuniMineUserAPI.deleteUserImgNewAPI(imgs[0]).then((res: any) => {
          socialuniUserModule.setUser(res.data)
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
    }*/
}
</script>
