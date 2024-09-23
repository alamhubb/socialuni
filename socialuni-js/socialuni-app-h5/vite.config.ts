import {fileURLToPath, URL} from 'node:url'
import {defineConfig} from 'vite'
import Inspect from 'vite-plugin-inspect'
import vue from '@vitejs/plugin-vue'
import typeIocJsPlugin from "typeiocjs/src";

// https://vitejs.dev/config/
export default defineConfig({
    esbuild: {
        target: 'es2022',
        sourcemap: true,
        tsconfigRaw: {
            compilerOptions: {
                useDefineForClassFields: true
            }
        }
    },
    build: {
        // sourcemap: false,
    },
    plugins: [
        vue(),
        // Inspect(),
        // inspectPlugin(),
        // typeIocJsPlugin(),
    ],
    resolve: {
        alias: {
            '@':
                fileURLToPath(new URL('./src', import.meta.url))
        }
    }
})
