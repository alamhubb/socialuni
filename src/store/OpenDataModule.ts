import { VuexModule, Module, Action } from 'vuex-class-modules'

@Module({ generateMutationSetters: true })
export default class OpenDataModule extends VuexModule {
  threeSecretKey = ''
  threeUserId = ''
  threeProviderAppId = ''
  threeProviderType = ''
}
