import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import transformIoc from "./viteplugin/index";
import Inspect from 'vite-plugin-inspect'
import babel from 'vite-plugin-babel';

// rollup-plugin-inspect.js
function inspectPlugin() {
  return {
    name: 'inspect-plugin',

    options(options) {
      console.log('Options:', options);
    },

    buildStart(options) {
      console.log('Build Start:', options);
    },

    resolveId(source, importer) {
      console.log('Resolve ID:', { source, importer });
      return null; // 继续默认行为
    },

    load(id) {
      console.log('Load Module:', id);
      return null; // 继续默认行为
    },

    transform(code, id) {
      console.log('Transforming:', { id, code });
      return null; // 不修改代码
    },

    generateBundle(outputOptions, bundle) {
      console.log('Generating Bundle:', bundle);
    },

    writeBundle(bundle) {
      console.log('Bundle Written:', bundle);
    }
  };
}

// https://vitejs.dev/config/
export default defineConfig({
  esbuild: {
    target: 'es5', // 使 esbuild 生成 es5 代码
  },
  plugins: [
    Inspect(),
    vue(),
    babel({
      babelConfig: {
        include: [/\.vue$/, /\.ts$/, /\.tsx$/, /\.jsx$/, /\.js$/],
        presets: [
          [
            '@babel/preset-env',
            {
              targets: 'es5', // 或者你可以指定更详细的目标环境
            },
          ],
        ],
      },
    }),
    // inspectPlugin(),
    transformIoc(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  }
})
