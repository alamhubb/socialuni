import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import {fileURLToPath} from "node:url";
import {visualizer} from 'rollup-plugin-visualizer'
// https://vitejs.dev/config/
export default defineConfig({
    plugins: [
        vue(),
        // visualizer({open: true})
    ],
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('./src', import.meta.url))
        }
    },
    build: {
        // rollup 配置
        rollupOptions: {
            output: {
                chunkFileNames: 'js/[name]-[hash].js', // 引入文件名的名称
                entryFileNames: 'js/[name]-[hash].js', // 包的入口文件名称
                assetFileNames: '[ext]/[name]-[hash].[ext]', // 资源文件像 字体，图片等
                manualChunks(id) {
                    if (id.includes("node_modules")) {
                        // 让每个插件都打包成独立的文件
                        return id .toString() .split("node_modules/")[1] .split("/")[0] .toString();
                    }
                }
            }
        }
    }
})
