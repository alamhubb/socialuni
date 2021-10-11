import { Module, VuexModule } from 'vuex-class-modules'

//用来存储当前用户的一些信息
@Module({ generateMutationSetters: true })
export default class SocialRouterModule extends VuexModule {
  params: any = null
}
