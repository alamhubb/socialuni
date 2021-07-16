import Vue from 'vue'
import Vuex from 'vuex'
import AppModule from '@/store/AppModule'
import TalkModule from '@/store/TalkModule'
import UserModule from '@/store/UserModule'
import ConfigModule from '@/store/ConfigModule'
import SystemModule from '@/store/SystemModule'
import ChatModule from '@/store/ChatModule'
import PlatformModule from '@/store/PlatformModule'
import NotifyModule from '@/store/NotifyModule'
import TagModule from '@/store/TagModule'
import LocationModule from '@/store/LocationModule'
import { namespace } from 'vuex-class'
import UniSystemModule from '@/store/UniSystemModule'

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
export const locationModule = new LocationModule({ store, name: 'location' })
export const uniSystemModule = new UniSystemModule({ store, name: 'uniSystem' })

export const uniSystemStore = namespace('uniSystem')

export default store
