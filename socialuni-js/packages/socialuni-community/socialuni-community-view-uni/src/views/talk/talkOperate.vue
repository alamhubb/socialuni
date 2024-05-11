<template>
  <div>
    <q-popup :value="commentVisible" @input="commentActionClose">
      <div class="w100p">
        <uni-list class="pd-sm w100p">
          <uni-list-item link :show-arrow="true" title="复制" @click="copyText"/>
          <uni-list-item link v-if="user&&comment&&(user.id === comment.user.id||user.id ===talk.user.id)"
                         :show-arrow="true" title="删除" @click="userDeleteComment"/>
          <uni-list-item link v-if="user" :show-arrow="true" title="举报"
                         @click="openReportDialog"/>
        </uni-list>
      </div>
    </q-popup>


    <q-popup :value="dialogVisible" hide-button title="举报">
      <div class="w100p row-center">
        <div class="uni-tip">
          <div class="uni-tip-content">
            <radio-group class="uni-list" @change="reportTypeChange">
              <label class="uni-list-cell flex-row uni-list-cell-pd"
                     v-for="report in reportTypes" :key="report">
                <div>
                  <radio :id="report" :value="report" :checked="report===pornInfo"></radio>
                </div>
                <div>
                  <label class="ml-10" :for="report">
                    <span>{{ report }}</span>
                  </label>
                </div>
              </label>
            </radio-group>
          </div>
          <div class="uni-textarea bd-1 bd-radius-xs">
                    <textarea placeholder="其他违规必填，其他情况选填，可详细陈述观点" v-model.trim="reportContent"
                              :show-confirm-bar="false"
                    />
          </div>
          <div class="uni-tip-group-button row-center">
            <button class="uni-tip-button w40p mr" type="default" @click="reportDialogClose" :plain="true">
              取消
            </button>
            <button class="uni-tip-button w40p" type="primary" @click="addReport" :disabled="!reportType">确定
            </button>
          </div>
        </div>
      </div>
    </q-popup>
  </div>
</template>

<script lang="ts">
import {Component, Vue, toNative} from 'vue-facing-decorator'
import QPopup from "qing-ui/src/components/QPopup.vue";
import ReportType from "socialuni-constant/constant/ReportType";
import ReportContentType from "socialuni-constant/constant/ReportContentType";
import ReportAddVO from "socialuni-api-base/src/model/report/ReportAddVO";
import {socialTalkModule} from "socialuni-community-sdk/src/store/SocialTalkModule";
import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule";
import {socialuniConfigModule} from "socialuni-app-sdk/src/store/SocialuniConfigModule";
import SocialuniTalkAPI from "socialuni-community-api/src/api/SocialuniTalkAPI";
import ReportAPI from "socialuni-app-api/src/api/ReportAPI";
import UserMsgUtil from "socialuni-user-sdk/src/util/UserMsgUtil";
import QingAppUtil from "qingjs/src/util/QingAppUtil";


// todo 后台可控制是否显示轮播图

@toNative
@Component({
  components: {
    QPopup
  }
})
export default class TalkOperate extends Vue {
  get appConfig() {
    return socialuniConfigModule.appConfig
  }

  get reportTypes() {
    return socialuniConfigModule.reportTypes
  }

  get user() {
    return socialuniUserModule.mineUser
  }

  get commentVisible() {
    return socialTalkModule.commentActionShow
  }

  get dialogVisible() {
    return socialTalkModule.reportDialogShow
  }

  get comment() {
    return socialTalkModule.comment
  }

  get reportContentType() {
    return socialTalkModule.reportContentType
  }

  get talk() {
    return socialTalkModule.talk
  }

  pornInfo: string = ReportType.pornInfo
  reportType: string = ReportType.pornInfo
  reportContent = ''

  // 被举报次数大于多少，则隐藏
  get reportCountHide() {
    return socialuniConfigModule.appMoreConfig.reportCountHide
  }

  reportDialogClose() {
    socialTalkModule.reportDialogShow = false
    this.initData()
  }

  addReport() {
    const reportAdd: ReportAddVO = new ReportAddVO(this.reportContentType, this.reportType, this.reportContent)
    if (this.reportContentType === ReportContentType.talk) {
      reportAdd.contentId = this.talk.id
    } else {
      reportAdd.contentId = this.comment.id
    }
    if (ReportType.other === this.reportType && !this.reportContent) {
      QingAppUtil.AlertUtil.hint('选择其他违规时，请您补充观点')
    } else {
      ReportAPI.addReportAPI(reportAdd).then((res: any) => {
        if (this.reportContentType === ReportContentType.comment) {
          /*const reportNum: number = this.comment.reportNum + 1
          if (reportNum >= this.reportCountHide) {
            this.frontDeleteComment()
          }*/
          this.frontDeleteComment()
        } else {
          /*
          const reportNum: number = this.home.reportNum + 1
          if (reportNum >= this.reportCountHide) {
            this.$emit('deleteTalk', this.home.id)
          }*/
          this.$emit('deleteTalk', this.talk.id)
        }
        // 必须最后清空因为前面还要使用做判断
        this.reportDialogClose()
        QingAppUtil.AlertUtil.hint(res.data)
        PlatformUtils.requestSubscribeReport()
      })
    }
  }

  deleteComment() {
    this.frontDeleteComment()
    SocialuniTalkAPI.deleteCommentAPI(this.comment.id).then(() => {
      this.initData()
    })
  }

  copyText() {
      QingAppUtil.NativeUtil.textCopy(this.comment.content)
    this.closeActionAndInitData()
  }

  closeActionAndInitData() {
    this.commentActionClose()
    this.initData()
  }

  commentActionClose() {
    socialTalkModule.commentActionShow = false
  }

  // 前端删除comment
  frontDeleteComment() {
    this.talk.comments.splice(this.talk.comments.findIndex(comment => comment.id === this.comment.id), 1)
  }

  // 用户自己删除
  userDeleteComment() {
    this.commentActionClose()
    QingAppUtil.AlertUtil.confirm('是否确定删除此条评论，此操作无法恢复').then(() => {
      this.deleteComment()
    })
  }

  openReportDialog() {
    if (this.user) {
      this.commentActionClose()
      socialTalkModule.reportContentType = ReportContentType.comment
      socialTalkModule.reportDialogShow = true
    } else {
      UserMsgUtil.unLoginMessage()
    }
  }

  reportTypeChange({detail}) {
    this.reportType = detail.value
  }

  initData() {
    socialTalkModule.talk = null
    socialTalkModule.comment = null
    socialTalkModule.reportContentType = ''
    this.$nextTick(() => {
      this.reportContent = ''
      this.reportType = ReportType.pornInfo
    })
  }
}
</script>
