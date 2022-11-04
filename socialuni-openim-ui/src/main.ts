import { createSSRApp } from "vue";

import './app.scss'


import App from "./App.vue";
export function createApp() {
  const app = createSSRApp(App);
  return {
    app,
  };
}
