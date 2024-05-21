import {fileURLToPath, URL} from 'node:url'

import {defineConfig, loadEnv} from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'
import nodeResolve from "@rollup/plugin-node-resolve";
import socialuniPlatformAutoImportPlugin from "vite-plugin-socialuni-platform-auto-import/src/index";

// https://vitejs.dev/config/

export default ({mode}) => {
    const env: string = loadEnv(mode, process.cwd());

    return defineConfig({
        plugins: [
            vue(),
            vueJsx(),
            nodeResolve(),
            socialuniPlatformAutoImportPlugin()
        ],
        server: {
            port: 8086,
            //配置端口
            //host: '0.0.0.0', //本机地址
            //port:5000,  //通过5000端口号访问

            //配置反向代理
            proxy: {
                //凡是以 /api 地址开头的请求, 都要做代理操作
                [env.VITE_APP_BASE_API]: {
                    //目的地址 => 代理服务器,需要向该地址发起请求
                    target: env.VITE_APP_BASE_URL,
                    changeOrigin: true,
                }
            },
        },
        resolve: {
            alias: {
                '@': fileURLToPath(new URL('./src', import.meta.url))
            }
        },
        css: {
            preprocessorOptions: {
                scss: {
                }
            }
        }
    })

}
