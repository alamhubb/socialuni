import {App, defineComponent} from "vue";
import CommonEventUtil from "./util/CommonEventUtil";
import UniappPageLifecycleConst from "./UniappPageLifecycleConst";

const UniappAPI = {
    async install(app: App) {
        const shareComponent = defineComponent({
            created() {
                CommonEventUtil.emit(UniappPageLifecycleConst.getOnLoadKey(this.$route), this.$route.query)
            }
        })
        app.mixin(shareComponent)
    }
}

export default UniappAPI
