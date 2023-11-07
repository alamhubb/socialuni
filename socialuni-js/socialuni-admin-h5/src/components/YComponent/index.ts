import {App} from "vue";
import YTableColumn from "./YTableColumn/YTableColumn.vue";
import YTableInput from "./YTableInput/YTableInput.vue";


const YComponents = {
    async install(app: App) {
        app.component('YTableColumn', YTableColumn)
        app.component('YTableInput', YTableInput)
    }
}

export default YComponents
