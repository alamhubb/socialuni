import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import {fileURLToPath} from "node:url";
import socialuniPlatformAutoImportPlugin from "vite-plugin-socialuni-platform-auto-import/src";

// https://vitejs.dev/config/
export default defineConfig({
    define: {
        __DEV__: false,
        __FEATURE_PROD_DEVTOOLS__: false,
        __BROWSER__: true,
    },
    plugins: [
        vue(),
        socialuniPlatformAutoImportPlugin()
    ],
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('./src', import.meta.url))
        }
    }
})
