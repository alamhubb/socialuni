import {fileURLToPath, URL} from 'node:url'

import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import markdown from 'vite-plugin-md'
// vite.config.ts
import Unocss from 'unocss/vite'
import StylesColor from "./src/constant/styles/StylesColor";
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
                //\d 支持 pd-20 数字， (.+)? 支持 pd-20px pd-20vm pd-20%
                [/^pd-?(\d+)?(.+)?$/, match => ({padding: `${match[2]||20}${match[3] || 'px'}`})],
                [/^py-?(\d+)?(.+)?$/, match => ({'padding-top': `${match[2]||20}${match[3] || 'px'}`, 'padding-bottom': `${match[2]||20}${match[3] || 'px'}`})],
                [/^px-?(\d+)?(.+)?$/, match => ({'padding-left': `${match[2]||20}${match[3] || 'px'}`, 'padding-right': `${match[2]||20}${match[3] || 'px'}`})],
                [/^pt-?(\d+)?(.+)?$/, match => ({'padding-top': `${match[2]||20}${match[3] || 'px'}`})],
                [/^pb-?(\d+)?(.+)?$/, match => ({'padding-bottom': `${match[2]||20}${match[3] || 'px'}`})],
                [/^pl-?(\d+)?(.+)?$/, match => ({'padding-left': `${match[2]||20}${match[3] || 'px'}`})],
                [/^pr-?(\d+)?(.+)?$/, match => ({'padding-right': `${match[2]||20}${match[3] || 'px'}`})],
                [/^ptl-?(\d+)?(.+)?$/, match => ({'padding-top': `${match[2]||20}${match[3] || 'px'}`, 'padding-left': `${match[2]||20}${match[3] || 'px'}`})],
                [/^ptr-?(\d+)?(.+)?$/, match => ({'padding-top': `${match[2]||20}${match[3] || 'px'}`, 'padding-right': `${match[2]||20}${match[3] || 'px'}`})],
                [/^pbl-?(\d+)?(.+)?$/, match => ({'padding-bottom': `${match[2]||20}${match[3] || 'px'}`, 'padding-left': `${match[2]||20}${match[3] || 'px'}`})],
                [/^pbr-?(\d+)?(.+)?$/, match => ({'padding-bottom': `${match[2]||20}${match[3] || 'px'}`, 'padding-right': `${match[2]||20}${match[3] || 'px'}`})],
                [/^md-?(\d+)?(.+)?$/, match => ({margin: `${match[2]||20}${match[3] || 'px'}`})],
                [/^my-?(\d+)?(.+)?$/, match => ({'margin-top': `${match[2]||20}${match[3] || 'px'}`, 'margin-bottom': `${match[2]||20}${match[3] || 'px'}`})],
                [/^mx-?(\d+)?(.+)?$/, match => ({'margin-left': `${match[2]||20}${match[3] || 'px'}`, 'margin-right': `${match[2]||20}${match[3] || 'px'}`})],
                [/^mt-?(\d+)?(.+)?$/, match => ({'margin-top': `${match[2]||20}${match[3] || 'px'}`})],
                [/^mb-?(\d+)?(.+)?$/, match => ({'margin-bottom': `${match[2]||20}${match[3] || 'px'}`})],
                [/^ml-?(\d+)?(.+)?$/, match => ({'margin-left': `${match[2]||20}${match[3] || 'px'}`})],
                [/^mr-?(\d+)?(.+)?$/, match => ({'margin-right': `${match[2]||20}${match[3] || 'px'}`})],
                [/^mtl-?(\d+)?(.+)?$/, match => ({'margin-top': `${match[2]||20}${match[3] || 'px'}`, 'margin-left': `${match[2]||20}${match[3] || 'px'}`})],
                [/^mtr-?(\d+)?(.+)?$/, match => ({'margin-top': `${match[2]||20}${match[3] || 'px'}`, 'margin-right': `${match[2]||20}${match[3] || 'px'}`})],
                [/^mbl-?(\d+)?(.+)?$/, match => ({'margin-bottom': `${match[2]||20}${match[3] || 'px'}`, 'margin-left': `${match[2]||20}${match[3] || 'px'}`})],
                [/^mbr-?(\d+)?(.+)?$/, match => ({'margin-bottom': `${match[2]||20}${match[3] || 'px'}`, 'margin-right': `${match[2]||20}${match[3] || 'px'}`})],
                [/^font-(\d+)((.+)?)$/, match => ({'font-size': `${match[2]||20}${match[3] || 'px'}`})],
                [/^bg-(.+)$/, match => ({'background': StylesColor.getColorByName(match[1])})],
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
