import {defineConfig} from 'vite'
import uni from '@dcloudio/vite-plugin-uni'
import requireTransform from 'vite-plugin-require-transform';
import commonjs from "@rollup/plugin-commonjs";
import {fileURLToPath, URL} from "node:url";
import purgecss from '@fullhuman/postcss-purgecss'
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
    css: {
        postcss: {
            plugins: [purgecss({
                // require('@fullhuman/postcss-purgecss')({
                content: ['./public/**/*.html', './src/**/*.vue'],
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
                    /q-.*/,
                    /u-.*/,
                    /uni-.*/,
                    /mdi-.*/,
                    /page/
                ]
            })]
        }
    },
    server: {
        host: "0.0.0.0"
    },
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('./src', import.meta.url)),
            'stream': 'stream-browserify'
        }
    }
})
