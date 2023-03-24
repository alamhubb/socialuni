import {defineConfig} from 'vite'
import uni from '@dcloudio/vite-plugin-uni'
import requireTransform from 'vite-plugin-require-transform';
import commonjs from "@rollup/plugin-commonjs";
import {fileURLToPath, URL} from "node:url";

const IN_PRODUCTION = process.env.NODE_ENV === 'production'

let plugins = []
    plugins = [
        require('@fullhuman/postcss-purgecss')({
            rejected: true,
            // require('@fullhuman/postcss-purgecss')({
            content: ['./public/**/*.html', './src/**/*.vue'],
            // content: ['./public/**/*.html', './node_modules/socialuni-view/**/*.scss', './node_modules/@dcloudio/**/*.scss', './node_modules/@dcloudio/**/*.vue', './node_modules/socialuni-view/**/*.vue', './src/**/*.vue', './src/**/*.scss'],
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
