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
                [/^pd-(\d+)$/, match => ({padding: `${match[1]}px`})],
                [/^py-(\d+)$/, match => ({'padding-top': `${match[1]}px`, 'padding-bottom': `${match[1]}px`})],
                [/^px-(\d+)$/, match => ({'padding-left': `${match[1]}px`, 'padding-right': `${match[1]}px`})],
                [/^pt-(\d+)$/, match => ({'padding-top': `${match[1]}px`})],
                [/^pb-(\d+)$/, match => ({'padding-bottom': `${match[1]}px`})],
                [/^pl-(\d+)$/, match => ({'padding-left': `${match[1]}px`})],
                [/^pr-(\d+)$/, match => ({'padding-right': `${match[1]}px`})],
                [/^ptl-(\d+)$/, match => ({'padding-top': `${match[1]}px`, 'padding-left': `${match[1]}px`})],
                [/^ptr-(\d+)$/, match => ({'padding-top': `${match[1]}px`, 'padding-right': `${match[1]}px`})],
                [/^pbl-(\d+)$/, match => ({'padding-bottom': `${match[1]}px`, 'padding-left': `${match[1]}px`})],
                [/^pbr-(\d+)$/, match => ({'padding-bottom': `${match[1]}px`, 'padding-right': `${match[1]}px`})],
                [/^md-(\d+)$/, match => ({margin: `${match[1]}px`})],
                [/^my-(\d+)$/, match => ({'margin-top': `${match[1]}px`, 'margin-bottom': `${match[1]}px`})],
                [/^mx-(\d+)$/, match => ({'margin-left': `${match[1]}px`, 'margin-right': `${match[1]}px`})],
                [/^mt-(\d+)$/, match => ({'margin-top': `${match[1]}px`})],
                [/^mb-(\d+)$/, match => ({'margin-bottom': `${match[1]}px`})],
                [/^ml-(\d+)$/, match => ({'margin-left': `${match[1]}px`})],
                [/^mr-(\d+)$/, match => ({'margin-right': `${match[1]}px`})],
                [/^mtl-(\d+)$/, match => ({'margin-top': `${match[1]}px`, 'margin-left': `${match[1]}px`})],
                [/^mtr-(\d+)$/, match => ({'margin-top': `${match[1]}px`, 'margin-right': `${match[1]}px`})],
                [/^mbl-(\d+)$/, match => ({'margin-bottom': `${match[1]}px`, 'margin-left': `${match[1]}px`})],
                [/^mbr-(\d+)$/, match => ({'margin-bottom': `${match[1]}px`, 'margin-right': `${match[1]}px`})],
                [/^font-(\d+)$/, match => ({'font-size': `${match[1]}px`})],
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
