<template>
    <q-popup ref="reportDialog" title="举报" @confirm="addReport" custom-close>
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
        </div>
    </q-popup>
</template>

<script lang="ts">
import {Emit, Component, Vue, toNative} from 'vue-facing-decorator'
import QPopup from "qing-ui/src/components/QPopup.vue";
import ReportType from "socialuni-constant/constant/ReportType";
import ReportAddVO from "socialuni-api-base/src/model/report/ReportAddVO";
import {socialuniConfigModule} from "socialuni-app-sdk/src/store/SocialuniConfigModule";
import {socialuniUserModule} from "socialuni-user-sdk/src/store/SocialuniUserModule";
import QingAppUtil from "qingjs/src/util/QingAppUtil";
import ReportAPI from "socialuni-app-api/src/api/ReportAPI";
import UserMsgUtil from "socialuni-user-sdk/src/util/UserMsgUtil";

@toNative
@Component({
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
        UserMsgUtil.unLoginMessage()
        this.reportContentType = reportContentType
        this.reportContentId = reportContentId
        this.reportContent = ''
        this.reportType = ReportType.pornInfo
        this.$refs.reportDialog.open()
    }

    reportTypeChange({detail}) {
        this.reportType = detail.value
    }

    async addReport() {
        const reportAdd: ReportAddVO = new ReportAddVO(this.reportContentType, this.reportType, this.reportContent)
        reportAdd.contentId = this.reportContentId
        if (ReportType.other === this.reportType && !this.reportContent) {
            return QingAppUtil.AlertUtil.hint('选择其他违规时，请您补充观点')
        }
        await ReportAPI.addReportAPI(reportAdd)
        QingAppUtil.AlertUtil.hint('举报成功')
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
