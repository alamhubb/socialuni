import { VuexModule, Module, Action } from 'vuex-class-modules'
import SocialAppModule from './SocialAppModule'

@Module({ generateMutationSetters: true })
export default class SocialOpenDataModule extends VuexModule {
  threeSecretKey = ''
  threeUserId = ''
  threeProviderAppId = ''
  threeProviderType = ''
}
