import {createApp} from "vue";
import {createPinia} from "pinia";

import App from "./App.vue";
import router from "./router";
// import "@/styles/qing-scss/index.scss";
// import "@/styles/index.scss";
import vuetify from "@/plugins/vuetify";
import {loadFonts} from "@/plugins/webfontloader";

const app = createApp(App);


loadFonts();

app.use(createPinia());
app.use(router);
app.use(vuetify);

app.mount("#app");
