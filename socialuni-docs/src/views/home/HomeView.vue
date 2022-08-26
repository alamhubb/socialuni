<template>
  <div>
    <markdown>
      # vite-plugin-md-string

      ### this is get markdown string from *.md vite-plugin

      ## install

      ```shell
      npm install vite-plugin-md-string -D
      ```

      ## usage

      ```js
      import mdString from 'vite-plugin-md-string'

      // https://vitejs.dev/config/
      export default defineConfig({
      plugins: [vue(), mdString()],
      server: {
      host: "0.0.0.0"
      },
      resolve: {
      alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
      }
      }
      })
      ```

      ## demo

      ### input ./hello.md

      ```markdown
      # hello world
      ```

      ### output ./hello.js

      ```markdown
      import helloStr from './hello.md'

      console.log(helloStr) // hello world
      ```
    </markdown>
  </div>
</template>
<script setup lang="ts">
import homeMd from '@/views/home/home.md'
import Markdown from "@/components/Markdown.vue";

console.log(homeMd)
</script>
