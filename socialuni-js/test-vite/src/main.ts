import './assets/main.css'

import {createApp} from 'vue'
import App from './App.vue'
import {QingCompatJs} from "qing-compat-js/src";

var app = createApp(App)

const modules = import.meta.glob('./**/*.ts', {eager: true})
console.log(modules)
app.use(QingCompatJs)

app.mount('#app')
