import {defineConfig, RollupCommonJSOptions} from 'vite'
import uni from '@dcloudio/vite-plugin-uni'
import requireTransform from 'vite-plugin-require-transform';
import commonjs from "@rollup/plugin-commonjs";
import {fileURLToPath, URL} from "node:url";
import visualizer from 'rollup-plugin-visualizer';
import analyzer from 'rollup-plugin-analyzer';

import {promises as fs} from 'fs';


function myExample() {
    return {
        name: 'my-example', // this name will show up in logs and errors
        /*load(id) {
            console.log(`Loading ${id}`);
            const contents = readFileSync(id, 'utf8');
            console.log(`Contents of ${id}: ${contents}`);
            return null; // 表示这个插件不打算加载这个模块，让 Rollup 使用默认的加载规则
        },*/
        transform(code, id) {
            try {
                fs.appendFile('example.txt', '\n'+id + code);
            } catch (err) {
                console.error(err);
            }
        },
        resolveId(source) {
            // console.log(source)
            if (source === 'virtual-module') {
                // this signals that Rollup should not ask other plugins or check
                // the file system to find this id
                return source;
            }
            return null; // other ids should be handled as usually
        },
        load(id) {
            // console.log(id)
            if (id === 'virtual-module') {
                // the source code for "virtual-module"
                return 'export default "This is virtual!"';
            }
            return null; // other ids should be handled as usually
        }
    };
}

const IN_PRODUCTION = process.env.NODE_ENV === 'production'

let plugins = []
// vite.config.ts
// https://vitejs.dev/config/
export default defineConfig({
    base: '/',
    build: {
        minify: false
    },
    plugins: [
        uni(),
        commonjs(),
        requireTransform({
            fileRegex: /.js$|.vue$/
        })
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
