import {fileURLToPath, URL} from 'node:url'

import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'
import nodeResolve from "@rollup/plugin-node-resolve";
import socialuniPlatformAutoImportPlugin from "vite-plugin-socialuni-platform-auto-import/src/index";

// https://vitejs.dev/config/
export default defineConfig({
    plugins: [
        vue(),
        vueJsx(),
        nodeResolve(),
        socialuniPlatformAutoImportPlugin()
    ],
    server: {
        port: 8085,
        // host: '0.0.0.0',
        proxy: {
            '/api': {
                target: 'https://api.sd-rtn.com',
                // 如果接口跨域，需要进行这个参数配置
                changeOrigin: true,
                rewrite: (path) => path.replace(/^\/api/, '')
            }

        }
    },
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('./src', import.meta.url))
        }
    },
    css: {
        preprocessorOptions: {
            scss: {
            }
        }
    },
})
