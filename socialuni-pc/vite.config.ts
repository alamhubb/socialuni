import {fileURLToPath, URL} from 'url'

import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import Inspect from 'vite-plugin-inspect'
import myExample from './rollup-plugin-my-example.js';
import {babel} from '@rollup/plugin-babel';
import babelPluginMyPlugin from './babel-plugin-myPlugin.js';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    // 仅适用于开发模式
    Inspect(),
    myExample(),
    babel({
      presets: ["@babel/preset-typescript"],
      extensions: [".ts"],
      babelHelpers: 'bundled',
      exclude: 'node_modules/**',
      plugins: [babelPluginMyPlugin()],
    })
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  server: {
    proxy: {
      '/api/': {
        // '/api/': 'https://api.socialuni.cn:8091',
        target: 'https://localpc.socialuni.cn:8091',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, '')
      }
    }
  }
})

