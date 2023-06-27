import {App, defineComponent} from "vue";

const UniappAPI = {
    async install(app: App) {
        const shareComponent = defineComponent({
            created() {
            }
        })
        app.mixin(shareComponent)
    }
}

export default UniappAPI
