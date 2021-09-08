<template>
  <uni-popup ref="reportDialog" :custom="true" :mask-click="false">
    <view class="uni-tip">
      <radio-group class="uni-list" @change="reportTypeChange">
        <label class="uni-list-cell flex-row uni-list-cell-pd"
               v-for="report in reportTypes" :key="report">
          <view>
            <radio :id="report" :value="report" :checked="report===pornInfo"></radio>
          </view>
          <view>
            <label class="ml-10px" :for="report">
              <text>{{report}}</text>
            </label>
          </view>
        </label>
      </radio-group>
      <view class="uni-textarea bd-1 bd-radius-xs">
        <textarea placeholder="其他违规必填，其他情况选填，可详细陈述观点" v-model.trim="reportContent"
                  :show-confirm-bar="false"
        />
      </view>
      <view class="uni-tip-group-button">
        <button class="uni-tip-button w40p" type="default" @click="closeDialogAndInitData" :plain="true">取消
        </button>
        <button class="uni-tip-button w40p" type="primary" @click="addReport" :disabled="!reportType">确定
        </button>
      </view>
    </view>
  </uni-popup>
</template>

<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'
import { namespace } from 'vuex-class'
import ReportType from 'socialuni/const/ReportType'
import ReportContentType from 'socialuni/const/ReportContentType'
import MessageVO from 'socialuni/model/message/MessageVO'
import ReportAddVO from 'socialuni/model/report/ReportAddVO'
import ReportAPI from 'socialuni/api/ReportAPI'
import CenterUserDetailRO from 'socialuni/model/social/CenterUserDetailRO'
import MsgUtil from 'socialuni/utils/MsgUtil'
import PlatformUtils from 'socialuni/utils/PlatformUtils'
import Alert from 'socialuni/utils/Alert'
import { chatModule } from 'socialuni/store'

const appStore = namespace('app')
const userStore = namespace('user')

  @Component
export default class ReportDialog extends Vue {
    public $refs!: {
      reportDialog: any;
    }

    @appStore.State('reportTypes') reportTypes: string[]
    @appStore.State('appConfig') readonly appConfig: object
    @Prop() readonly reportInfo: MessageVO
    @Prop() readonly reportInfoType: string
    @userStore.State('user') user: CenterUserDetailRO
    reportType: string = ReportType.pornInfo
    pornInfo: string = ReportType.pornInfo
    reportContent = ''
    reportContentType: string = ReportContentType.message

    openReport () {
      if (this.user) {
        this.$refs.reportDialog.open()
      } else {
        MsgUtil.unLoginMessage()
      }
    }

    closeDialogAndInitData () {
      this.$refs.reportDialog.close()
      this.initReportData()
    }

    initReportData () {
      this.reportContent = ''
      this.reportContentType = ReportContentType.message
      this.reportType = ReportType.pornInfo
    }

    reportTypeChange ({ target }) {
      this.reportType = target.value
    }

    addReport () {
      const reportAdd: ReportAddVO = new ReportAddVO(this.reportContentType, this.reportType, this.reportContent)
      reportAdd.contentId = this.reportInfo.id
      if (ReportType.other === this.reportType && !this.reportContent) {
        Alert.hint('选择其他违规时，请您补充观点')
      } else {
        ReportAPI.addReportAPI(reportAdd).then((res: any) => {
          chatModule.deleteMsgAction(reportAdd.contentId)
          // 调用删除内容
          // 关闭弹框病初始化数据
          this.closeDialogAndInitData()
          Alert.hint(res.data)
          PlatformUtils.requestSubscribeReport()
        })
      }
    }
}
</script>
