import {defineConfig} from 'vite'
import uni from '@dcloudio/vite-plugin-uni'

import {fileURLToPath, URL} from "node:url";
import socialuniPlatformAutoImportPlugin from "vite-plugin-socialuni-platform-auto-import/src/index";

const IN_PRODUCTION = process.env.NODE_ENV === 'production'

let plugins = []

if (IN_PRODUCTION) {
    plugins = [
        require('@fullhuman/postcss-purgecss')({
            // rejected: true,
            // require('@fullhuman/postcss-purgecss')({
            // content: ['./public/**/*.html', './src/**/*.vue'],
            content: ['./public/**/*.html', './node_modules/socialuni-*/**/*.scss', './node_modules/@dcloudio/**/*.scss', './node_modules/@dcloudio/**/*.vue', './node_modules/socialuni-*/**/*.vue', './src/**/*.vue', './src/**/*.scss'],
            defaultExtractor(content) {
                const contentWithoutStyleBlocks = content.replace(
                    /<style[^]+?<\/style>/gi,
                    ''
                )
                return (
                    contentWithoutStyleBlocks.match(
                        /[A-Za-z0-9-_/:]*[A-Za-z0-9-_/]+/g
                    ) || []
                )
            },
            safelist: [
                /-(leave|enter|appear)(|-(to|from|active))$/,
                /^(?!(|.*?:)cursor-move).+-move$/,
                /^router-link(|-exact)-active$/,
                /data-v-.*/,
                /uicon-.*/,
                /uniui-.*/,
                /q-.*/,
                /u-.*/,
                /uni-.*/,
                /mdi-.*/,
                /page/
            ]
        })
    ]
}

// vite.config.ts
// https://vitejs.dev/config/
export default defineConfig({
    base: '/',
    plugins: [
        uni(),
        socialuniPlatformAutoImportPlugin()
    ],
    // build: {
    //     minify: false
    // },
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
