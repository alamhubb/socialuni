import {fileURLToPath, URL} from 'node:url'

import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import markdown from 'vite-plugin-md'
// vite.config.ts
import Unocss from 'unocss/vite'
import UnocssRuleUtil from "./src/constant/styles/UnocssRuleUtil";
import ColorStyles from "./src/constant/styles/ColorStyles";
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
            theme: {
                colors: {
                    ...ColorStyles.themeColors
                }
            },
            rules: [
                [/^p(.)(-(\D+))?$/, match => UnocssRuleUtil.getSizeStyles('padding', match)],
                [/^p(.)-(\d+)(\D+)?$/, match => UnocssRuleUtil.getNumStyles('padding', match)],
                [/^m(.)(-(\D+))?$/, match => UnocssRuleUtil.getSizeStyles('margin', match)],
                [/^m(.)-(\d+)(\D+)?$/, match => UnocssRuleUtil.getNumStyles('margin', match)],
                [/^(.)-(\d+)(\D+)?$/, match => UnocssRuleUtil.getDirectionStyles('margin', match)],
                [/^font-(\d+)(\D+)?$/, match => {
                    return ({'font-size': `${match[1]}${match[2] || 'px'}`})
                }],
                [/^w(\d+)(\D+)?$/, match => {
                    return ({'width': `${match[1]}${match[2] || 'px'}`})
                }],
                [/^h(\d+)(\D+)?$/, match => {
                    return ({'height': `${match[1]}${match[2] || 'px'}`})
                }]
                ,
                [/^size(\d+)(\D+)?$/, match => {
                    return ({
                        'width': `${match[1]}${match[2] || 'px'}`,
                        'height': `${match[1]}${match[2] || 'px'}`
                    })
                }]
            ]
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
