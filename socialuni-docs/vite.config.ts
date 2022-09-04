import {fileURLToPath, URL} from 'node:url'

import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import markdown from 'vite-plugin-md'
import unocss from 'unocss/vite'
import ColorStyles from "./src/constant/styles/ColorStyles";
import UnocssRuleUtil from "./src/constant/styles/UnocssRuleUtil";
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
                [/^b(.)(-(\D+))?$/, (match, theme) => UnocssRuleUtil.getBorderStyles(match, theme)],
                [/^b(.)-radius(-(\d+)(\D+)?)?$/, match => UnocssRuleUtil.getBorderRadiusStyles(match)],
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
                [/^btn(-(.*))?$/, ([, , c]) => `${c ? 'bg-' + c + ' color-white bd-' + c : 'bg-white bd color-main'}  py-10 px-20 bd-radius-20 use-click`],
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
