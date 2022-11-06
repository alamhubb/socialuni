import { createSSRApp } from "vue";
import App from "./App.vue";
import {createPinia} from "pinia";
import './app.scss'

const pinia = createPinia()
import openIm from "@/plugins/openIm/openIm";

export function createApp() {
  const app = createSSRApp(App);
  app.use(pinia)
  app.use(openIm)
  return {
    app,
  };
}
