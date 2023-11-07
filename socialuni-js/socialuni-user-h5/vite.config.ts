import {fileURLToPath, URL} from 'node:url'

import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'
import nodeResolve from "@rollup/plugin-node-resolve";
import socialuniPlatformAutoImportPlugin from "@socialuni/vite-plugin-socialuni-platform-auto-import/src/index";

// https://vitejs.dev/config/
export default defineConfig({
    plugins: [
        vue(),
        vueJsx(),
        nodeResolve(),
        socialuniPlatformAutoImportPlugin()
    ],
    server: {
        port: 8085
    },
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('./src', import.meta.url))
        }
    }
})
