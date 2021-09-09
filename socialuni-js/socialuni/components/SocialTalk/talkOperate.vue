<template>
  <view>
    <u-popup :value="commentVisible" border-radius="20" mode="center"
             @close="commentActionClose">
      <view class="uni-tip w180px">
        <uni-list class="w100px">
          <uni-list-item :show-arrow="true" title="复制" @click="copyText"/>
          <uni-list-item v-if="user&&comment&&(user.id === comment.user.id||user.id ===talk.user.id)"
                         :show-arrow="true" title="删除" @click="userDeleteComment"/>
          <uni-list-item v-if="user" :show-arrow="true" title="举报"
                         @click="openReportDialog"/>
        </uni-list>
      </view>
    </u-popup>

    <u-popup :value="dialogVisible" border-radius="20" mode="center"
             :mask-close-able="false">
      <view class="uni-tip">
        <view class="uni-tip-title">举报</view>
        <view class="uni-tip-content">
          <radio-group class="uni-list" @change="reportTypeChange">
            <label class="uni-list-cell flex-row uni-list-cell-pd"
                   v-for="report in reportTypes" :key="report">
              <view>
                <radio :id="report" :value="report" :checked="report===pornInfo"></radio>
              </view>
              <view>
                <label class="ml-10px" :for="report">
                  <text>{{ report }}</text>
                </label>
              </view>
            </label>
          </radio-group>
        </view>
        <view class="uni-textarea bd-1 bd-radius-xs">
                    <textarea placeholder="其他违规必填，其他情况选填，可详细陈述观点" v-model.trim="reportContent"
                              :show-confirm-bar="false"
                    />
        </view>
        <view class="uni-tip-group-button">
          <button class="uni-tip-button w40p" type="default" @click="reportDialogClose" :plain="true">
            取消
          </button>
          <button class="uni-tip-button w40p" type="primary" @click="addReport" :disabled="!reportType">确定
          </button>
        </view>
      </view>
    </u-popup>
  </view>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator'

import { namespace } from 'vuex-class'

import CommentVO from 'socialuni/model/comment/CommentVO'
import ReportType from 'socialuni/const/ReportType'
import ReportAddVO from 'socialuni/model/report/ReportAddVO'
import ReportContentType from 'socialuni/const/ReportContentType'
import ReportAPI from 'socialuni/api/ReportAPI'
import TalkAPI from 'socialuni/api/TalkAPI'
import { socialAppStore, socialConfigStore, socialTalkModule, socialTalkStore, socialUserStore } from 'socialuni/store'
import TalkVO from 'socialuni/model/talk/TalkVO'
import MsgUtil from 'socialuni/utils/MsgUtil'
import ConfigMap from 'socialuni/const/ConfigMap'
import PlatformUtils from 'socialuni/utils/PlatformUtils'
import UniUtil from 'socialuni/utils/UniUtil'
import Alert from 'socialuni/utils/Alert'
import CenterUserDetailRO from 'socialuni/model/social/CenterUserDetailRO'
import UPopup from 'uview-ui/components/u-popup/u-popup'

// todo 后台可控制是否显示轮播图

@Component({
  components: {
    UPopup
  }
})
export default class TalkOperate extends Vue {
  @socialAppStore.State('appConfig') readonly appConfig: object
  @socialAppStore.State('reportTypes') readonly reportTypes: string[]
  @socialUserStore.State('user') readonly user: CenterUserDetailRO
  @socialTalkStore.State('commentActionShow') readonly commentVisible: boolean
  @socialTalkStore.State('reportDialogShow') readonly dialogVisible: boolean
  @socialTalkStore.State('comment') readonly comment: CommentVO
  @socialTalkStore.State('reportContentType') readonly reportContentType: string
  @socialTalkStore.State('talk') readonly talk: TalkVO

  pornInfo: string = ReportType.pornInfo
  reportType: string = ReportType.pornInfo
  reportContent = ''
  // 被举报次数大于多少，则隐藏
  @socialConfigStore.Getter(ConfigMap.reportHideCountKey) reportHideCount: number

  reportDialogClose () {
    socialTalkModule.reportDialogShow = false
    this.initData()
  }

  addReport () {
    const reportAdd: ReportAddVO = new ReportAddVO(this.reportContentType, this.reportType, this.reportContent)
    if (this.reportContentType === ReportContentType.talk) {
      reportAdd.contentId = this.talk.id
    } else {
      reportAdd.contentId = this.comment.id
    }
    if (ReportType.other === this.reportType && !this.reportContent) {
      Alert.hint('选择其他违规时，请您补充观点')
    } else {
      ReportAPI.addReportAPI(reportAdd).then((res: any) => {
        if (this.reportContentType === ReportContentType.comment) {
          const reportNum: number = this.comment.reportNum + 1
          if (reportNum >= this.reportHideCount) {
            this.frontDeleteComment()
          }
        } else {
          const reportNum: number = this.talk.reportNum + 1
          if (reportNum >= this.reportHideCount) {
            this.$emit('deleteTalk', this.talk.id)
          }
        }
        // 必须最后清空因为前面还要使用做判断
        this.reportDialogClose()
        Alert.hint(res.data)
        PlatformUtils.requestSubscribeReport()
      })
    }
  }

  deleteComment () {
    this.frontDeleteComment()
    TalkAPI.deleteCommentAPI(this.comment.id).then(() => {
      this.initData()
    })
  }

  copyText () {
    UniUtil.textCopy(this.comment.content)
    this.closeActionAndInitData()
  }

  closeActionAndInitData () {
    this.commentActionClose()
    this.initData()
  }

  commentActionClose () {
    socialTalkModule.commentActionShow = false
  }

  // 前端删除comment
  frontDeleteComment () {
    this.talk.comments.splice(this.talk.comments.findIndex(comment => comment.id === this.comment.id), 1)
  }

  // 用户自己删除
  userDeleteComment () {
    this.commentActionClose()
    Alert.confirm('是否确定删除此条评论，此操作无法恢复').then(() => {
      this.deleteComment()
    })
  }

  openReportDialog () {
    if (this.user) {
      this.commentActionClose()
      socialTalkModule.reportContentType = ReportContentType.comment
      socialTalkModule.reportDialogShow = true
    } else {
      MsgUtil.unLoginMessage()
    }
  }

  reportTypeChange ({ target }) {
    this.reportType = target.value
  }

  initData () {
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
