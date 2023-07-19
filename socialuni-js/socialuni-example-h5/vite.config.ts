import {fileURLToPath, URL} from 'node:url'

import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import nodeResolve from "@rollup/plugin-node-resolve";

// https://vitejs.dev/config/
export default defineConfig({
    plugins: [vue(), nodeResolve()],
    server: {
        port: 8085
    },
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('./src', import.meta.url))
        }
    }
})
