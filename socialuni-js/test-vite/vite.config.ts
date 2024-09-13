import {fileURLToPath, URL} from 'node:url'

import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import transformIoc from "./viteplugin/index";
import Inspect from 'vite-plugin-inspect'


// https://vitejs.dev/config/
export default defineConfig({
    esbuild: {
        sourcemap: false,
    },
    build: {
        sourcemap: false,
    },
    plugins: [
        Inspect(),
        vue(),
        // inspectPlugin(),
        transformIoc(),
    ],
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('./src', import.meta.url))
        }
    }
})