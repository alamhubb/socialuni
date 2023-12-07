import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import socialuniPlatformAutoImportPlugin from './index'
import {fileURLToPath} from "node:url";

// https://vitejs.dev/config/
export default defineConfig({
    plugins: [vue(), socialuniPlatformAutoImportPlugin()],
    resolve: {
    alias: {
        '@': fileURLToPath(new URL('./src', import.meta.url))
    }
}
})
