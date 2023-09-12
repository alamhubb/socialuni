import { createStore } from 'vuex'
import createPersistedState from "vuex-persistedstate";
import {getUserNum} from "@/network/user.js";

import login from "@/store/login.js";
import songDetail from "@/store/songDetail.js";
import commentData from "@/store/commentData.js";
import singer from '@/store/singer.js'

export default createStore({
  //配置插件
  plugins:[createPersistedState({
    paths:['login','songDetail','commentData','singer']
  })],
  state: {
    isPlay:false,
    boolean:false,
    data:null
  },
  mutations: {
    setPlay(state){
      state.isPlay = !state.isPlay
    },
    setBoolean(state){
      state.boolean = true
    },
    setData(state,payload){
      state.data = payload
    }
  },
  actions: {
    getUserNumber(context){
      getUserNum().then(res => {
        context.commit('setData',res?.data)
      })
    }
  },
  modules: {
    login,
    songDetail,
    commentData,
    singer
  }
})
