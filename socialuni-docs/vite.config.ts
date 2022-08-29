import {fileURLToPath, URL} from 'node:url'

import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
// import md from '@socialuni/vite-plugin-md'
import markdown from './@socialuni/vite-plugin-md/index'
// import Markdown from './vite-plugin-md/index'
import vitePluginRequire from "./vite-plugin-require/index"

// https://vitejs.dev/config/
export default defineConfig({
    plugins: [vue({
        include: [/\.vue$/, /\.md$/], // <--
    }), markdown(), vitePluginRequire()],
    server: {
        host: "0.0.0.0"
    },
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('./src', import.meta.url))
        }
    }
})
