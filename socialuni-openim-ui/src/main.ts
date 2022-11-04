import { createSSRApp } from "vue";
import App from "./App.vue";
import {createPinia} from "pinia";
import './app.scss'

const pinia = createPinia()

console.log(111111)

export function createApp() {
  const app = createSSRApp(App);
  app.use(pinia)
  return {
    app,
  };
}
