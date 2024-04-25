import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import {fileURLToPath} from "node:url";

// https://vitejs.dev/config/
export default defineConfig({
    base: "/deploy",
    build: {
        outDir: "deploy"
    },
    plugins: [
        vue()
    ],
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('./src', import.meta.url))
        }
    }
})
