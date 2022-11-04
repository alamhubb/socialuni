import {defineConfig} from "vite";
import {fileURLToPath, URL} from "node:url";
import uni from "@dcloudio/vite-plugin-uni";
import commonjs from "@rollup/plugin-commonjs";


// https://vitejs.dev/config/
export default defineConfig({
    plugins: [uni(), commonjs()],
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('./src', import.meta.url)),
        }
    }
});
