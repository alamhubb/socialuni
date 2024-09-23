import './assets/main.css'

import {createApp} from 'vue'
import App from './App.vue'
import {typeIocJs} from "typeiocjs/src";

var app = createApp(App)
// app.use(typeIocJs, './src/**/*.ts')
const modules = import.meta.glob('./**/*.ts', {eager: true});
app.mount('#app')
