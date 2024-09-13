import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import TypeIoc from "@/plugins/TypeIoc";

var app = createApp(App)

app.use(TypeIoc)

app.mount('#app')
