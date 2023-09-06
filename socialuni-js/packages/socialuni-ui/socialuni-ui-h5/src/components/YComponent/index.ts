import {App} from "vue";
import YTableColumn from "./YTableColumn/YTableColumn.vue";


const YComponents = {
    async install(app: App) {
        app.component('YTableColumn', YTableColumn)
    }
}

export default YComponents
