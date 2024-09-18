import {fileURLToPath, URL} from 'node:url'
import {defineConfig} from 'vite'
import transformIoc from "./viteplugin/index";
import Inspect from 'vite-plugin-inspect'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
    esbuild: {
        target: 'es2022',
        sourcemap: true
    },
    build: {
        // sourcemap: false,
    },
    plugins: [
        vue(),
        Inspect(),
        // inspectPlugin(),
        transformIoc(),
    ],
    resolve: {
        alias: {
            '@':
                fileURLToPath(new URL('./src', import.meta.url))
        }
    }
})
