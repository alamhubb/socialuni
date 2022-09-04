import {fileURLToPath, URL} from 'node:url'

import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import markdown from 'vite-plugin-md'
import unocss from 'unocss/vite'
import ColorStyles from "./src/styles/jsStyle/ColorStyles";
import UnocssRuleUtil from "./src/styles/jsStyle/UnocssRuleUtil";
import tinycolor2 from "tinycolor2";
// vite.config.ts

// https://vitejs.dev/config/
export default defineConfig({
    base: './',
    plugins: [
        vue({
            include: [/\.vue$/, /\.md$/]
        }),
        markdown(),
        unocss({
            theme: {
                colors: {
                    ...ColorStyles.colors
                }
            },
            rules: [
                [/^p(.)(-(\d+)(\D+)?)?$/, match => UnocssRuleUtil.getNumStyles('padding', match)],
                [/^m(.)(-(\d+)(\D+)?)?$/, match => UnocssRuleUtil.getNumStyles('margin', match)],
                [/^(.)-(\d+)(\D+)?$/, match => UnocssRuleUtil.getDirectionStyles('', match)],
                [/^b(.)(-(.+))?$/, (match, theme) => UnocssRuleUtil.getBorderStyles(match, theme)],
                [/^b(.)-(\d+)?$/, (match, theme) => UnocssRuleUtil.getBorderWidthStyles(match, theme)],
                [/^b(.)-radius(-(\d+)(\D+)?)?$/, match => UnocssRuleUtil.getBorderRadiusStyles(match)],
                [/^bg-(.+)_light$/, match => ({'background': tinycolor2.mix(ColorStyles.colors[match[1]], ColorStyles.themeColors.white, 90)})],
                [/^bg-(.+)_dark$/, match => ({'background': tinycolor2.mix(ColorStyles.colors[match[1]], ColorStyles.themeColors.black, 10)})],
                [/^font-(\d+)(\D+)?$/, match => {
                    return ({'font-size': `${match[1]}${match[2] || 'px'}`})
                }],
                [/^w(\d+)(\D+)?$/, match => {
                    return ({'width': `${match[1]}${match[2] || 'px'}`})
                }],
                [/^h(\d+)(\D+)?$/, match => {
                    return ({'height': `${match[1]}${match[2] || 'px'}`})
                }],
                [/^size(\d+)(\D+)?$/, match => {
                    return ({
                        'width': `${match[1]}${match[2] || 'px'}`,
                        'height': `${match[1]}${match[2] || 'px'}`
                    })
                }],
            ],
            postprocess: (util) => {
                util.entries.forEach((i) => {
                    const value = i[1]
                    if (value && typeof value === 'string' && /^-?[\.\d]+rem$/.test(value))
                        i[1] = `${+value.slice(0, -3) * 4}px`
                })
            },
            shortcuts: [
                [/^btn(-(.+))?$/, ([, , c]) => `${c ? 'bg-' + c + ' color-white bd-' + c : 'bg-white bd color-main'}  py-10 px-20 bd-radius-20 use-click`],
                [/^note(-(.+))?$/, ([, , c]) => `bl-${c} bl-5 bg-${c}_light py-8 px-16 bd-radius`],
            ]

        })
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
