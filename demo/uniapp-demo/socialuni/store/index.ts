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

export let socialAppModule
export let socialTalkModule
export let socialUserModule
export let socialConfigModule
export let socialSystemModule
export let socialChatModule
export let socialPlatformModule
export let socialNotifyModule
export let socialTagModule
export let socialLocationModule

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
}

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

