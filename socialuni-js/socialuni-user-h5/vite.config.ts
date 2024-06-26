import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import {fileURLToPath} from "node:url";
import socialuniPlatformAutoImportPlugin from "vite-plugin-socialuni-platform-auto-import/src";

// https://vitejs.dev/config/
export default defineConfig({
    base: "/user",
    build: {
        outDir: "user"
    },
    plugins: [
        vue(),
        socialuniPlatformAutoImportPlugin()
    ],
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('./src', import.meta.url))
        }
    }
})
