import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import transformIoc from "./viteplugin/index";


// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    transformIoc()
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  }
})
