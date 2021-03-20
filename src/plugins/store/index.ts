import Vue from 'vue'
import Vuex from 'vuex'
import AppModule from '@/plugins/store/AppModule'
import TalkModule from '@/plugins/store/TalkModule'
import UserModule from '@/plugins/store/UserModule'
import ConfigModule from '@/plugins/store/ConfigModule'
import SystemModule from '@/plugins/store/SystemModule'
import ChatModule from '@/plugins/store/ChatModule'
import PlatformModule from '@/plugins/store/PlatformModule'
import NotifyModule from '@/plugins/store/NotifyModule'
import TagModule from '@/plugins/store/TagModule'
import LocationModule from '@/plugins/store/LocationModule'

Vue.use(Vuex)

const store = new Vuex.Store({})

export const appModule = new AppModule({ store, name: 'app' })
export const talkModule = new TalkModule({ store, name: 'talk' })
export const userModule = new UserModule({ store, name: 'user' })
export const configModule = new ConfigModule({ store, name: 'config' })
export const systemModule = new SystemModule({ store, name: 'system' })
export const chatModule = new ChatModule({ store, name: 'chat' })
export const platformModule = new PlatformModule({ store, name: 'platform' })

export const notifyModule = new NotifyModule({ store, name: 'notify' })
export const tagModule = new TagModule({ store, name: 'tag' })
export const locationModule = new LocationModule({ store, name: 'district' })



export default store
