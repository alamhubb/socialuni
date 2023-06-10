import {defineConfig} from 'vite'
import uni from '@dcloudio/vite-plugin-uni'
import requireTransform from 'vite-plugin-require-transform';
import commonjs from "@rollup/plugin-commonjs";
import {fileURLToPath, URL} from "node:url";

const IN_PRODUCTION = process.env.NODE_ENV === 'production'

let plugins = []
    plugins = [

    ]
// vite.config.ts
// https://vitejs.dev/config/
export default defineConfig({
    base: '/',
    plugins: [
        uni(),
        commonjs(),
        requireTransform({
            fileRegex: /.js$|.vue$/
        }),
    ],
    server: {
        host: "0.0.0.0"
    },
    css: {
        postcss: {
            plugins: plugins
        }
    },
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('./src', import.meta.url)),
        }
    }
})
