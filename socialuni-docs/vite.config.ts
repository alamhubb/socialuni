import {fileURLToPath, URL} from 'node:url'

import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import markdown from 'vite-plugin-md'
// vite.config.ts
import Unocss from 'unocss/vite'
import UnocssRules from "./src/constant/styles/UnocssRules";
// import UnocssRules from "./src/constant/styles/UnocssRules";
// https://vitejs.dev/config/
export default defineConfig({
    base: './',
    plugins: [
        vue({
            include: [/\.vue$/, /\.md$/]
        }),
        markdown(),
        // Unocss(),
        Unocss({
            presets: [],
            rules: UnocssRules.rules
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
