import {fileURLToPath, URL} from 'node:url'

import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import markdown from 'vite-plugin-md'
// vite.config.ts
import Unocss from 'unocss/vite'
// https://vitejs.dev/config/
export default defineConfig({
    plugins: [
        vue({
            include: [/\.vue$/, /\.md$/]
        }),
        markdown(),
        Unocss({
            presets: [], // disable default preset
            rules: [
                [/^pd-(\d+)$/, match => ({ padding: `${match[1]}px` })],
                [/^pl-(\d+)$/, match => ({ 'padding-left': `${match[1]}px` })],
                [/^pr-(\d+)$/, match => ({ 'padding-left': `${match[1]}px` })],
                [/^pt-(\d+)$/, match => ({ 'padding-left': `${match[1]}px` })],
            ],
        }),
    ],
    server: {
        host: "0.0.0.0"
    },
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('./src', import.meta.url))
        }
    }
})
