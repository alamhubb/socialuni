import {fileURLToPath, URL} from 'node:url'

import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import nodeResolve from "@rollup/plugin-node-resolve";
import socialuniPlatformAutoImportPlugin from "@socialuni/vite-plugin-socialuni-platform-auto-import/src/index";
import * as https from "https";
import * as fs from "fs";

// https://vitejs.dev/config/
export default defineConfig({
    plugins: [vue(), nodeResolve(), socialuniPlatformAutoImportPlugin()],
    server: {
        port: 8085,
        host: '0.0.0.0',
        /*https: {
            key: fs.readFileSync('./test/ssl/localtest.socialuni.cn.key'),
            cert: fs.readFileSync('./test/ssl/localtest.socialuni.cn_bundle.crt'),
        },*/
        proxy: {
            '/api': {
                target: 'https://api.sd-rtn.com',
                // 如果接口跨域，需要进行这个参数配置
                changeOrigin: true,
                rewrite: (path) => path.replace(/^\/api/, '')
            },
            '/music': {
                target: 'https://music.163.com',
                // 如果接口跨域，需要进行这个参数配置
                changeOrigin: true,
                rewrite: (path) => path.replace(/^\/music/, '')
            }
        }
    },
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('./src', import.meta.url))
        }
    }
})
