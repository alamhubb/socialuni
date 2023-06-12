<template>
  <q-popup ref="reportDialog" title="举报" @confirm="addReport" custom-close>
    <view class="uni-tip">
      <view class="uni-tip-content">
        <radio-group class="uni-list" @change="reportTypeChange">
          <label class="uni-list-cell flex-row uni-list-cell-pd"
                 v-for="report in reportTypes" :key="report">
            <view>
              <radio :id="report" :value="report" :checked="report===pornInfo"></radio>
            </view>
            <view>
              <label class="ml-10" :for="report">
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
    </view>
  </q-popup>
</template>

<script lang="ts">
import {Emit, Options, Vue} from 'vue-property-decorator'
import QPopup from "socialuni-ui-uni/src/components/QPopup/QPopup.vue";
import ReportType from "socialuni-constant/constant/ReportType";
import ReportAddVO from "socialuni-api-base/src/model/report/ReportAddVO";
import {socialuniConfigModule} from "socialuni-app/src/store/SocialuniConfigModule";
import {socialuniUserModule} from "socialuni-user/src/store/SocialuniUserModule";
import UserCheckUtil from "socialuni-user/src/util/UserCheckUtil";
import SocialuniAppUtil from "socialuni-native-util/src/util/SocialuniAppUtil";
import ReportAPI from "socialuni-app-api/src/api/ReportAPI";

@Options({
  components: {QPopup}
})
export default class SocialuniReportDialog extends Vue {
  $refs!: {
    reportDialog: QPopup;
  }

  get reportTypes() {
    return socialuniConfigModule.reportTypes
  }

  get mineUser() {
    return socialuniUserModule.mineUser
  }

  reportType: string = ReportType.pornInfo
  pornInfo: string = ReportType.pornInfo
  reportContent = ''
  reportContentType = null
  reportContentId = null

  openReportDialog(reportContentType: string, reportContentId: string) {
    UserCheckUtil.checkUserLogin()
    this.reportContentType = reportContentType
    this.reportContentId = reportContentId
    this.reportContent = ''
    this.reportType = ReportType.pornInfo
  }

  reportTypeChange({detail}) {
    this.reportType = detail.value
  }

  async addReport() {
    const reportAdd: ReportAddVO = new ReportAddVO(this.reportContentType, this.reportType, this.reportContent)
    reportAdd.contentId = this.reportContentId
    if (ReportType.other === this.reportType && !this.reportContent) {
      return SocialuniAppUtil.AlertUtil.hint('选择其他违规时，请您补充观点')
    }
    await ReportAPI.addReportAPI(reportAdd)
      SocialuniAppUtil.AlertUtil.hint('举报成功')
    this.closeDialog()
    this.deleteContent()
  }

  closeDialog() {
    this.$refs.reportDialog.close()
  }

  @Emit()
  deleteContent() {
    return this.reportContentId
  }
}


</script>
