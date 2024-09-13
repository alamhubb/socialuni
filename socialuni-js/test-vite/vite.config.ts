import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import transformIoc from "./viteplugin/index";
import Inspect from 'vite-plugin-inspect'

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
  plugins: [
    Inspect(),
    vue(),
    // inspectPlugin(),
    transformIoc(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  }
})
