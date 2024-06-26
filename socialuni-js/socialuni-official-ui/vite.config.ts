import {fileURLToPath, URL} from 'node:url'

import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import markdown from 'unplugin-vue-markdown/vite'
import markdownItAnchor from 'markdown-it-anchor'
import markdownItPrism from 'markdown-it-prism'
import markdownItShiki from 'markdown-it-shiki'
import socialuniPlatformAutoImportPlugin from "vite-plugin-socialuni-platform-auto-import/src";
// vite.config.ts

// https://vitejs.dev/config/
export default defineConfig({
    base: '/',
    plugins: [
        vue({
            include: [/\.vue$/, /\.md$/]
        }),
        socialuniPlatformAutoImportPlugin(),
        markdown({
            markdownItOptions: {
                html: true,
                linkify: true,
                typographer: true,
            },
            markdownItSetup(md) {
                // add anchor links to your H[x] tags
                md.use(markdownItAnchor)
                // add code syntax highlighting with Prism
                md.use(markdownItPrism)
                md.use(markdownItShiki, {
                    theme: 'material-palenight'
                })
            },
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
