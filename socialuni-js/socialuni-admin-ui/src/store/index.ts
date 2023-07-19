import Vue from 'vue'
import Vuex from 'vuex'
import UserModule from '@/store/UserModule'
import AppModule from '@/store/AppModule'

Vue.use(Vuex)

const store = new Vuex.Store({})

export const userModule = new UserModule({ store, name: 'user' })
export const appModule = new AppModule({ store, name: 'app' })

export default store

