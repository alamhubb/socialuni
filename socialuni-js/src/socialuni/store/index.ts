import SocialAppModule from './SocialAppModule'
import SocialTalkModule from './SocialTalkModule'
import SocialUserModule from './SocialUserModule'
import SocialConfigModule from './SocialConfigModule'
import SocialSystemModule from './SocialSystemModule'
import SocialChatModule from './SocialChatModule'
import SocialPlatformModule from './SocialPlatformModule'
import SocialNotifyModule from './SocialNotifyModule'
import SocialTagModule from './SocialTagModule'
import SocialLocationModule from './SocialLocationModule'
import { namespace } from 'vuex-class'
import SocialModuleName from './SocialModuleName'
import { Store } from 'vuex'
import SocialRouterModule from '@/socialuni/store/SocialRouterModule'
import SocialOAuthModule from '@/socialuni/store/SocialOAuthModule'
import SocialCircleModule from '@/socialuni/store/SocialCircleModule'

export let socialAppModule: SocialAppModule
export let socialTalkModule: SocialTalkModule
export let socialUserModule: SocialUserModule
export let socialConfigModule: SocialConfigModule
export let socialSystemModule: SocialSystemModule
export let socialChatModule: SocialChatModule
export let socialPlatformModule: SocialPlatformModule
export let socialNotifyModule: SocialNotifyModule
export let socialTagModule: SocialTagModule
export let socialLocationModule: SocialLocationModule
export let socialRouterModule: SocialRouterModule
export let socialOAuthModule: SocialOAuthModule
export let socialCircleModule: SocialCircleModule

export function registerSocialStore (store: Store<any>) {
  socialAppModule = new SocialAppModule({
    store,
    name: SocialModuleName.socialAppModule
  })
  socialTalkModule = new SocialTalkModule({
    store,
    name: SocialModuleName.socialTalkModule
  })
  socialUserModule = new SocialUserModule({
    store,
    name: SocialModuleName.socialUserModule
  })
  socialConfigModule = new SocialConfigModule({
    store,
    name: SocialModuleName.socialConfigModule
  })
  socialSystemModule = new SocialSystemModule({
    store,
    name: SocialModuleName.socialSystemModule
  })
  socialChatModule = new SocialChatModule({
    store,
    name: SocialModuleName.socialChatModule
  })
  socialPlatformModule = new SocialPlatformModule({
    store,
    name: SocialModuleName.socialPlatformModule
  })
  socialNotifyModule = new SocialNotifyModule({
    store,
    name: SocialModuleName.socialNotifyModule
  })
  socialTagModule = new SocialTagModule({
    store,
    name: SocialModuleName.socialTagModule
  })
  socialLocationModule = new SocialLocationModule({
    store,
    name: SocialModuleName.socialLocationModule
  })
  socialRouterModule = new SocialRouterModule({
    store,
    name: SocialModuleName.socialRouterModule
  })
  socialOAuthModule = new SocialOAuthModule({
    store,
    name: SocialModuleName.socialOAuthModule
  })
  socialCircleModule = new SocialCircleModule({
    store,
    name: SocialModuleName.socialCircleModule
  })
}

export const socialCircleStore = namespace(SocialModuleName.socialOAuthModule)
export const socialOAuthStore = namespace(SocialModuleName.socialCircleModule)
export const socialAppStore = namespace(SocialModuleName.socialAppModule)
export const socialTalkStore = namespace(SocialModuleName.socialTalkModule)
export const socialUserStore = namespace(SocialModuleName.socialUserModule)
export const socialConfigStore = namespace(SocialModuleName.socialConfigModule)
export const socialSystemStore = namespace(SocialModuleName.socialSystemModule)
export const socialChatStore = namespace(SocialModuleName.socialChatModule)
export const socialPlatformStore = namespace(SocialModuleName.socialPlatformModule)
export const socialNotifyStore = namespace(SocialModuleName.socialNotifyModule)
export const socialTagStore = namespace(SocialModuleName.socialTagModule)
export const socialLocationStore = namespace(SocialModuleName.socialLocationModule)
export const socialRouterStore = namespace(SocialModuleName.socialRouterModule)

