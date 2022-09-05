import {fileURLToPath, URL} from 'node:url'

import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import markdown from 'vite-plugin-md'
import unocss from 'unocss/vite'
import ColorStyles from "./src/styles/jsStyle/ColorStyles";
import UnocssRuleUtil from "./src/styles/jsStyle/UnocssRuleUtil";
import tinycolor2 from "tinycolor2";
import markdownItAnchor from 'markdown-it-anchor'

// vite.config.ts

// https://vitejs.dev/config/
export default defineConfig({
    base: '/',
    plugins: [
        vue({
            include: [/\.vue$/, /\.md$/]
        }),
        markdown({
            markdownItOptions: {
                html: true,
                linkify: true,
                typographer: true,
            },
            markdownItSetup(md) {
                // add anchor links to your H[x] tags
                md.use(markdownItAnchor)
            },
        }),
        unocss({
            theme: {
                colors: {
                    ...ColorStyles.colors
                }
            },
            rules: [
                [/^p(.)(-(\D+))?$/, match => UnocssRuleUtil.getSizeStyles('padding', match)],
                [/^p(.)(-(\d+)(\D+)?)?$/, match => UnocssRuleUtil.getNumStyles('padding', match)],
                [/^m(.)(-(\D+))?$/, match => UnocssRuleUtil.getSizeStyles('margin', match)],
                [/^m(.)(-(\d+)(\D+)?)?$/, match => UnocssRuleUtil.getNumStyles('margin', match)],
                [/^(.)-(\d+)(\D+)?$/, match => UnocssRuleUtil.getDirectionStyles('', match)],
                [/^b([dtblfxy])(-(\w+))?$/, (match, theme) => UnocssRuleUtil.getBorderStyles(match, theme)],
                [/^b([dtblfxy])(-(\w+))?-dark$/, (match, theme) => UnocssRuleUtil.getBorderDarkStyles(match, theme)],
                [/^b([dtblfxy])(-(\w+))?-light$/, (match, theme) => UnocssRuleUtil.getBorderLightStyles(match, theme)],
                [/^b([dtblfxy])-(\d+)?$/, (match, theme) => UnocssRuleUtil.getBorderWidthStyles(match, theme)],
                [/^b([dtblfxy])-radius(-(\d+)(\D+)?)?$/, match => UnocssRuleUtil.getBorderRadiusStyles(match)],
                [/^bg-(\w+)-light$/, match => ({'background': tinycolor2.mix(ColorStyles.colors[match[1]], ColorStyles.themeColors.white, 90)})],
                [/^bg-(\w+)-dark$/, match => ({'background': tinycolor2.mix(ColorStyles.colors[match[1]], ColorStyles.themeColors.black, 10)})],
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
                [/^btn$/, () => `bg-white bd color-main py-10 px-20 bd-radius-20`],
                [/^btn-(\w+)?$/, ([, color]) => `bg-${color} color-white bd-${color}-dark py-10 px-20 bd-radius-20`],
                [/^btn-(\w+)-light?$/, ([, color]) => `bg-${color}-light color-main bd py-10 px-20 bd-radius-20`],
                [/^btn-(\w+)-plain?$/, ([, color]) => `bg-white color-${color} bd-${color} py-10 px-20 bd-radius-20`],
                [/^note-(\w+)?$/, ([, c]) => `bl-${c} bl-5 bg-${c}-light py-8 px-16 bd-radius`],
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
