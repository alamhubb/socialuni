import {App, defineComponent} from "vue";
import CommonEventUtil from "./util/CommonEventUtil";
import UniappPageLifecycle from "./UniappPage";

const UniappAPI = {
    async install(app: App) {
        const shareComponent = defineComponent({
            beforeMount() {
                CommonEventUtil.emit(UniappPageLifecycle.onLoad)
            }
        })
        app.mixin(shareComponent)
    }
}

export default UniappAPI
