import {App} from "vue";

export const QingCompatJs = {
    async install(app: App) {
        async function loadModules() {
            const modules = import.meta.glob('./**/*.ts', {eager: true});
            const modules1 = import.meta.glob('../../*-h5/src/**/*.ts', {eager: true});
            console.log(modules)
            console.log(modules1)
        }
        loadModules()
    }
}
