import Vue from 'vue'
import Vuex from 'vuex'
import AppModule from '@/store/AppModule'
import TalkModule from '@/store/TalkModule'
import UserModule from '@/store/UserModule'
import ConfigModule from '@/store/ConfigModule'
import SocialSystemModule from '@/store/SocialSystemModule'
import ChatModule from '@/store/ChatModule'
import PlatformModule from '@/store/PlatformModule'
import NotifyModule from '@/store/NotifyModule'
import TagModule from '@/store/TagModule'
import LocationModule from '@/store/LocationModule'
import { namespace } from 'vuex-class'

Vue.use(Vuex)

const store = new Vuex.Store({})

export const appModule = new AppModule({ store, name: 'app' })
export const talkModule = new TalkModule({ store, name: 'talk' })
export const userModule = new UserModule({ store, name: 'user' })
export const configModule = new ConfigModule({ store, name: 'config' })
export const socialSystemModule = new SocialSystemModule({ store, name: 'system' })
export const chatModule = new ChatModule({ store, name: 'chat' })
export const platformModule = new PlatformModule({ store, name: 'platform' })
export const notifyModule = new NotifyModule({ store, name: 'notify' })
export const tagModule = new TagModule({ store, name: 'tag' })
export const locationModule = new LocationModule({ store, name: 'location' })

export const appStore = namespace('app')
export const talkStore = namespace('talk')
export const userStore = namespace('user')
export const configStore = namespace('config')
export const systemStore = namespace('system')
export const chatStore = namespace('chat')
export const platformStore = namespace('platform')
export const notifyStore = namespace('notify')
export const tagStore = namespace('tag')
export const locationStore = namespace('location')

export default store
