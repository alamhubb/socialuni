import {App} from "vue";

export const QingCompatJs = {
    async install(app: App) {
        const modules = import.meta.glob('../../*/src/**/*.ts', {eager: true});
        console.log(modules)
    }
}
