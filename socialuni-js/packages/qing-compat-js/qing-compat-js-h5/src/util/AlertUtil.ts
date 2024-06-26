import {ElMessageBox} from 'element-plus'
import {SocialuniAlertUtilInterface} from "qing-compat-js/src/interface/SocialuniAlertUtilInterface";

class AlertUtil implements SocialuniAlertUtilInterface {
    confirm(message: string, okLabel = '确定', cancel = '取消') {
        ElMessageBox.close()
        return ElMessageBox.confirm(message, {
            type: 'info',
            distinguishCancelAndClose: true,
            confirmButtonText: okLabel,
            cancelButtonText: cancel
        })
    }

    hint(msg: string, okLabel?: string) {
        ElMessageBox.close()
        return ElMessageBox.alert(msg, okLabel, {
            type: 'info'
        })
    }

    info(message: string, title?: string) {
        ElMessageBox.close()
        return ElMessageBox.alert(message, title, {
            type: 'info'
        })
    }

    warning(message: string, okLabel = '确定', cancel = '取消') {
        ElMessageBox.close()
        return ElMessageBox.confirm(message, {
            type: 'warning'
        })
    }

    error(message: string) {
        ElMessageBox.close()
        ElMessageBox.alert(message, {
            type: 'error'
        })
        throw new Error(message)
    }

    success(message: string, okLabel = '确定', cancel = '取消') {
        ElMessageBox.close()
        return ElMessageBox.confirm(message, {
            type: 'success'
        })
    }
}

const alertUtil = new AlertUtil()
export default alertUtil
