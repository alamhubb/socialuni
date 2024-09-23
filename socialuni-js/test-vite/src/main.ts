import './assets/main.css'

import {createApp} from 'vue'
import App from './App.vue'

var app = createApp(App)

const modules = import.meta.glob('./**/*.ts', {eager: true})
console.log(modules)

app.mount('#app')
