import {fileURLToPath, URL} from 'node:url'

import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import mdString from 'vite-plugin-md-string'
import test from './testPlugin'
import vitePluginRequire from "./vite-plugin-require/index"

const url = fileURLToPath(new URL('./src', import.meta.url))
// https://vitejs.dev/config/
export default defineConfig({
    plugins: [vue(), mdString(), test(), vitePluginRequire()],
    server: {
        host: "0.0.0.0"
    },
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('./src', import.meta.url))
        }
    }
})
